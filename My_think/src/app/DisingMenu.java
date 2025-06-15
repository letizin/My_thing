package app;

import java.util.List;
import java.util.Scanner;

import dising.DisingService;
import dising.DisingVO;
import dising.NJDisingService;
import dising.file.ObjFileHashMapDisingDAO;
import member.MemberVO;

public class DisingMenu {
	DisingService ds = new NJDisingService(new ObjFileHashMapDisingDAO());

	final String CONFIRM = "yes";
	
	MemberVO loggedMember;

	MyAppReader input = new MyAppReader();
	Scanner sc = new Scanner(System.in);
	
	// 유저 정보 땡기기
	public DisingMenu(MemberVO user) {
		loggedMember = user;
	}
	
	public DisingMenu() {
	}
	
	public void DisingMenuView() {
		System.out.println("*** 디자인 목록 ***");
		displayDisingList();
		if (loggedMember != null) {
			System.out.println("-------------------------------");
			System.out.println("1 : 디자인 검색");
			System.out.println("2 : 뒤로 가기");
			System.out.println("-------------------------------");
			int setMenu = input.readInt(">> 메뉴 선택 : ");
			if(setMenu == 1) {
				String setNm = input.readString("디자인 이름 : ");
				DisingVO dsvo = ds.detailDisingInfo(setNm);
				System.out.println(dsvo.toString());
			}else {
				
			}
		}
	}
	
	private void displayDisingList() {
		List<DisingVO> disingList = ds.listDising();
		System.out.println("---------------------------------------");
		if (disingList.isEmpty()) {
			System.out.println("등록된 디자인이 없습니다.");
		} else {
			for (DisingVO ds: disingList) {
				if(ds.isShare() == true) {
					System.out.println(ds);					
				}
			}
		}
		System.out.println("---------------------------------------");	
	}
	
	public boolean displayDisingToUserList() {
		List<DisingVO> disingList = ds.listDising();
		boolean Viewdisplay = false;
		for(DisingVO ds : disingList) {
			if(ds.getCreateUser().equals(loggedMember.getUsername())) {
				System.out.println("온");
				Viewdisplay = true;
			}
		}
		System.out.println("---------------------------------------");
		if (!Viewdisplay) {
			System.out.println("등록된 디자인이 없습니다.");
		} else {
			for (DisingVO ds: disingList) {
				if(ds.getCreateUser().equals(loggedMember.getUsername())) {
					if(ds.isShare() == true) {
						System.out.println(ds);		
					}
				}
			}
		}
		System.out.println("---------------------------------------");	
		return Viewdisplay;
	}
	
	public void DisingRegist() {
		
		System.out.println("*** 디자인 등록 ***");
		String title = input.readString(">> 디자인 이름 : ");
		String publisher = input.readString(">> 공유(1 : 공유, 2 : 비공유) : ");
		boolean share;
		if(publisher.equals("1")) {
			share = true;
		}else {
			share = false;
		}
		if (ds.registDising(new DisingVO(title, loggedMember.getUsername(),share))) {
			System.out.println("디자인를 등록했습니다.");
			displayDisingList();
		} else {
			System.out.println("디자인 등록에 실패했습니다.");
		}
		
	}

	public void DisingUpdate() {
		System.out.println("*** 디자인 정보 수정 ***");
		if(displayDisingToUserList()) {
			String disingNm = input.readString(">> 디자인 이름 :");
			
			DisingVO findDis = ds.selectDising(disingNm, loggedMember.getUsername());
			if(findDis != null) {
				int select = input.readInt(">> 수정할 정보 선택 (1. 이름, 2. 공유정보) : ");
				if (select == 1) { // 이름
					String changeNm = input.readString(">> 변경 이름 : ");
					if(ds.updateDisingName(disingNm, changeNm, loggedMember.getUsername())) {
						System.out.println("[디자인 정보 수정] 이름을 수정하였습니다.");
					}else {
						System.out.println("[디자인 정보 수정 오류] 없는 디자인입니다.");
					}
				} else if (select == 2) {// 공유정보
					String changeDi = input.readString(">> 변경할 디자인 : ");
					int instock = input.readInt(">> 공유 사항 (1, yes | 2, no) :");
					boolean chSh;
					if(instock == 1) {
						chSh = true;
					}else {
						chSh = false;
					}
					if(ds.updateDisingShare(disingNm,chSh, loggedMember.getUsername())) {
						System.out.println("[디자인 정보 수정] 공유 사항을 수정하였습니다.");
					} else {
						System.out.println("[디자인 정보 수정 오류] 없는 도서입니다.");
					}
				} else {
					System.out.println("[디자인 정보 수정 취소] 지원하지 않는 기능입니다.");
				}
			}
		}
	}

	public void DisingRemove() {
		System.out.println("*** 디자인 삭제 ***");
		displayDisingToUserList();
		String disingNo = input.readString(">> 디자인 이름 :");
		String confirm = input.readString("선택한 디자인을 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
		if (confirm.equals(CONFIRM)) {
			if (ds.removeDising(disingNo, loggedMember.getUsername())) {
				System.out.println("[디자인 삭제] 디자인를 삭제했습니다.");
			} else {
				System.out.println("[디자인 삭제 오류] 없는 디자인입니다.");
			}
		} else {
			System.out.println("[디자인 삭제 취소] 디자인 삭제를 취소했습니다.");
		}
	}
}
