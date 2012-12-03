package org.experimenter.web.datatable.column;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;

/**
 * The cell in a entity data table containing the edit link.
 * 
 * @author jfaryad
 * 
 * @param <T>
 */
public abstract class LinkCell<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    public LinkCell(String id, final IModel<T> rowModel, final IModel<String> linkText) {
        super(id);
        Link<String> link = new Link<String>("link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                LinkCell.this.onClick(rowModel);
            }

        };

        add(link);
        link.add(new Label("link-text", linkText));
    }

    protected abstract void onClick(final IModel<T> rowModel);

}
