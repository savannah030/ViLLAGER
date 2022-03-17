package com.savannah030.ViLLAGER.service;

import com.savannah030.ViLLAGER.config.auth.dto.SessionMemberDto;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.dto.BoardListResponseDto;
import com.savannah030.ViLLAGER.dto.MyBoardResponseDto;
import com.savannah030.ViLLAGER.dto.BoardSaveRequestDto;
import com.savannah030.ViLLAGER.dto.BoardUpdateRequestDto;
import com.savannah030.ViLLAGER.exception.ReturnCode;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // CREATE
    /**
     * 1. 세션에 있는 사용자의 정보로 사용자 찾기
     * 2. 사용자가 존재하지 않으면 오류 리턴
     * 3. 글을 저장하고 인덱스 리턴(이 때 사용자의 정보도 같이 저장)
     * 4. 글이 생성됐는지 확인
     */
    public ReturnCode createBoard(BoardSaveRequestDto boardSaveRequestDto, SessionMemberDto sessionMember) {

        Optional<Member> member = memberRepository.findByEmail(sessionMember.getEmail()); // 1.
        if(!member.isPresent()){ // 2.
            return ReturnCode.MEMBER_NOT_EXIST;
        }

        Long idx = boardRepository.save(boardSaveRequestDto.toEntity(member.get())).getIdx(); //3.
        Optional<Board> findBoard = boardRepository.findById(idx); // 4.
        if(!findBoard.isPresent()){
            return ReturnCode.FAIL_TO_CREATE_BOARD;
        }
        return ReturnCode.SUCCESS;
    }

    // READ
    // NOTE: 엔티티 객체 찾아서 있으면 그 엔티티를 DTO로 변환해서 반환
    //  엔티티 없으면 일단 새로운 DTO를 반환하기
    //  엔티티 생성은 BoardService.createBoard에서!!!! (저장버튼 누를 때)
    @Transactional
    public MyBoardResponseDto findMyBoardByIdx(Long idx){
        Optional<Board> entity = boardRepository.findById(idx);
        // 이미 있는 게시글을 클릭한 경우는 조회수 증가
        entity.ifPresent(Board::increaseHits);
        // 엔티티 객체 없으면 필드값을 초기화하지않은 dto 생성
        // 있으면 그 객체의 필드로 dto 초기화
        return entity.map(MyBoardResponseDto::new).orElseGet(MyBoardResponseDto::new);
    }

    // READ LIST
    public Page<BoardListResponseDto> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1, pageable.getPageSize());
        Page<Board> boardList = boardRepository.findAll(pageable);
        // NOTE: 자바 stream 공부하기!!
        return new PageImpl<>(boardList.stream().map(BoardListResponseDto::new).collect(Collectors.toList()), pageable,boardList.getTotalElements());
    }

    // UPDATE
    // NOTE: 트랜잭션 끝날 때 플러쉬 하면서 데이터 상태를 DB에 동기화
    @Transactional
    public ReturnCode updateBoard(Long idx, BoardUpdateRequestDto boardDto){
        Optional<Board> board = boardRepository.findById(idx);
        if(!board.isPresent()){
            return ReturnCode.FAIL_TO_CREATE_BOARD;
        }
        else {
            // NOTE: Optional 클래스의 ifPresent 메서드는 값이 존재하면 지정된 Consumer를 실행하고, 값이 없으면 아무 일도 일어나지 않음
            //  Consumer란 함수형 인터페이스의 한 종류로, T 형식의 객체를 인수로 받아서 동작 수행
            board.ifPresent(board1 -> board1.update(boardDto));
            return ReturnCode.SUCCESS;
        }
    }

    //DELETE 는 컨트롤러에서 바로 삭제
}
