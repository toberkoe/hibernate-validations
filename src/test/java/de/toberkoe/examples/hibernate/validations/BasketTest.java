package de.toberkoe.examples.hibernate.validations;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.toberkoe.examples.hibernate.validations.EntityValidator.assertThat;

@DisplayName("Basket Validation Test")
class BasketTest {

	private Product product;
	private Basket basket;

	@BeforeEach
	void init() {
		product = Product.create("Google Pixel XL", "-", BigDecimal.ONE);
		BasketItem basketItem = BasketItem.create(product, 1);
		basket = Basket.create(basketItem);
	}

	@Test
	@DisplayName("Basket is valid")
	void isValid() {
		assertThat(basket).isValid();
	}

	@Nested
	@DisplayName("Items")
	class ItemsTests {

		@Test
		@DisplayName("Items are null")
		void shouldFailOnNullItems() {
			basket.setItems(null);
			assertThat(basket)
					.isInvalid()
					.containsInvalidAttribute("items");
		}

		@Test
		@DisplayName("Items are empty")
		void shouldFailOnEmptyItems() {
			basket.setItems(new ArrayList<>());
			assertThat(basket)
					.isInvalid()
					.containsInvalidAttribute("items");
		}

		@Test
		@DisplayName("Items are invalid")
		void shouldFailOnInvalidItems() {
			basket.getItems().clear();
			basket.addItem(BasketItem.create(product, 0));
			assertThat(basket)
					.isInvalid()
					.containsInvalidAttribute("items[0].quantity");
		}
	}

}