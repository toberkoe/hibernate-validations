package de.toberkoe.examples.hibernate.validations;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasketItem {

	private long id;

	@Min(1)
	private int quantity;

	@NotNull
	@Valid
	private Product product;

	public BasketItem() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static BasketItem create(Product product, int quantity) {
		BasketItem item = new BasketItem();
		item.setProduct(product);
		item.setQuantity(quantity);
		return item;
	}
}
