package dising;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisingVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int DisingNo;
	private String DisingName;
	private String CreateUser;
	private boolean Share;
	private Date Regdate;
	
	public DisingVO(int DisingNo, String DisingName, String CreateUser, boolean Share, Date Regdate) {
		this.DisingNo = DisingNo;
		this.DisingName = DisingName;
		this.CreateUser = CreateUser;
		this.Share = Share;
		this.Regdate = Regdate;
	}

	public DisingVO(String DisingName, String CreateUser, boolean Share) {
		this(-1, DisingName, CreateUser, Share, new Date());
	}
	
	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일"); 
    	String strNowDate = simpleDateFormat.format(Regdate); 
    	
		
		return "이름 : " + DisingName + " | 제작자 : " + CreateUser
				+ " | 만든 날짜 : " + strNowDate + "\n";
	}

	public int getDisingNo() {
		return DisingNo;
	}

	public void setDisingNo(int disingNo) {
		DisingNo = disingNo;
	}

	public String getDisingName() {
		return DisingName;
	}

	public void setDisingName(String disingName) {
		DisingName = disingName;
	}

	public String getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(String createUser) {
		CreateUser = createUser;
	}

	public boolean isShare() {
		return Share;
	}

	public void setShare(boolean share) {
		Share = share;
	}

	public Date getRegdate() {
		return Regdate;
	}

	public void setRegdate(Date regdate) {
		Regdate = regdate;
	}
	
	
}
