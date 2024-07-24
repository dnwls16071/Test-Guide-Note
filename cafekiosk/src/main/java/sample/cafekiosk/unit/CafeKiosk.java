package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

	private final List<Beverage> beverages = new ArrayList<>();

	public void add(Beverage beverage) {
		beverages.add(beverage);
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
