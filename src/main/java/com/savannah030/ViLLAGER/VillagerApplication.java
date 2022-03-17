package com.savannah030.ViLLAGER;

import com.savannah030.ViLLAGER.domain.components.Address;
import com.savannah030.ViLLAGER.domain.entity.Board;
import com.savannah030.ViLLAGER.domain.entity.Member;
import com.savannah030.ViLLAGER.domain.enums.CategoryType;
import com.savannah030.ViLLAGER.domain.enums.RoleType;
import com.savannah030.ViLLAGER.repository.BoardRepository;
import com.savannah030.ViLLAGER.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.IntStream;

@EnableJpaAuditing // NOTE: JPA Auditing 활성화해야
@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
@Slf4j
public class VillagerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(VillagerApplication.class, args);
	}


	@Bean
	public CommandLineRunner runner(MemberRepository memberRepository, BoardRepository boardRepository) {
		return (args) -> {

			// 일반사용자 member1과 손님 member2 생성
			Member member1 = memberRepository.save(Member.builder()
							.memberName("Yooni윤승")
							.email("syhan97@gmail.com")
							.roleType(RoleType.USER)
							.build());
			Member member2 = memberRepository.save(Member.builder()
							.memberName("짱짱유튜브")
							.email("syhan9708@gmail.com")
							.roleType(RoleType.GUEST)
							.build());

			// NOTE: 날짜는 BaseEntity에서 자동 생성되므로 builder로 생성할 필요없음
			IntStream.rangeClosed(1, 100).forEach(index ->
							boardRepository.save(Board.builder()
									.categoryType(CategoryType.ELECTRONICS)
									.title("게시글"+index)
									.content("컨텐츠")
									.address(new Address(0.001*(index)+37.4865883, 0.001*(index)+126.9839492))
									.seller(member1)
									.build())
			);
			IntStream.rangeClosed(101, 200).forEach(index ->
							boardRepository.save(Board.builder()
									.categoryType(CategoryType.CLOTHES)
									.title("게시글"+index)
									.content("컨텐츠")
									.address(new Address(0.001*(index)+39.4865883, 0.001*(index)+128.9839492))
									.seller(member2)
									.build())
			);
			log.info("board[0] 카테고리: {}", boardRepository.findById(1L).get().getCategoryType()); // ok
			log.info("board[0] 제목: {}", boardRepository.findById(1L).get().getTitle());			// ok
			log.info("board[0] 판매상태: {}", boardRepository.findById(1L).get().getStatusType().getValue());	// ok
			log.info("board[0] 생성날짜: {}", boardRepository.findById(1L).get().getCreatedDate()); // ok
			log.info("board[0] 조회수: {}", boardRepository.findById(1L).get().getHits()); // ok

		};
	}

}
