# ReactProject
- ### 스프링 부트와 리액트 프레임워크를 이용한 놀이공원 할인을 통한 예매권 구매 사이트
  - [기획의도](#기획의도)
  - [사용기술](#사용기술)
  - [담당파트](#담당파트)

## AWS주소
- http://www.excitingamusement.com/

<br/>

## 개발기간 
 
- ### (2023.06.15 ~ 2023.07.17)

<br/>

# 기획의도
- ### 기존의 스프링 프로젝트(https://github.com/gydn123/SpringProject) 를 스프링부트와 리액트로 백단과 프론트단을 분리하는 작업을 수행
- ### 엘라스틱서치 기술 적용
![jpa](https://github.com/gydn123/ReactProject/assets/121388591/5414aeb6-25e8-43ac-96b5-6cc7af955729)
<br/>

# 사용기술

| | |
| --- | --- |
| FrontEnd | ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) ![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white) ![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) ![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white) |
| BackEnd | ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white) ![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)  ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)  |
| DateBase | ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)  |
| Data and Visualization | ![ElasticSearch](https://img.shields.io/badge/-ElasticSearch-005571?style=for-the-badge&logo=elasticsearch) ![Kibana](https://img.shields.io/badge/Kibana-%236674a4.svg?style=for-the-badge&logo=kibana&logoColor=white) ![Logstash](https://img.shields.io/badge/Logstash-%234A138A.svg?style=for-the-badge&logo=logstash&logoColor=white) |
| Others | ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white) |

- # 사용 버전
### JAVA : 11

### SpringBoot FrameWork : 2.7.14-SNAPSHOT

### node v14.21.3

### npm v6.14.18

### react@18.2.0

### Elasticsearch-7.17.10

### kibana-7.17.10

### logstash-7.17.10

# 담당파트

- ##  프로젝트팀장
- ## 프로모션
- ## 구매
- ## 포인트관리
- ## 게시판페이지의 DB정보를 MySql이 아닌 ElasticSearch 를 사용하여 정보 가져오기 
<br/>



# 프로모션 진행
![프로모션](https://github.com/gydn123/SpringProject/assets/121388591/ed892744-537f-4f9b-8f0c-fb392b17b4d5)
- 진행하고있는 프로모션들을 볼 수 있는 페이지
# 프로모션가격
![프로모션가격](https://github.com/gydn123/SpringProject/assets/121388591/8c8fd887-d8f1-4f91-9e96-14588834dfd7)
- 해당 프로모션을 클릭하면 행사내용과 할인된 가격을 확인
# 로그인유효성검사
![로그인유효성검사](https://github.com/gydn123/SpringProject/assets/121388591/b48b09e5-6ec2-4481-ba78-fec45685a9ae)
- 자바스크립트로 session에 로그인되어있는 사용자가 구매를 하는지 확인 비회원은 구매 불가능
# 포인트유효성검사
![포인트유효성검사](https://github.com/gydn123/SpringProject/assets/121388591/06bc5ec9-e0b9-4f54-9cce-9ff8c04719ab)
- 0보다 작거나 보유포인트 이상은 사용 불가능
# 구매
![구매](https://github.com/gydn123/SpringProject/assets/121388591/6ec74495-c131-4109-93f8-6a2b767bb540)
- 티켓마다 수량, 가격이 상이해 각각의 고유 값들을 json타입으로 값 전달
# 구매내역
![구매내역](https://github.com/gydn123/SpringProject/assets/121388591/0f33cdd7-6389-4379-bf84-f8fdd29a651d)
- 지금까지 구매,환불을 했던 이력확인가능
# 구매확정
![구매확정](https://github.com/gydn123/SpringProject/assets/121388591/dab9aaac-4df3-4369-b53f-66d7d93d7758)
- 구매확정을 통해 포인트사용 및 적립
# 환불
![환불](https://github.com/gydn123/SpringProject/assets/121388591/60043ceb-d0ad-4009-9246-8b07a0eccb82)
- 환불을 하게되면 사용포인트 취소 구매내역 취소
# 포인트내역
![포인트내역](https://github.com/gydn123/SpringProject/assets/121388591/c297c66b-126a-48c5-a46e-14fb8a841e3e)
- 총 사용가능한 포인트와 적립,사용이력을 확인

# 프로모션,구매,포인트의 개체관계도
![erd](https://github.com/gydn123/SpringProject/assets/121388591/78b06443-a065-4952-8644-9d751e57cd25)

- 프로모션과 티켓가격은 해당 프로모션이 끝나면 정상가격으로 반영되어야 하기때문에 2개의 테이블로 나눔
- 오더 테이블엔 사용한 포인트와 구매확정,환불을 가려내는 컬럼으로 확인 orders_detail 테이블엔 해당 티켓의 교유 id값과 수량을 확인
- 그리고 구매확정이나 환불에 true값이 전달이되면 mypoint에 적립 유무 결정 (ex. checkorder가 ture값이면 적립)
- delete문 사용 없이 insert 와 update문만 사용을해서 누락없이 모든 구매내역의 기록을 확인가능
