package com.savannah030.ViLLAGER;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.StatusType;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@EnableJpaAuditing // NOTE: JPA Auditing 활성화해야
@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
@Slf4j
public class VillagerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(VillagerApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner(BoardRepository boardRepository) {
		return (args) -> {

			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.categoryType(CategoryType.ELECTRONICS)
							.title("게시글"+index)
							.content("컨텐츠")
							.address(new Address(0.1*(index)+37.4865883, 0.1*(index)+126.9839492)).build()) // 데이터가 황해로 갔던 이유..
							//.createdDate(LocalDateTime.now()) // NOTE: BaseEntity에서 자동 생성되므로 builder로 생성할 필요없음
							//.updatedDate(LocalDateTime.now())
							//.user(user)

			);
			log.info("board[0] 카테고리: {}", boardRepository.findById(1L).get().getCategoryType()); // ok
			log.info("board[0] 제목: {}", boardRepository.findById(1L).get().getTitle());			// ok
			log.info("board[0] 판매상태: {}", boardRepository.findById(1L).get().getStatusType().getValue());	// ok
			log.info("board[0] 생성날짜: {}", boardRepository.findById(1L).get().getCreatedDate()); // ok
			log.info("board[0] 조회수: {}", boardRepository.findById(1L).get().getHits()); // ok

		};
	}

}
