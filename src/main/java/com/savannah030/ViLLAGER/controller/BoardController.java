package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.service.BoardService;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    /**
     *
     * @RequestParam HTTP 요청 파라미터의 값을 메서드의 파라미터로 전달
     */
    @GetMapping("/form-save")
    public String formSave() {
        return "/board/form-save";
    }

    @GetMapping("/form-update")
    public String formUpdate(@RequestParam(value="idx", defaultValue = "0")Long idx, Model model){
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        return "/board/form-update";
    }

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable,Model model){
        model.addAttribute("boardList",boardService.findBoardList(pageable));
        return "/board/list";
    }
}
