package sample.cafekiosk.dto;

import lombok.Getter;
import lombok.Setter;
import sample.cafekiosk.domain.Product;
import sample.cafekiosk.enumType.ProductSellingStatus;
import sample.cafekiosk.enumType.ProductType;

@Getter
@Setter
public class ProductResponseDTO {
	private Long id;
	private String productNumber;
	private ProductType productType;
	private ProductSellingStatus productSellingStatus;
	private String name;
	private int price;

	public ProductResponseDTO(Product product) {
		this.productNumber = product.getProductNumber();
		this.productType = product.getProductType();
		this.productSellingStatus = product.getProductSellingStatus();
		this.name = product.getName();
		this.price = product.getPrice();
	}
}
