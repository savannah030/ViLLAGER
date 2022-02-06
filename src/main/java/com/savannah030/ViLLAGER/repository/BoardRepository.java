package com.savannah030.ViLLAGER.repository;

import com.savannah030.ViLLAGER.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByLatitude(double latitude); //JpaMappingTest용
    Board findByLongitude(double longitude);

    //here
    @Query("select b from Board b where b.latitude<=:latitude+0.001*10 and b.longitude<=:longitude+0.001*10")
    List<Board> findAllByLatitudeAndLongitude(@Param("latitude")Double latitude,@Param("longitude")Double longitude);
}
