package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.config.auth.LoginUser;
import com.savannah030.ViLLAGER.config.auth.dto.SessionMemberDto;
import com.savannah030.ViLLAGER.dto.MyBoardResponseDto;
import com.savannah030.ViLLAGER.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /** 게시글 보여주기
     *  작성자만 글 수정,삭제할 수 있도록
     */
    @GetMapping("/form")
    public String form(@RequestParam(value="idx", defaultValue = "0")Long idx, Model model, @LoginUser SessionMemberDto seller){

        MyBoardResponseDto dto = boardService.findMyBoardByIdx(idx); // 조회수 증가

        if (seller.getEmail().equals(dto.getSeller().getEmail())){  // 작성자인지 체크(이메일이 같은 사용자는 같은 사용자라고 판단)
            model.addAttribute("isSeller",true);
        } else{
            model.addAttribute("isSeller",false);
        }
        model.addAttribute("boardResponseDto", dto);
        return "/board/form";
    }

    /**
     * 게시판 목록 보여주기
     * 목록 위 등록 버튼: 로그인한 일반 사용자만 글 쓸 수 있도록. 로그인하지 않은 경우에는 숨기기
     */
    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model, @LoginUser SessionMemberDto member){
        if (member != null){
            model.addAttribute("member",member);
        }
        model.addAttribute("boardList",boardService.findBoardList(pageable)); //  Page<BoardListResponseDto>
        return "/board/list";
    }
}
