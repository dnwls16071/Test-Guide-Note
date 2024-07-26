package sample.cafekiosk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.cafekiosk.domain.Product;
import sample.cafekiosk.enumType.ProductSellingStatus;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByProductSellingStatusIn(List<ProductSellingStatus> sellingTypes);
}
