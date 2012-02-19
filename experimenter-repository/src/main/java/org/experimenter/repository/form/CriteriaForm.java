package org.experimenter.repository.form;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

public class CriteriaForm<T> {

    private T entity;
    Integer first;
    Integer count;
    List<Order> order = new ArrayList<Order>();

    public CriteriaForm() {

    }

    public CriteriaForm(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public void addOrder(String property, boolean ascending) {
        if (ascending)
            this.order.add(Order.asc(property));
        else
            this.order.add(Order.desc(property));
    }

    @Override
    public String toString() {
        return "CriteriaForm [" + entity + ", first=" + first + ", count=" + count + ", order=" + order + "]";
    }

}
