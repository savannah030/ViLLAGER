package com.savannah030.ViLLAGER;


import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class JpaMappingTest {


    /*
    @Autowired
    BoardRepository boardRepository;

    @Before
    public void 현재위치와_근접한_게시물_10개생성() {
        Location curLoc = new Location(37.48603206504228,126.98308494303069 );
        for (int i=0;i<10;i++){
            Location testLoc = new Location(0.001*(i+1)+curLoc.getLatitude(),0.001*(i+1)+curLoc.getLongitude());
            boardRepository.save(Board.builder()
                    //.idx(li) ///////////////////////
                    .title("제목"+i)
                    .content("내용"+i)
                    .latitude(testLoc.getLatitude())
                    .longitude(testLoc.getLongitude()).build());
        }
    }

    @After
    public void cleanup(){
        boardRepository.deleteAll();
    }

    @Test
    public void 제대로_생성됐는지_테스트(){
        Board board = boardRepository.findByLatitude(0.001+37.48603206504228);
        assertThat(board.getTitle(),is("제목"+0));

        Board board2 = boardRepository.findByLongitude(0.002+126.98308494303069);
        assertThat(board2.getTitle(),is("제목"+1));
    }
     */


}
