package com.kwc.ch2reactive.ch2reactive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private CartService cartService;

    public HomeController(ItemRepository itemRepository,
        CartRepository cartRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    /**
     * Mono<Rendering> : 뷰/애트리뷰트를 포함하는 웹플럭스 컨테이너
     * @return
     */
    @GetMapping
    Mono<Rendering> home(){
        return Mono.just(Rendering.view("home.html")
        .modelAttribute("items", this.itemRepository.findAll()) //Flux<Item> 반환
        .modelAttribute("cart", this.cartRepository.findById("My Cart") //Mono<Cart> 반환
        .defaultIfEmpty(new Cart("My Cart"))) //전형적인 리액터 사용법
        .build());
    }

    /**
     * - 현재 장바구니를 조회하고, 없으면 비어 있는 새 장바구니를 생성
     * - 장바구니에 담은 상품이 이미 장바구니에 있던 상품이라면 수량만 1 증가시키고, 기존에 없던 상품이라면
     * 상품 정보를 표시하고 수량을 1로 표시
     * - 장바구니 저장
     * @param id
     * @return
     */
    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id){
        return this.cartService.addToCart("My Cart", id)
            .thenReturn("redirect:/");

        //서비스로 추출
//        return this.cartRepository.findById("My Cart")
//            .defaultIfEmpty(new Cart("My Cart"))
//            .flatMap(cart -> cart.getCartItems().stream()
//                .filter(cartItem -> cartItem.getItem().getId().equals(id))
//                .findAny()
//                    .map(cartItem -> { //같은 상품이 있다면 map() 내부에서 해당 상품의 수량만 증가시키고 장바구니를 Mono에 담아 반환
//                        cartItem.increment();
//                        return Mono.just(cart);
//                    })
//                    .orElseGet(() -> { //같은 상품이 없다면 해당상품을 조회 및 수량 1지정 후 CartItem에 담은 다음, CatrItem을 장바구니에 추가한 후 장바구니를 반환
//                        return this.itemRepository.findById(id)
//                            .map(item -> new CartItem(item))
//                            .map(cartItem -> {
//                                cart.getCartItems().add(cartItem);
//                                return cart;
//                            });
//                    }))
//            .flatMap(cart -> this.cartRepository.save(cart))
//            .thenReturn("redirect:/");
    }
}
