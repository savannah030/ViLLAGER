package com.savannah030.ViLLAGER.controller;

import com.savannah030.ViLLAGER.domain.Board;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.PagedModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

    private BoardRepository boardRepository;

    // 생성자로 Bean 주입받기
    public BoardRestController(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    // Create
    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody Board board){
        boardRepository.save(board);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    /**
     *
     * @param pageable
     * @return
     */
    // Read
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        /**
         * public PageMetadata(long size,
         *                     long number,
         *                     long totalElements,
         *                     long totalPages)
         */
        PagedModel.PageMetadata pageMetadata
                = new PagedModel.PageMetadata(pageable.getPageSize(),
                                                boards.getNumber(),
                                                boards.getTotalElements());
        PagedModel<Board> models = PagedModel.of(boards.getContent(),pageMetadata);
        models.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(models);
    }

    /**
     *
     * @param idx
     * @param board
     * @return
     */
    // Update
    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx, @RequestBody Board board) {
        // here valid 체크
        Board board1 = boardRepository.getById(idx);
        board1.update(board);
        boardRepository.save(board1);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    /**
     *
     * @param idx
     * @return
     */
    // Delete
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx") Long idx){
        // here valid 체크
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
