package sample.cafekiosk.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.domain.Product;
import sample.cafekiosk.enumType.ProductSellingStatus;
import sample.cafekiosk.enumType.ProductType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

//@SpringBootTest	// 스프링 서버를 띄워서 테스트
@DataJpaTest		// JPA 관련 빈들만 주입해서 스프링 서버를 띄워서 테스트
@ActiveProfiles(profiles = "test")
class ProductRepositoryTest {

	@Autowired private ProductRepository productRepository;

	@Test
	@DisplayName("원하는 판매 상태를 가진 상품들을 조회한다.")
	void findAllBySellingStatusIn() {
		// given
		Product product1 = Product.builder()
				.productNumber("001")
				.productType(ProductType.HANDMADE)
				.productSellingStatus(ProductSellingStatus.SELLING)
				.name("아메리카노")
				.price(4000)
				.build();

		Product product2 = Product.builder()
				.productNumber("002")
				.productType(ProductType.HANDMADE)
				.productSellingStatus(ProductSellingStatus.HOLD)
				.name("카페라떼")
				.price(4500)
				.build();

		Product product3 = Product.builder()
				.productNumber("003")
				.productType(ProductType.HANDMADE)
				.productSellingStatus(ProductSellingStatus.STOP_SELLING)
				.name("팥빙수")
				.price(7000)
				.build();

		productRepository.saveAll(List.of(product1, product2, product3));

		// when
		List<Product> products = productRepository.findAllByProductSellingStatusIn(List.of(ProductSellingStatus.SELLING, ProductSellingStatus.HOLD));

		// then
		assertThat(products).hasSize(2)
				.extracting("productNumber", "name", "price", "productSellingStatus")
				.containsExactlyInAnyOrder(tuple("001", "아메리카노", 4000, ProductSellingStatus.SELLING),
										   tuple("002", "카페라떼", 4500, ProductSellingStatus.HOLD)
				);
	}
}