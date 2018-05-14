package de.toberkoe.examples.hibernate.validations;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.toberkoe.examples.hibernate.validations.EntityValidator.assertThat;

@DisplayName("BasketItem Validation Test")
class BasketItemTest {

	private Product product;
	private BasketItem basketItem;

	@BeforeEach
	void init() {
		product = Product.create("Google Pixel XL", "-", BigDecimal.ONE);
		basketItem = BasketItem.create(product, 1);
	}

	@Test
	@DisplayName("BasketItem is valid")
	void isValid() {
		assertThat(basketItem).isValid();
	}

	@Nested
	@DisplayName("Product")
	class ProductTests {

		@Test
		@DisplayName("Product is null")
		void shouldFailOnNullProduct() {
			basketItem.setProduct(null);
			assertThat(basketItem)
					.isInvalid()
					.containsInvalidAttribute("product");
		}

		@Test
		@DisplayName("Product is invalid")
		void shouldFailOnInvalidProduct() {
			product.setPrice(BigDecimal.ZERO);
			basketItem.setProduct(product);
			assertThat(basketItem)
					.isInvalid()
					.containsInvalidAttribute("product.price");
		}

		@Test
		@DisplayName("Product is valid")
		void productIsValid() {
			assertThat(basketItem).isValid();
		}
	}

	@Nested
	@DisplayName("Quantity")
	class QuantityTests {

		@Test
		@DisplayName("Quantity is negative")
		void shouldFailOnNegativeQuantity() {
			basketItem.setQuantity(-1);
			assertThat(basketItem)
					.isInvalid()
					.containsInvalidAttribute("quantity");
		}

		@Test
		@DisplayName("Quantity is zero")
		void shouldFailOnZeroQuantity() {
			basketItem.setQuantity(0);
			assertThat(basketItem)
					.isInvalid()
					.containsInvalidAttribute("quantity");
		}

		@Test
		@DisplayName("Quantity is valid")
		void quantityIsValid() {
			assertThat(basketItem).isValid();
		}

	}

}