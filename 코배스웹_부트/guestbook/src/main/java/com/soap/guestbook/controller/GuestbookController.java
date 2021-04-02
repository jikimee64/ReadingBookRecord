package com.soap.guestbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.soap.guestbook.dto.GuestBookDTO;
import com.soap.guestbook.dto.PageRequestDTO;
import com.soap.guestbook.service.GuestBookService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestbookController {

    private final GuestBookService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..............." + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestBookDTO.Request.Save dto, RedirectAttributes redirectAttributes){
        log.info("dto..." + dto);

        // 새로 추가된 엔티티의 번호
        Long gno = service.register(GuestBookDTO.Request.Save.toEntity(dto));

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
        Model model){
        log.info("gno: " + gno);

        GuestBookDTO.Response.Read dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long gno, RedirectAttributes redirectAttributes){
        log.info("gno : " + gno);

        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @PostMapping("/modify")
    public String modify(GuestBookDTO.Request.Modify dto,
                            @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                            RedirectAttributes redirectAttributes){

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }


}
