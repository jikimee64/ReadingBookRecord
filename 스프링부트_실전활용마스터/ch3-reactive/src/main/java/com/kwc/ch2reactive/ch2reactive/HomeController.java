package com.kwc.ch2reactive.ch2reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private InventoryService inventoryService;

    public HomeController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    Mono<Rendering> home(){
        return Mono.just(Rendering.view("home.html")
            .modelAttribute("items", this.inventoryService.getInventory())
            .modelAttribute("cart", this.inventoryService.getCart("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))).build());
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id){
        return this.inventoryService.addItemToCart("My Cart", id)
            .thenReturn("redirect:/");
    }

    @DeleteMapping("/remove/{id}")
    Mono<String> removeFromCart(@PathVariable String id){
        return this.inventoryService.removeOneFromCart("My Cart", id)
            .thenReturn("redirectL:/");
    }

    @PostMapping
    Mono<String> createItem(@ModelAttribute Item newItem){
        return this.inventoryService.saveItem(newItem)
            .thenReturn("redirect:/");
    }

    @DeleteMapping("/delete/{id}")
    Mono<String> deleteItem(@PathVariable String id){
        return this.inventoryService.deleteItem(id)
            .thenReturn("redirect:/");
    }



}
