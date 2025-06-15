package cart;

public class CartItemVO {

	private int DisingNo;
	private String DisingNm;
	private int quantity;
	
	public CartItemVO(int DisingNo, String DisingNm, int quantity) {
		this.DisingNo = DisingNo;
		this.DisingNm = DisingNm;
		this.quantity = quantity;
	}

	public void setDisingNo(int disingNo) {
		DisingNo = disingNo;
	}
	
	public int getDisingNo() {
		return DisingNo;
	}
	
	public String getDisingNm() {
		return DisingNm;
	}

	public void setDisingNm(String DisingNm) {
		this.DisingNm = DisingNm;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "[" + DisingNm + "]";
	}
	
	
}
