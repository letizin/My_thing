package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cart.CartItemVO;
import cart.CartService;
import cart.CartServiceImpl;
import cart.HashMapCartDAO;
import dising.DisingService;
import dising.DisingVO;
import dising.NJDisingService;
import dising.file.ObjFileHashMapDisingDAO;
import member.HJMemberService;
import member.MemberService;
import member.MemberVO;
import member.ObjFileHashMapMemberDAO;
import order.ObjFileHashMapOrderDAO;
import order.OrderItemVO;
import order.OrderService;
import order.OrderServiceImpl;
import order.OrderVO;

// 회원정보 수정
// 회원가입 수정
// 디자인 추가

public class MyThingConsoleApp {

	
	String[] category = {"로그아웃", "디자인", "장바구니", "디자인 담기", "주문", "내정보"};
	
	String[] disingMenu = {"뒤로가기", "디자인 목록", "디자인 등록", "디자인 정보 수정", "디자인 삭제"};
	String[] startMenuList = {"종료", "디자인 목록", "로그인", "회원 가입"};
	String[] adminMenuList = {"로그아웃", "디자인 목록", "디자인 등록", "디자인 정보 수정", "디자인 삭제", "회원 목록", "주문 목록"};
	String[] memberMenuList = {"로그아웃", "디자인 목록", "디자인 주문", "주문 목록", "디자인 담기", "장바구니 보기", "내 정보"};
	String[] cartMenuList = {"돌아가기", "디자인 주문", "디자인 삭제", "장바구니 비우기"};
	String[] myInfoMenuList = {"돌아가기", "비밀번호 변경", "회원 탈퇴"};
	
	final String ADMIN_ID = "admin";
	final String ADMIN_PWD = "1234";
	final String ADMIN_NAME = "관리자";
	
	final String CONFIRM = "yes";

	DisingService ds = new NJDisingService(new ObjFileHashMapDisingDAO());
	MemberService ms = new HJMemberService(new ObjFileHashMapMemberDAO());
	OrderService os = new OrderServiceImpl(new ObjFileHashMapOrderDAO(), ds);
	CartService cs = new CartServiceImpl(new HashMapCartDAO());
	MemberVO loggedMember;

	MyAppReader input = new MyAppReader();
	DisingMenu dising = new DisingMenu();
	
	public static void main(String[] args) {
		MyThingConsoleApp app = new MyThingConsoleApp();
		app.displayWelcome();
		app.controlStartMenu();
	}

	private void displayWelcome() {
		System.out.println("***********************************");
		System.out.println("*            MY THINK             *");
		System.out.println("***********************************");
	}

	// 시작 메뉴
	private void controlStartMenu() {
		int menu;
		do {
			menu = selectMenu(startMenuList);
			
			switch (menu) {
			case 1: dising.DisingMenuView(); break;
			case 2: menuLogin(); break;
			case 3: menuSignUp(); break;
			case 0: menuExit(); break;
			default : menuWrongNumber();
			}
			
		} while (menu != 0);
		
	}

	// 로그인 메뉴(사용자)
	public void controlMemberMenu() {
		int menu;
		do {
			menu = selectMenu(category);
			switch (menu) {
				case 1 : disingMenu(); break;
				case 2 : menuCartView(); break;
				case 3 : menuAddDising2Cart(); break;
				case 4 : menuOrderList(); break;
				case 5 : menuMyInfo(); break;
				case 0 : menuLogout(); break;
				default : menuWrongNumber();
			}
		} while (menu != 0);

	}
	
