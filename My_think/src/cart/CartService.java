package cart;

import java.util.List;

public interface CartService {

	boolean addItem2Cart(CartItemVO item);
	CartItemVO getCartItemInfo(String disingNm);
	List<CartItemVO> listCartItems();
	boolean isCartEmpty();
	boolean removeCartItem(int bookNo);
	boolean clearCart();
}
