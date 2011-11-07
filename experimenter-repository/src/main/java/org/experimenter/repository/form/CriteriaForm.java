package org.experimenter.repository.form;

import org.sqlproc.engine.SqlOrder;

public class CriteriaForm<T> {

    private T model;
    int first;
    int count;
    SqlOrder order;

    public CriteriaForm() {

    }

    public CriteriaForm(T model) {
        this.model = model;
    }

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

    public SqlOrder getOrder() {
        if (order == null)
            return SqlOrder.getOrder();
        else
            return order;
    }

    public void setOrder(SqlOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CriteriaForm [" + model + ", first=" + first + ", count=" + count + ", order=" + order + "]";
    }

}
