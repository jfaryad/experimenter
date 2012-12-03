package org.experimenter.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link LoadableDetachableModel} of a list, that filters the loaded list based on values of other models.<br>
 * By calling {@link #filterBy(String, IModel)}, you ensure, that the resulting filtered list will contain only those
 * items, whose property specified in the filterBy method has a value equal to the one returned by the given model. <br>
 * Mulitple filters can be chained. Only the items that pass through all filters will be included in the result.<br>
 * The property can be recursive, i.e. it can be a property expression like the one passed to {@link PropertyModel}s
 * <p>
 * <b>WARNING</b>: when using recursive property expressions on entities, consider the performance hit, as for every
 * step of the iteration several hibernate queries can be triggered.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class FilteredListModel<T> extends LoadableDetachableModel<List<T>> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(FilteredListModel.class);

    private final Map<String, IModel<?>> filters = new HashMap<String, IModel<?>>();

    /**
     * Register a new property filter
     * 
     * @param propertyName
     *            name of the property to filter on
     * @param filter
     *            model containing the value, that the given property must be equal to
     * @return the same instance of {@link FilteredListModel} to enable chaining.
     */
    public FilteredListModel<T> filterBy(String propertyName, IModel<?> filter) {
        Args.notNull(propertyName, "propertyName");
        filters.put(propertyName, filter);
        return this;
    }

    @Override
    protected final List<T> load() {
        List<T> originalList = loadUnfiltered();
        List<T> filteredList = new ArrayList<T>(originalList);
        if (!filters.isEmpty()) {
            for (Iterator<T> it = filteredList.iterator(); it.hasNext();) {
                T item = it.next();
                for (Entry<String, IModel<?>> entry : filters.entrySet()) {
                    if (entry.getValue() != null && entry.getValue().getObject() != null) {
                        try {
                            Object actualPropertyValue = new PropertyModel<Object>(item, entry.getKey()).getObject();
                            if (!entry.getValue().getObject().equals(actualPropertyValue)) {
                                it.remove();
                                break;
                            }
                        } catch (Exception e) {
                            LOG.error(
                                    "Error in FilteredListModel when accessing property "
                                            + entry.getKey()
                                            + ", skipping filtering.",
                                    e);
                            return originalList;
                        }

                    }
                }
            }
        }
        return filteredList;
    }

    /**
     * Loads the whole result list, before it get's filtered.
     * 
     * @return
     */
    protected abstract List<T> loadUnfiltered();

}
