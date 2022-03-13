package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.dto.BoardDto;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
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

    public ReturnCode createBoard(BoardDto boardDto) {
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
    // NOTE: 트랜잭션 끝날 때 플러쉬 하면서 데이터 상태를 DB에 동기화
    @Transactional
    public ReturnCode updateBoard(Long idx, BoardDto boardDto){
        // CONFUSED: board 엔티티 영속성?
        Optional<Board> board = boardRepository.findById(idx);
        if(!board.isPresent()){
            return ReturnCode.FAIL_TO_CREATE_BOARD;
        }
        else {
            // NOTE: Optional 클래스의 ifPresent 메서드는 값이 존재하면 지정된 Consumer를 실행하고, 값이 없으면 아무 일도 일어나지 않음
            //  Consumer란 함수형 인터페이스의 한 종류로, T 형식의 객체를 인수로 받아서 동작 수행
            board.ifPresent(board1 -> {
                board1.update(boardDto);
            });
            return ReturnCode.SUCCESS;
        }
    }
}
