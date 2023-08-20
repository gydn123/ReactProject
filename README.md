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

- ## 프로젝트팀장
- ## 프로모션
- ## 구매
- ## 포인트관리
- ## 게시판페이지의 DB정보를 MySql이 아닌 ElasticSearch 를 사용하여 정보 가져오기 
<br/>

# 프로모션,구매,포인트의 개체관계도
![erd](https://github.com/gydn123/SpringProject/assets/121388591/78b06443-a065-4952-8644-9d751e57cd25)

- 프로모션과 티켓가격은 해당 프로모션이 끝나면 정상가격으로 반영되어야 하기때문에 2개의 테이블로 나눔
- 오더 테이블엔 사용한 포인트와 구매확정,환불을 가려내는 컬럼으로 확인 orders_detail 테이블엔 해당 티켓의 교유 id값과 수량을 확인
- 그리고 구매확정이나 환불에 true값이 전달이되면 mypoint에 적립 유무 결정 (ex. checkorder가 ture값이면 적립)
- delete문 사용 없이 insert 와 update문만 사용을해서 누락없이 모든 구매내역의 기록을 확인가능

# 실행내용은 기존의 스프링 프로젝트와 동일

<br />

# 힘들었던점

- 기존의 스프링기반의 프로젝트를 스프링부트와 리액트로 프론트,백을 분리하는 작업이 생각보다 만만치않았고
- 4명의 팀원은 리액트를 적용시키고 나는 엘라스틱서치를 사용해서 게시판페이지의 DB정보를 조회하는 업무를 맡았다.
- 왜냐하면 엘라스틱서치는 인덱스 형식이기 때문에 기존의 RDBMS보다 빠르고 다른 페이지에 비해 실시간으로 업데이트되는 정보가 많기 때문에 엘라스틱서치를 사용해보는걸 채택하였다.
- 그리고 스프링부트에서 모델과 컨트롤러가 작동하는 값들을 리액트로 받아오는 과정에서 cors 에러가 자주등장했는데
- 이 에러를 처음 접했을때 많은 어려움이 있었지만 생각보다 쉽게 해결되었고, 우리들을 괴롭힌건 컴포넌트형식을 처음 접한다는것 이었다.
- html 코드를 자바스크립트 형식에서 리턴을 하고 css 코드도 리액트가 작동을 하면서 App.js에서 루트에 연결된 모든 컴포넌트들이 실행되다보니
- css충돌이 잦았고 이를 해결하기위해 클래스네임, 전역변수명 등을 전체적으로 손봐야했고
- 5명이라 서로의 스타일이 다르지만 결국은 같은 프로젝트를 진행하는거기 때문에
- 클래스명,id명, 전역변수를 사용한다면 변수명까지 서로 겹치지않고 고유한 이름들을 사용해야
- 나중에 유지보수하기 편해지고 충돌이 일어날일이 적어지기 때문에 이를 보완해야겠다는걸 깨달았다.


