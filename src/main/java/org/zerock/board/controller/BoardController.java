package org.zerock.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.service.BoardService;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import java.util.List;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(Model model){
        List<BoardDTO> boardList = boardService.getList();
        model.addAttribute("result", boardList);
    }

    @GetMapping("/read")
    public String read(Long bno, Model model, RedirectAttributes redirectAttributes){
        log.info("bno: " + bno);

        String return_url = "board/read";

        try {
            BoardDTO boardDTO = boardService.getView(bno);
            model.addAttribute("dto", boardDTO);
        }catch (Exception e) {
            log.error("게시물이 없습니다.: " + e.getMessage());

            return_url = "redirect:/board/list";
        }
        return return_url;
    }


    @GetMapping("/register")
    public void register(){
        log.info("regiser get...");
    }

    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes){

        String return_url = "redirect:/board/list";

        try {
            log.info("dto..." + dto);
            //새로 추가된 엔티티의 번호
            Long bno = boardService.register(dto);

            log.info("BNO: " + bno);

            if (bno == null) {
                log.error("게시물 등록 실패: 반환된 bno가 null입니다.");
            }

            redirectAttributes.addFlashAttribute("msg", bno);
            return_url = "redirect:/board/read?bno=" + bno;
        }catch (Exception e) {
            log.error("게시물 등록 실패: " + e.getMessage());
        }

        return return_url;
    }

}