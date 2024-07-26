package sample.cafekiosk.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.baseentity.BaseEntity;
import sample.cafekiosk.enumType.ProductSellingStatus;
import sample.cafekiosk.enumType.ProductType;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRODUCTS")
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	private String productNumber;
	private String name;
	private int price;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@Enumerated(EnumType.STRING)
	private ProductSellingStatus productSellingStatus;

	@Builder
	private Product(String productNumber, String name, int price, ProductType productType, ProductSellingStatus productSellingStatus) {
		this.productNumber = productNumber;
		this.name = name;
		this.price = price;
		this.productType = productType;
		this.productSellingStatus = productSellingStatus;
	}
}
