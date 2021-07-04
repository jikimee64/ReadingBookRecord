package com.kwc.ch2reactive.ch2reactive;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(ItemRepository itemRepository,
        CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    Mono<Cart> addToCart(String cartId, String id){
        return this.cartRepository.findById("My Cart")
            .defaultIfEmpty(new Cart("My Cart"))
            .flatMap(cart -> cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(id))
                .findAny()
                .map(cartItem -> { //같은 상품이 있다면 map() 내부에서 해당 상품의 수량만 증가시키고 장바구니를 Mono에 담아 반환
                    cartItem.increment();
                    return Mono.just(cart);
                })
                .orElseGet(() -> //같은 상품이 없다면 해당상품을 조회 및 수량 1지정 후 CartItem에 담은 다음, CatrItem을 장바구니에 추가한 후 장바구니를 반환
                    this.itemRepository.findById(id)
                        .map(CartItem::new)
                        .doOnNext(cartItem -> cart.getCartItems().add(cartItem))
                        .map(cartItem -> cart)))
            .flatMap(this.cartRepository::save);
    }
}
