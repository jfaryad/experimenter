package org.experimenter.web.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.DropDownChoice;

/**
 * An {@link AjaxLink} that sets null to all registered {@link Component}s and updates them and also all other
 * components registered as target components.
 * 
 * @author jfaryad
 * 
 */
public class ResetFiltersLink extends AjaxLink<Void> {

    private static final long serialVersionUID = 1L;
    private final List<Component> filterList = new ArrayList<Component>();
    private final List<Component> targetComponents = new ArrayList<Component>();

    public ResetFiltersLink(String id, Component... filters) {
        super(id);
        if (filters != null) {
            filterList.addAll(Arrays.asList(filters));
        }
    }

    @Override
    public void onClick(AjaxRequestTarget target) {
        for (Component filter : filterList) {
            filter.setDefaultModelObject(null);
            target.add(filter);
        }
        for (Component component : targetComponents) {
            target.add(component);
        }
    }

    public ResetFiltersLink addFiltersToReset(DropDownChoice<?>... filters) {
        if (filters != null && filters.length > 0) {
            filterList.addAll(Arrays.asList(filters));
        }
        return this;
    }

    public ResetFiltersLink addTargetComponents(Component... components) {
        if (components != null && components.length > 0) {
            targetComponents.addAll(Arrays.asList(components));
        }
        return this;
    }

}
