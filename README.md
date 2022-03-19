# ViLLAGER
사용자의 현재 위치를 받아 글을 생성하고, 지도에서 현재 위치와 가까운 글을 찾는 서비스


![screen-recording (1)](https://user-images.githubusercontent.com/95270406/158801763-05ecdb7f-ecf8-4153-b6aa-72db2861ae96.gif)


# 기술스택
Spring Boot, Spring MVC, JPA, 카카오 지도 API 
# 기능
## 회원가입,로그인
OAuth2를 이용한 구글 로그인
## 게시판
#### 게시글 생성, 수정, 삭제 
- 로그인한 사용자만 글 작성할 수 있음
- 작성자만 글 수정,삭제할 수 있음
#### 사용자가 글을 올리면, 사용자의 위도,경도 정보를 받아와 엔티티에 저장

## 지도에서 가까운 게시글 찾기 서비스
#### 카카오 지도 api의 마커, 커스텀 오버레이 사용
#### mouseover,mouseout 이벤트 적용



# 배운 것
[예외처리](https://github.com/savannah030/ViLLAGER/pull/6)<br/>
[게시판 서비스 흐름](https://github.com/savannah030/ViLLAGER/pull/8)<br/>
[자바 Optional](https://velog.io/@savannah030/%EC%9E%90%EB%B0%94-Optional%EB%9E%8C%EB%8B%A4stream)<br/>
[사용자와 가까운 게시글 데이터 갖고와서 지도 api에 뿌리기](https://github.com/savannah030/ViLLAGER/pull/9)<br/>
