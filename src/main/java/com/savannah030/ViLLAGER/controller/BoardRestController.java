package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.config.auth.dto.SessionMemberDto;
import com.savannah030.ViLLAGER.dto.BoardSaveRequestDto;
import com.savannah030.ViLLAGER.dto.BoardUpdateRequestDto;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardRestController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final HttpSession httpSession;

    // Create
    // NOTE: @RequestBody는 JSON 형식으로 전송된 요청 데이터를 객체로 전달받음
    //  createdDate, updateDate는 사용자가 임의로 지정하면 안되므로 서비스단에서 처리
    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody BoardSaveRequestDto boardDto) {
        SessionMemberDto sessionMember = (SessionMemberDto) httpSession.getAttribute("member");
        ReturnCode result = boardService.createBoard(boardDto, sessionMember);

        if(result != ReturnCode.SUCCESS){
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }


    // Read
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable){
    //   Page<Board> boards = boardRepository.findAll(pageable);
        /**
         * public PageMetadata(long size,
         *                     long number,
         *                     long totalElements,
         *                     long totalPages)
         */
    //   PagedModel.PageMetadata pageMetadata
    //            = new PagedModel.PageMetadata(pageable.getPageSize(),
    //                                            boards.getNumber(),
    //                                            boards.getTotalElements());
    //    PagedModel<Board> models = PagedModel.of(boards.getContent(),pageMetadata);
    //    models.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());
    //    return ResponseEntity.ok(models);
    //}

    // Update
    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx, @RequestBody BoardUpdateRequestDto boardDto) {

        // TODO valid 체크
        log.info("update boardDto: {}", boardDto);
        ReturnCode result = boardService.updateBoard(idx, boardDto);
        if (result != ReturnCode.SUCCESS){
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx") Long idx){
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
