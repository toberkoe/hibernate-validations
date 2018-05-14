package de.toberkoe.examples.hibernate.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class Basket {

	private long id;

	@NotEmpty
	@Valid
	private List<BasketItem> items;

	public Basket() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<BasketItem> getItems() {
		return items;
	}

	public void setItems(List<BasketItem> items) {
		this.items = items;
	}

	public static Basket create(BasketItem... items) {
		Basket basket = new Basket();
		Stream.of(items).forEach(basket::addItem);
		return basket;
	}

	public void addItem(BasketItem item) {
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
	}
}