	// 디자인 메뉴(사용자 전용)
	private void disingMenu() {
		int menuNum;
		do {
			menuNum = selectMenu(disingMenu);
			
			switch (menuNum) {
				case 1: dising.DisingMenuView(); break;
				case 2: dising.DisingRegist(); break;
				case 3: dising.DisingUpdate(); break;
				case 4: dising.DisingRemove(); break;
				case 0: controlMemberMenu(); break;
				default : menuWrongNumber();
			}
		}while(menuNum != 0);
	}

	
	// 로그인 화면
	private void menuLogin() {
		System.out.println("*** 로그인 ***");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");
		
		// 관리자 -> 관리자 메뉴
		if (id.equals(ADMIN_ID) && password.equals(ADMIN_PWD)) {
			loggedMember = new MemberVO(ADMIN_ID, ADMIN_PWD, ADMIN_NAME);
			System.out.println("관리자 모드로 변경합니다.");
			dising = new DisingMenu(loggedMember);
			controlAdminMenu();
		} else {
			// 회원 -> 회원 메뉴
			loggedMember = ms.login(id, password);
			
			if (loggedMember != null) {
				System.out.println("[로그인] " + loggedMember.getUsername() + "님 안녕하세요.");
				dising = new DisingMenu(loggedMember);
				controlMemberMenu();
			} else {
				// 아니면
				System.out.println("로그인을 하지 못했습니다.");
			}
		}
		
	}

	
	// 디자인 주문
	private void menuBookOrder() {
		System.out.println("*** 디자인 주문 ***");
		displayAvailableDisingList();
		String bookNm = input.readString(">> 디자인 이름 : ");
		DisingVO dising = ds.detailDisingInfo(bookNm);
		
		if (dising == null) {
			System.out.println("없는 디자인 입니다.");
			return;
		}else {
			
		}
	}
	
	// 배송정보가 없는경우 추가 작성
	private void setDeliveryInfo() {
		if (loggedMember.getMobile() == null) {
			System.out.println("*** 배송 정보 입력 ***");
			
			String mobile = input.readString(">> 모바일 번호 : ");
			String email = input.readString(">> 이메일 주소 : ");
			String address = input.readString(">> 주소 : ");
			
			loggedMember.setMobile(mobile);
			loggedMember.setEmail(email);
			loggedMember.setAddress(address);
			
			ms.addMemberInfo(loggedMember.getId(), mobile, email, address);
			//loggedMember = ms.detailMemberInfo(loggedMember.getId());
			
		}
	}
	
	
	// 주문 가능한 디자인 있으면 ㄱ
	private void displayAvailableDisingList() {
		List<DisingVO> disingList = ds.listDising();
		System.out.println("---------------------------------------");
		if (disingList.isEmpty()) {
			System.out.println("주문 가능한 디자인이 없습니다.");
		} else {
			int count = 0;
			for (DisingVO dising : disingList) {
				if (dising.isShare()) {
					System.out.println(dising);
					count++;
				}
			}
			if (count == 0) 
				System.out.println("주문 가능한 디자인가 없습니다.");
		}
		System.out.println("---------------------------------------");	
		
	}

	// 장바구니에 디자인 담기
	private void menuAddDising2Cart() {
		System.out.println("*** 장바구니에 디자인 담기 ***");
		
		displayAvailableDisingList();
		String disingNm = input.readString(">> 디자인 이름 : ");
		DisingVO dising = ds.detailDisingInfo(disingNm);
		
		if (dising == null) {
			System.out.println("없는 디자인 입니다.");
			return;
		}
		
		DisingVO dsSet = ds.detailDisingInfo(disingNm);
		int dsNo = dsSet.getDisingNo();
		
		// 이미 장바구니에 있는지 확인
		// 없으면, 장바구니에 넣기
		if (cs.getCartItemInfo(disingNm) == null) {
			cs.addItem2Cart(new CartItemVO(dsNo ,disingNm, 1));
			System.out.println("장바구니에 추가했습니다.");
		} else {
			System.out.println("이미 장바구니에 있는 도서입니다.");
		}
		
		
	}

	
	// 장바구니 내용 보기
	private void menuCartView() {
		System.out.println("*** 장바구니 보기 ***");
		displayCartItemList();
		
		if (!cs.isCartEmpty())controlCartMenu();
		
	}

	
	// 장바구니 아이템 노출
	private void displayCartItemList() {	
		if (cs.isCartEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
		} else {
			System.out.println("---------------------------------------");	
			for (CartItemVO item : cs.listCartItems()) {
				System.out.println(item);
			}
			System.out.println("---------------------------------------");
		}
	}

