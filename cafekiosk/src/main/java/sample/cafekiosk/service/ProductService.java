package sample.cafekiosk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.cafekiosk.domain.Product;
import sample.cafekiosk.dto.ProductResponseDTO;
import sample.cafekiosk.enumType.ProductSellingStatus;
import sample.cafekiosk.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductResponseDTO> getSellingProducts() {
		List<Product> products = productRepository.findAllByProductSellingStatusIn(ProductSellingStatus.forDisplay());
		return products
				.stream()
				.map(ProductResponseDTO::new)
				.collect(Collectors.toList());
	}
}
