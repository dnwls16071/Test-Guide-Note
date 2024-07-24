package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.order.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CafeKioskTest {

	@Test
	void add_manual_test() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		cafeKiosk.add(new Americano(), 1);
		System.out.println(">>> 담긴 음료 수: " + cafeKiosk.getBeverages().size());
		System.out.println(">>> 담긴 음료: " + cafeKiosk.getBeverages().get(0).getName());
	}

	@Test
	@DisplayName("음료 1개 추가하면 주문 목록에 담겨야 한다.")
	void add() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		cafeKiosk.add(new Americano(), 1);

		assertThat(cafeKiosk.getBeverages()).hasSize(1);
		assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	@DisplayName("음료 1개 ")
	void remove() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano, 1);
		assertThat(cafeKiosk.getBeverages()).hasSize(1);

		cafeKiosk.remove(americano);
		assertThat(cafeKiosk.getBeverages()).isEmpty();
	}

	@Test
	void clear() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();
		Latte latte = new Latte();

		cafeKiosk.add(americano, 1);
		cafeKiosk.add(latte, 1);
		assertThat(cafeKiosk.getBeverages()).hasSize(2);

		cafeKiosk.clear();
		assertThat(cafeKiosk.getBeverages()).isEmpty();
	}

	@Test
	void addSeveralBeverage() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		// 해피 케이스
		cafeKiosk.add(americano, 2);

		assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
		assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
	}

	@Test
	void addZeroBeverages() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		/*
		assertThrows(IllegalStateException.class, () -> {
			cafeKiosk.add(americano, 0);
		});
		 */

		// 예외 케이스
		assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("음료는 최소 한 잔 이상 주문해야합니다!");
	}

	@Test
	void createOrder() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();

		cafeKiosk.add(americano);

		// 해피 케이스
		Order order1 = cafeKiosk.createOrder(LocalDateTime.of(2024, 7, 24, 13, 0));
		assertThat(order1.getBeverages()).hasSize(1);
		assertThat(order1.getBeverages().get(0).getName()).isEqualTo("아메리카노");

		// 예외 케이스
		assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024, 7, 24, 23, 50)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요!");
	}

	@Test
	void calculateTotalPrice() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();
		Latte latte = new Latte();

		cafeKiosk.add(americano);
		cafeKiosk.add(latte);

		int totalPrice = cafeKiosk.calculateTotalPrice();
		assertThat(totalPrice).isEqualTo(8500);
	}
}