	// 장바구니 메뉴 노출
	private void controlCartMenu() {
		int menu;
		do {
			menu = selectMenu(cartMenuList);
			switch (menu) {
				case 1 : menuCartOrder(); break;
				case 2 : menuCartDisingDelete(); break;
				case 3 : menuCartClear();
				case 0 : break;
				default : menuWrongNumber();
			}
		} while (menu != 0 && !cs.isCartEmpty());	
	}

	
	// 장바구니에 있는 디자인 주문
	private void menuCartOrder() {
		System.out.println("*** 장바구니 도서 주문 ***");
		displayCartItemList();
		
		// 주문 디자인 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int totalPrice = 0;
		for (CartItemVO item : cs.listCartItems()) {
			DisingVO dising = ds.detailDisingInfo(item.getDisingNm());
			totalPrice += 6000;
			orderItemList.add(
				new OrderItemVO(item.getDisingNo(), item.getQuantity(), 6000)
			);
		}
						
		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedMember.getId(), orderItemList, totalPrice);
		
		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedMember.getMobile());
		order.setAddress(loggedMember.getAddress());
		
		displayOrderInfo(order);
		
		String confirm = input.readString(">> 위와 같은 내용을 주문 및 결제를 진행하시겠습니까? ('" 
									+ CONFIRM + "'이면 주문 실행) : " );
		if (confirm.equals(CONFIRM)) {
			if (os.orderItems(order)) {
				cs.clearCart();
				System.out.println("주문이 완료되었습니다.");
				System.out.println("배송이 완료되었습니다.");
				
			} else {
				System.out.println("주문을 하지 못했습니다.");
			}
		} else {
			System.out.println("주문이 취소되었습니다.");
		}
		
		
	}

	// 디자인 주문 내용
	private void displayOrderInfo(OrderVO order) {
		System.out.println(order);
	}

	
	// 장바구니 내용 삭제
	private void menuCartDisingDelete() {
		System.out.println("*** 장바구니 디자인 삭제 ***");
		displayCartItemList();
		String disingNm = input.readString(">> 디자인 이름 :");
		CartItemVO item = cs.getCartItemInfo(disingNm);
		DisingVO setD = ds.detailDisingInfo(disingNm);
		if (item == null) {
			System.out.println("없는 디자인입니다.");
		} else {
			cs.removeCartItem(setD.getDisingNo());
			System.out.println("장바구니에서 디자인를 삭제하였습니다.");
		}
		displayCartItemList();
	}

	
	// 장바구니 비우기(전체 삭제)
	private void menuCartClear() {
		System.out.println("*** 장바구니 비우기 ***");
		String confirm = input.readString(">> 장바구니의 모든 디자인를 삭제하시겠습니까? ('" + CONFIRM + "'이면 삭제) : ");
		if (confirm.equals(CONFIRM)) {
			cs.clearCart();
			System.out.println("장바구니의 모든 디자인를 삭제하였습니다.");
		} else {
			System.out.println("장바구니 비우기가 취소되었습니다.");
		}
		
	}

	// 내정보
	private void menuMyInfo() {
		System.out.println("*** 내 정보 ***");
		System.out.println(loggedMember);
		
		controlMyInfoMenu();
	}

	
	// 회원 정보 변경
	private void controlMyInfoMenu() {
		int menu;
		do {
			menu = selectMenu(myInfoMenuList);
			// "돌아가기", "비밀번호 변경", "회원 탈퇴"
			switch (menu) {
			case 1 : menuUpatePassword(); break;
			case 2 : menuMemberExit(); break;
			case 0 : break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && loggedMember != null);
		
	}

	
	// 회원 정보 수정
	private void menuUpatePassword() {
		System.out.println("*** 비밀번호 수정 ***");
		String oldPassword = input.readString(">> 기존 비밀번호 : ");
		String newPassword = input.readString(">> 새 비밀번호 : ");
		if (ms.updatePassword(loggedMember.getId(), oldPassword, newPassword)) {
			System.out.println("[비밀번호 수정] 비밀번호를 수정했습니다.");
		} else {
			System.out.println("[비밀번호 수정 실패] 비밀번호 수정에 실패했습니다.");
		}
	}

	
	// 회원 탈퇴
	private void menuMemberExit() {
		System.out.println("*** 회원 탈퇴 ***");
		String password = input.readString(">> 비밀번호 : ");
		if (ms.removeMember(loggedMember.getId(), password)) {
			System.out.println("[회원 탈퇴] 회원정보, 주문정보를 삭제하였습니다. 그동안 My Think을 이용해주셔서 감사합니다.");
			loggedMember = null;
		} else {
			System.out.println("[회원 탈퇴 실패] 회원 탈퇴 처리에 실패했습니다.");
		}
		
	}

	
	// 어드민 메뉴
	public void controlAdminMenu() {
		int menu;
		do {
			menu = selectMenu(adminMenuList);
			switch (menu) {
			case 1: dising.DisingMenuView(); break;
			case 2: dising.DisingRegist(); break;
			case 3: dising.DisingUpdate(); break;
			case 4: dising.DisingRemove(); break;
			case 5: menuMemberList(); break;
			case 6: menuOrderList(); break;
			case 0: menuLogout(); break;
			default : menuWrongNumber();
			}
			
		} while (menu != 0 && loggedMember != null);
		
	}

	
	// 회원 목록 노출
	private void menuMemberList() {
		System.out.println("*** 회원 목록 ***");
		System.out.println("---------------------------------------");
		List<MemberVO> memberList = ms.listMembers();
		if (memberList.isEmpty()) {
			System.out.println("회원이 없습니다.");
		} else {
			for (MemberVO member : memberList) {
				System.out.println(member);
			}
		}
		System.out.println("---------------------------------------");
		
	}

	
	// 주문 내역 보기(어드민
	private void menuOrderList() {
		if (loggedMember.getId().equals(ADMIN_ID)) {
			System.out.println(os.listAllOrder());
		} else {
			System.out.println(os.listMyOrders(loggedMember.getId()));
		}
		
	}

	// 로그아웃
	private void menuLogout() {
		System.out.println("[로그아웃] " + loggedMember.getUsername() + "님, 안녕히 가십시오.");
		loggedMember = null;
		
	}

	// 회원 가입
	private void menuSignUp() {
		System.out.println("*** 회원 가입 ***");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");
		String username = input.readString(">> 이름 : ");
		int plusMember = input.readInt(">> 추가 정보를 입력하시겠습니까? (1, yes  2, no) : ");
		if(plusMember == 1) {
			String address = input.readString(">> 주소 : ");
			String phone = input.readString(">> 휴대폰 번호 : ");
			String email = input.readString(">> 이메일 : ");
			if (ms.registMember(new MemberVO(id, password, username, phone, email, address, new Date()))) {
				System.out.println("회원 가입(추가) 이 완료되었습니다. 서비스 이용을 위한 로그인 해주세요.");
			} else {
				System.out.println("회원 가입에 실패하였습니다.");
			}
		}else {
			if (ms.registMember(new MemberVO(id, password, username))) {
				System.out.println("회원 가입(기본) 이 완료되었습니다. 서비스 이용을 위한 로그인 해주세요.");
			} else {
				System.out.println("회원 가입에 실패하였습니다.");
			}
		}
		
		
	}

	
	// 메뉴 노출(거의 다 씀)
	private int selectMenu(String[] menuList) {

		System.out.println("-------------------------------");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(i + ". " + menuList[i]);
		}
		System.out.println("0. " + menuList[0]);
		System.out.println("-------------------------------");
		return input.readInt(">> 메뉴 선택 : ");
	}
	
	// 메뉴가 없는 경우
	private void menuWrongNumber() {
		System.out.println("없는 메뉴입니다.");
		
	}

	// 나가기
	private void menuExit() {
		System.out.println("MY THINK 서비스를 종료합니다.");
		
	}
}
