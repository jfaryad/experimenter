package org.experimenter.repository.form;

public class CriteriaForm<T> {

	private T model;
	int first;
	int count;
	int order;

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "ItemForm [" + model + ", first=" + first + ", count=" + count
				+ ", order=" + order + "]";
	}

}
