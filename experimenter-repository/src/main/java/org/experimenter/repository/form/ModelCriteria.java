package org.experimenter.repository.form;


public class ModelCriteria<T> {

    private T model;
    int first;
    int count;

    public ModelCriteria() {

    }

    public ModelCriteria(T model) {
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

    @Override
    public String toString() {
        return "ModelCriteria [" + model + ", first=" + first + ", count=" + count + "]";
    }

}
