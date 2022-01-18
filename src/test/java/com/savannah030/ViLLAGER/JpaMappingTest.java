package com.savannah030.ViLLAGER;


import com.savannah030.ViLLAGER.domain.Board;
import com.savannah030.ViLLAGER.domain.Position;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {


    @Autowired
    BoardRepository boardRepository;

    @Before
    public void 현재위치와_근접한_게시물_10개생성() {
        Position curPos = new Position(37.48603206504228,126.98308494303069 );
        for (int i=0;i<10;i++){
            Position testPos = new Position(0.001*(i+1)+curPos.getLatitude(),0.001*(i+1)+curPos.getLongitude());
            boardRepository.save(Board.builder()
                    .title("제목"+i)
                    .content("내용"+i)
                    .latitude(testPos.getLatitude())
                    .longitude(testPos.getLongitude()).build());
        }
    }

    @Test
    public void 제대로_생성됐는지_테스트(){
        Board board = boardRepository.findByLatitude(0.001+37.48603206504228);
        assertThat(board.getTitle(),is("제목"+0));

        Board board2 = boardRepository.findByLongitude(0.002+37.48603206504228);
        assertThat(board2.getTitle(),is("제목"+1));
    }

}
