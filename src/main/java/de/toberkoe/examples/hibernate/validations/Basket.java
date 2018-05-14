package de.toberkoe.examples.hibernate.validations;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Basket {

	private long id;

	@NotEmpty
	@Size(min = 1)
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

	public void addItem(BasketItem item) {
		if (items == null) {
			items = new ArrayList<>();
		}
	}
}
