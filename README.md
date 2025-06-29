---

# My_thing Console Application

이 프로젝트는 사용자가 직접 의류 디자인을 선택하고 커스터마이징할 수 있는 커스텀 의류 관리 프로그램입니다.

의류 아이템 등록, 디자인 옵션 선택, 주문 및 장바구니 기능 등을 통해 맞춤형 쇼핑 경험을 제공합니다.

객체지향 설계를 기반으로 유지보수성과 확장성을 고려하여 구현되었습니다.

## 주요 기능

+ 디자인 공유 선택 : 제작자가 공유를 원치 않으면 리스트에서 노출되지 않게 할수 있습니다
+ 회원 정보 변경 : 회원 대부분의 내용을 변경할수 있습니다
+ 관리자 기능 : 회원 목록, 전체 주문 내역을 확인할수 있습니다

## 개발 환경

+ 연어 : java
+ 데이터 저장 : 파일 시스템을 이용한 객체 직렬화
+ 주요 패턴 : DAO 패턴을 적용하여 데이터 로직 분석

## 프로젝트 구조

<pre>
:포장: src
 ┣ :파일_폴더: app
 ┃ ┣ :글씨가_쓰여진_페이지: MyThinkApp.java
 ┃ ┗ :글씨가_쓰여진_페이지: DesignApp.java
 ┣ :파일_폴더: design
 ┃ ┣ :글씨가_쓰여진_페이지: DesignDAO.java
 ┃ ┣ :글씨가_쓰여진_페이지: DesignVO.java
 ┃ ┣ :글씨가_쓰여진_페이지: DesignService.java
 ┃ ┣ :글씨가_쓰여진_페이지: HashMapDesignDAO.java
 ┃ ┣ :글씨가_쓰여진_페이지: NJDesignService.java
 ┃ ┣ :글씨가_쓰여진_페이지: ListDesignDAO.java
 ┃ ┗ :파일_폴더: file
 ┃   ┣ :글씨가_쓰여진_페이지: FileDesignDB.java
 ┃   ┣ :글씨가_쓰여진_페이지: ObjFileHashMapDesignDAO.java
 ┃   ┗ :글씨가_쓰여진_페이지: TextFileHashMapDesignDAO.java
 ┣ :파일_폴더: member
 ┃ ┣ :글씨가_쓰여진_페이지: FileMemberDB.java
 ┃ ┣ :글씨가_쓰여진_페이지: HashMapMemberDAO.java
 ┃ ┣ :글씨가_쓰여진_페이지: NJMemberService.java
 ┃ ┣ :글씨가_쓰여진_페이지: MemberVO.java
 ┃ ┣ :글씨가_쓰여진_페이지: MemberService.java
 ┃ ┣ :글씨가_쓰여진_페이지: ObjFileHashMapMemberDAO.java 
 ┃ ┣ :글씨가_쓰여진_페이지: MemberDAO.java
 ┣ :파일_폴더: order
 ┃ ┣ :글씨가_쓰여진_페이지: ObjFileHashMapOrderDAO.java
 ┃ ┣ :글씨가_쓰여진_페이지: OrderItemVO.java
 ┃ ┣ :글씨가_쓰여진_페이지: OrderDAO.java  
 ┃ ┣ :글씨가_쓰여진_페이지: OrderService.java
 ┃ ┣ :글씨가_쓰여진_페이지: OrderServiceImpl.java
 ┃ ┣ :글씨가_쓰여진_페이지: OrderVO.java
 ┣ :파일_폴더: cart
 ┃ ┣ :글씨가_쓰여진_페이지: CartItemVO.java
 ┃ ┣ :글씨가_쓰여진_페이지: CartDAO.java  
 ┃ ┣ :글씨가_쓰여진_페이지: CartService.java
 ┃ ┣ :글씨가_쓰여진_페이지: CartServiceImpl.java
 ┃ ┣ :글씨가_쓰여진_페이지: CartVO.java
</pre>


## 요구사항 명세서
![요구사항 명세서](https://github.com/letizin/My_thing/blob/main/RequestForProposal.png)
## 클래스 다이어그램
![클래스 이미지](https://github.com/letizin/My_thing/blob/main/class.PNG)
## 액티비티 다이어그램
![액티비티 이미지](https://github.com/letizin/My_thing/blob/main/Activity.png)
## 코드리뷰
![코드리뷰](https://github.com/letizin/My_thing/blob/main/%EC%BD%94%EB%93%9C%EB%A6%AC%EB%B7%B0.png)

## 시연 영상
### 로그인 시연
[시연 영상](https://youtube.com/shorts/mSHIyNiM_FM?feature=share)
### 디자인 시연
[시연 영상](https://youtube.com/shorts/-7b1N9T1HbM)
### 장바구니, 주문 시연
[시연 영상](https://youtube.com/shorts/6u1XazNfS2Y)
### 회원 정보 변경 시연
[시연 영상](https://www.youtube.com/watch?v=d3BoblHgva0)
### 어드민 시연
[시연 영상](https://youtube.com/shorts/fCTIHTdQ1ZQ)





