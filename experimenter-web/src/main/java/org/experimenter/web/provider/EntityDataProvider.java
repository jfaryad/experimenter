package org.experimenter.web.provider;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.experimenter.repository.entity.Entity;

/**
 * Parent class for all entity data providers. The extended classes have to implement the
 * {@link IDataProvider#model(Object)} method.
 * 
 * @author jakub
 * 
 * @param <T>
 *            the provided entity
 */
public abstract class EntityDataProvider<T extends Entity> implements IDataProvider<T> {

    private static final long serialVersionUID = 1L;

    private volatile Collection<T> items;

    public EntityDataProvider() {
        Injector.get().inject(this);
    }

    @Override
    public void detach() {
        items = null;
    }

    @Override
    public Iterator<? extends T> iterator(int first, int count) {
        if (items == null)
            items = load();
        return items.iterator();
    }

    @Override
    public int size() {
        if (items == null)
            items = load();
        return items.size();
    }

    /**
     * Calls the appropriate service method to load the list of entities.
     * 
     * @return a list of entities
     */
    protected abstract List<T> load();

}
