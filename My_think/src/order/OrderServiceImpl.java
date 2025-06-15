package order;

import java.util.Date;
import java.util.List;

import dising.DisingService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDAO orderDAO;
	private DisingService bookService;
	
	private final int COMPLETE = 10;
	
	public OrderServiceImpl(OrderDAO orderDAO, DisingService bookService) {
		this.orderDAO = orderDAO;
		this.bookService = bookService;
	}

	@Override
	public boolean orderItems(OrderVO order) {

		// 1. 주문 정보 추가 (주문일, 배송상태, 배송완료일)
		order.setOrderDate(new Date());
		order.setStatus(COMPLETE);
		order.setDeliverDate(new Date());
		
		// 3. 주문 정보 DB에 추가
		orderDAO.insertOrder(order);		
		return true;
	}

	@Override
	public List<OrderVO> listMyOrders(String memberId) {
		return orderDAO.selectOrdersOfMember(memberId);
	}

	@Override
	public List<OrderVO> listAllOrder() {
		return orderDAO.selectAllOrder();
	}

}
