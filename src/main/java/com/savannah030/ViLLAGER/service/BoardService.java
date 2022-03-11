package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
//import com.savannah030.ViLLAGER.dto.BoardDto;
import com.savannah030.ViLLAGER.dto.BoardDto;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx){
        return boardRepository.findById(idx).orElse(new Board());
    }

    public ReturnCode createBoard(BoardDto boardDto){
        // NOTE: 빌더 패턴을 통해 '서비스단'에서 엔티티 객체 생성하고 리포지토리에 저장
        // CONFUSED: Board 엔티티 영속성?
        Long idx = boardRepository.save(Board.builder()
                .categoryType(boardDto.getCategoryType())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .address(new Address(boardDto.getLatitude(),boardDto.getLongitude())).build()).getIdx();
        Optional<Board> findBoard = boardRepository.findById(idx); // 글이 생성됐는지 확인
        if(!findBoard.isPresent()){
            return ReturnCode.FAIL_TO_CREATE_BOARD;
        }
        return ReturnCode.SUCCESS;
    }


}
