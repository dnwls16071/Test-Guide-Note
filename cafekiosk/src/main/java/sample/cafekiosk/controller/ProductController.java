package sample.cafekiosk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.dto.ProductResponseDTO;
import sample.cafekiosk.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/api/v1/products/selling")
	public List<ProductResponseDTO> getSellingProducts() {
		return productService.getSellingProducts();
	}
}
