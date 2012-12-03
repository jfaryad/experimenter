package org.experimenter.web.provider;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.entity.User;
import org.experimenter.web.ExperimenterSession;

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
    public Iterator<? extends T> iterator(long first, long count) {
        if (items == null)
            items = load();
        return new SubsetIterator<T>(items.iterator(), first, count);
    }

    @Override
    public long size() {
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

    protected User currentUser() {
        return ExperimenterSession.get().getCurrentUser();
    }

    private static class SubsetIterator<T extends Entity> implements Iterator<T> {

        private final Iterator<T> fullIterator;
        private final long first;
        private final long count;
        private long index = 0;

        private SubsetIterator(Iterator<T> fullIterator, long first, long count) {
            this.fullIterator = fullIterator;
            this.count = count;
            this.first = first;
            while (index != first && fullIterator.hasNext()) {
                fullIterator.next();
                index++;
            }
        }

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return fullIterator.hasNext() && index <= first + count;
        }

        @Override
        public T next() {
            if (index > first + count) {
                throw new NoSuchElementException();
            }
            index++;
            return fullIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
