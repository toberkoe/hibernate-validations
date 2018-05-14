package de.toberkoe.examples.hibernate.validations;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static de.toberkoe.examples.hibernate.validations.EntityValidator.assertThat;

@DisplayName("Product Validation Test")
class ProductTest {

	private Product product;

	@BeforeEach
	void initProduct() {
		product = Product.create("Google Pixel XL", "-", BigDecimal.valueOf(699));
	}

	@Test
	@DisplayName("Product is valid")
	void isValid() {
		assertThat(product).isValid();
	}

	@Nested
	@DisplayName("Name")
	class NameTests {

		@Test
		@DisplayName("Name is null")
		void shouldFailOnNullName() {
			product.setName(null);
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("name");
		}

		@Test
		@DisplayName("Name is too short")
		void shouldFailOnShortName() {
			product.setName("");
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("name");
		}

		@Test
		@DisplayName("Name is too long")
		void shouldFailOnLongName() {
			product.setName(product.getName() + product.getName());
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("name");
		}

		@Test
		@DisplayName("Name is valid")
		void nameIsValid() {
			assertThat(product).isValid();
		}
	}

	@Nested
	@DisplayName("Description")
	class DescriptionTests {

		@Test
		@DisplayName("Description is null")
		void shouldFailOnNullDescription() {
			product.setDescription(null);
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("description");
		}

		@Test
		@DisplayName("Description is empty")
		void shouldFailOnEmptyDescription() {
			product.setDescription("");
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("description");
		}

		@Test
		@DisplayName("Description is valid")
		void descriptionIsValid() {
			assertThat(product).isValid();
		}
	}

	@Nested
	@DisplayName("Price")
	class PriceTests {

		@Test
		@DisplayName("Price is null")
		void shouldFailOnNullPrice() {
			product.setPrice(null);
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("price");
		}

		@Test
		@DisplayName("Price is negative")
		void shouldFailOnNegativePrice() {
			product.setPrice(BigDecimal.ONE.negate());
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("price");
		}

		@Test
		@DisplayName("Price is zero")
		void shouldFailOnZeroPrice() {
			product.setPrice(BigDecimal.ZERO);
			assertThat(product)
					.isInvalid()
					.containsInvalidAttribute("price");
		}

		@Test
		@DisplayName("Price is valid")
		void priceIsValid() {
			assertThat(product).isValid();
		}

	}

}