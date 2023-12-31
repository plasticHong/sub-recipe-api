# sub-recipe

domain
* https://sub-recipe.com

서비스 개요

* 개인의 서브웨이 레시피를 공유 할 수 있는 서비스 입니다.

기능

* 해당 서비스에 회원가입, 로그인(kakao)을 할 수 있습니다.
* 본인의 서브웨이 레시피를 작성, 저장할 수 있습니다.
* 각종 요소( 영양 정보, 먹지않는 야채) 등으로 필터링, 정렬하여 등록된 레시피를 검색 할 수 있습니다.
* 등록된 레시피에 평가를 내릴 수 있습니다. ( 추천 / 비추천 )
* 레시피를 즐겨찾기에 등록하고 열람 할 수 있습니다.
* 많은 추천/비추천 을 받은 유저의 랭킹을 확인 할 수 있습니다.

## 참여
* Server  
  * plasticHong
* client
  * frontEnd : emop99 https://github.com/emop99 
  * publishing : (markUp) lim072 https://github.com/lim072

## 개발 환경 (Server)
* Application
  * Spring boot 3.1.3
  * Java 17
  * JPA / QueryDSL
* DB
  * AWS RDS (free tier)
  * MySql 8
* OS
  * AWS EC2 (free tier) 
  * Ubuntu 22.04.3 LTS
* Storage
  * AWS S3
* 도메인
  * AWS Route53

## 개발 환경 (Front)
* repository : https://github.com/emop99/subway-front

## table diagram
![diagram](https://github.com/plasticHong/sub-recipe-api/assets/101045399/c2dd0f79-8339-417e-9bc0-ab0385e94a81)




