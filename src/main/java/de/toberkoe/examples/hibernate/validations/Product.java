package de.toberkoe.examples.hibernate.validations;

import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {

	private long id;

	@NotNull
	@Size(min = 10, max = 20)
	private String name;

	@NotBlank
	private String description;

	@NotNull
	@DecimalMin("0.01")
	private BigDecimal price;

	public Product() {
	}

	public static Product create(String name, String description, BigDecimal price) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		return product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
