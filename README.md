# ShoppingMall

### 소개
김영한님의 강의를 보고 Spring Boot와 JPA를 사용하여 만든 프로젝트입니다.
(2023년 1월 4주 ~ 2월 1주)

<br/>

## 프로젝트 주요 기능

+ 사용자 등록 
+ 로그인
+ 상품 등록 및 조회
+ 주문 및 배송 조회

<br/>

## 사용 기술

#### [백엔드]
**주요 프레임워크 / 라이브러리**
+ java 11
+ Spring boot
+ JPA & hibernate

**build tool**
+ Gradle

**DB** 
+ h2


#### [프론트엔드]
+ html/css
+ javascript
+ thymeleaf
+ bootstrap

<br/>

## 설계 화면

#### 1. 메인화면
![스크린샷 2023-03-25 오전 11 19 42](https://user-images.githubusercontent.com/107406119/227681748-55629f28-8e6e-4f33-927e-54f5705e3f97.png)

#### 2. 사용자 등록
![스크린샷 2023-03-25 오전 11 19 52](https://user-images.githubusercontent.com/107406119/227681750-c846747c-587a-4b43-a7d2-19e8df379a5a.png)

#### 3. 사용자 조회
![스크린샷 2023-03-25 오전 11 20 00](https://user-images.githubusercontent.com/107406119/227681751-3602ca89-d97e-48e4-99d3-30c412160e71.png)

#### 4. 상품 등록
![스크린샷 2023-03-25 오전 11 20 10](https://user-images.githubusercontent.com/107406119/227681752-511f6b06-b43a-42e9-834d-dec3b44e8c68.png)

#### 5. 상품 조회 및 수정
![스크린샷 2023-03-25 오전 11 20 19](https://user-images.githubusercontent.com/107406119/227681754-306f23cd-8612-4c83-b1e4-7a8a49cfb17b.png)

#### 6. 주문 
![스크린샷 2023-03-25 오전 11 20 28](https://user-images.githubusercontent.com/107406119/227681755-489e8dfa-5b4d-412a-ad34-41578e11efa1.png)

#### 7. 주문 조회 및 취소 (배송 현황)
![스크린샷 2023-03-25 오전 11 20 37](https://user-images.githubusercontent.com/107406119/227681756-7b26fcbf-4233-46c1-a76c-ca380ea29582.png)

<br/>

## 후기
이 프로젝트는 강의를 보면서 JPA를 사용하는 방법과 CRUD를 구현하는 방법을 배운 것이라 보안이나 기능에서 부족한 점이 많다.

개인적으로 스프링 mvc와 Mymatis만 사용하다가 스프링 부트와 JPA를 사용하려니 익숙하지 않고 불편했고

또한 JPA는 Mybatis와 다르게 엔티티를 만들어 직접 DB를 생성하다보니 고려할 점도 많았고 어려웠다.

<br/>

이 프로젝트를 시작으로 JPA, Querydsl의 기본기를 다져 보안이나 기능면에서 더 나은 프로젝트를 만들어 볼 것이다.



