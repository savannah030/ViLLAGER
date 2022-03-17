package com.savannah030.ViLLAGER.repository;

import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 페치 조인
    @Query("select b from Board b join fetch b.seller s where b.idx = :boardId")
    Optional<Board> findBoardWithMember(@Param("boardId") Long boardId);

    @Query("select b from Board b where b.address.latitude<=:latitude+0.001*10 and b.address.longitude<=:longitude+0.001*10")
    List<Board> findAllByLatitudeAndLongitude(@Param("latitude")Double latitude,@Param("longitude")Double longitude);
}
