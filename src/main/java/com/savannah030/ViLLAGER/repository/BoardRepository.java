package com.savannah030.ViLLAGER.repository;

import com.savannah030.ViLLAGER.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByLatitude(double latitude);
    Board findByLongitude(double longitude);
}
