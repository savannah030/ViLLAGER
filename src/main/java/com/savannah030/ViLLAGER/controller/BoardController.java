package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.config.auth.SessionMemberDto;
import com.savannah030.ViLLAGER.dto.MyBoardResponseDto;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession httpSession;

    @GetMapping("/form")
    public String form(@RequestParam(value="idx", defaultValue = "0")Long idx, Model model){
        MyBoardResponseDto dto = boardService.findMyBoardByIdx(idx); // 조회수 증가
        model.addAttribute("boardResponseDto", dto);
        log.info("BoardController");
        log.info("dto에 조회수 증가 적용되는지: {}, {}", dto.getIdx(), dto.getHits()); //ok
        return "/board/form";
    }

    /**
     * 게시판 목록 보여주기
     * 목록 위 등록 버튼: 로그인한 일반 사용자만 글 쓸 수 있도록. 로그인하지 않은 경우에는 숨기기
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable,Model model){
        SessionMemberDto member = (SessionMemberDto) httpSession.getAttribute("member");
        if (member != null){
            model.addAttribute("member",member);
        }
        model.addAttribute("boardList",boardService.findBoardList(pageable));
        return "/board/list";
    }
}
