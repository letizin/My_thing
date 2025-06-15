package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dising.DisingVO;

public class HashMapCartDAO implements CartDAO {
	
	Map<Integer, CartItemVO> cartDB = new HashMap<>();

	@Override
	public boolean insertCartItem(CartItemVO item) {
		cartDB.put(item.getDisingNo(), item);
		return true;
	}

	@Override
	public CartItemVO selectCartItem(String disingNm) {
		CartItemVO setDi = null;
		for (Integer key : cartDB.keySet()) {
		    if(cartDB.get(key).getDisingNm().equals(disingNm)) {
		    	setDi = cartDB.get(key);
		    }
		}
		
	    if (setDi == null) {
	        
	        return null;
	    }

	    return setDi;
	}

	@Override
	public List<CartItemVO> selectAllCartItem() {
		return new ArrayList<>(cartDB.values());
	}

	@Override
	public boolean deleteCartItem(int bookNo) {
		return cartDB.remove(bookNo) != null;
	}

	@Override
	public boolean clear() {
		cartDB.clear();
		return true;
	}

}
