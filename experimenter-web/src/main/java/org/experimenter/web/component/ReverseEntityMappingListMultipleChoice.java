package org.experimenter.web.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;

/**
 * A {@link ListMultipleChoice} that displays an entity list that is a field of another entity. <br>
 * However, since the displayed entity is the owning entity of the hibernate mapping, changing items in the owned
 * entity's list doesn't do anything. That's why the old items have to be removed and the new ones have to be added
 * explicitly by calling the the service
 * 
 * @author jfaryad
 * 
 */
public abstract class ReverseEntityMappingListMultipleChoice<Owner, Reverse> extends
        ListMultipleChoice<Owner> {

    private static final long serialVersionUID = 1L;

    private final IModel<Reverse> reverse;
    private final List<Owner> originalList = new ArrayList<Owner>();

    /**
     * Constructor.
     * 
     * @param id
     *            wicket id
     * @param model
     *            the model of the owning entities that belong to the mapped list
     * @param choices
     *            all available owning entities
     * @param renderer
     *            the renderer to use
     * @param reverse
     *            the edited user group
     */
    public ReverseEntityMappingListMultipleChoice(String id, IModel<? extends Collection<Owner>> model,
            IModel<? extends List<? extends Owner>> choices, IChoiceRenderer<? super Owner> renderer,
            IModel<Reverse> reverse) {
        super(id, model, choices, renderer);
        Injector.get().inject(this);
        this.reverse = reverse;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onModelChanging() {
        originalList.clear();
        Collection<Owner> oldCollection = (Collection<Owner>) getDefaultModelObject();
        if (oldCollection != null) {
            originalList.addAll(oldCollection);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onModelChanged() {
        Collection<Owner> newCollection = (Collection<Owner>) getDefaultModelObject();
        List<Owner> newList = new ArrayList<Owner>(newCollection);
        for (Owner owner : originalList) {
            removeFromList(owner, reverse.getObject());
        }
        for (Owner owner : newList) {
            addToList(owner, reverse.getObject());
        }
    }

    protected abstract void removeFromList(Owner owner, Reverse reverse);

    protected abstract void addToList(Owner owner, Reverse reverse);
}
