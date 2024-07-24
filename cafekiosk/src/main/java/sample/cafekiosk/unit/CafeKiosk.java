package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

	private final List<Beverage> beverages = new ArrayList<>();

	public void add(Beverage beverage, int amount) {
		if (amount <= 0) {
			throw new IllegalStateException("음료는 최소 한 잔 이상 주문해야합니다!");
		}
		for (int i=0; i<amount; i++) {
			beverages.add(beverage);	// 하나의 음료를 여러 개 넣을 수 있다는 요구사항
		}
	}

	public int calculateTotalPrice() {
		int total = 0;
		for (Beverage beverage : beverages) {
			total += beverage.getPrice();
		}
		return total;
	}

	public void remove(Beverage beverage) {
		beverages.remove(beverage);
	}

	public void clear() {
		beverages.clear();
	}

	public Order createOrder() {
		return new Order(LocalDateTime.now(), beverages);
	}
}
