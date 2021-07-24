package com.kwc.ch2reactive.ch2reactive;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class InventoryService {

    private ItemRepository itemRepository;
    private CartRepository cartRepository;

    InventoryService(ItemRepository repository,
        CartRepository cartRepository) {
        this.itemRepository = repository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> getCart(String cardId) {
        return this.cartRepository.findById(cardId);
    }

    public Flux<Item> getInventory() {
        return this.itemRepository.findAll();
    }

    Mono<Item> saveItem(Item newItem) {
        return this.itemRepository.save(newItem);
    }

    Mono<Void> deleteItem(String id) {
        return this.itemRepository.deleteById(id);
    }

    Mono<Cart> addItemToCart(String cartId, String itemId) {
        return this.cartRepository.findById(cartId)
            .log("foundCart")
            .defaultIfEmpty(new Cart(cartId))
            .log("emptyCart")
            .flatMap(cart -> cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem()
                    .getId().equals(itemId))
                .findAny()
                .map(cartItem -> {
                    cartItem.increment();
                    return Mono.just(cart).log("newCartItem");
                })
                .orElseGet(() -> {
                    return this.itemRepository.findById(itemId)
                        .log("fetchItem")
                        .map(item -> new CartItem(item))
                        .log("cartItem")
                        .map(cartItem -> {
                            cart.getCartItems().add(cartItem);
                            return cart;
                        }).log("addedCartItem");
                }))
            .log("cartWithAnotherItem")
            .flatMap(cart -> this.cartRepository.save(cart))
            .log("saveCart");
    }

    Mono<Cart> removeOneFromCart(String cardId, String itemId) {
        return this.cartRepository.findById(cardId)
            .defaultIfEmpty(new Cart(cardId))
            .flatMap(cart -> cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                .findAny()
                .map(cartItem -> {
                    cartItem.decrement();
                    return Mono.just(cart);
                })
                .orElse(Mono.empty()))
            .map(cart -> new Cart(cart.getId(), cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getQuantity() > 0)
                .collect(Collectors.toList()))
            )
            .flatMap(cart -> this.cartRepository.save(cart));
    }


}
