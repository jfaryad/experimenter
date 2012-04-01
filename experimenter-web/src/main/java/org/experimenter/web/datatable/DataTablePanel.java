package org.experimenter.web.datatable;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Entity;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.model.EntityModel;

/**
 * Base class for all data tables in the experimenter application.
 * 
 * @author jakub
 * 
 * @param <T>
 */
public abstract class DataTablePanel<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;

    private ModalWindow modalWindow;
    private EntityFormPanel<T> entityForm;

    public DataTablePanel(String id, IDataProvider<T> dataProvider) {
        super(id);
        setOutputMarkupId(true);

        modalWindow = createModalWindow();
        add(modalWindow);

        entityForm = createEntityForm(modalWindow.getContentId());
        entityForm.setModalContainer(modalWindow);

        modalWindow.setContent(entityForm);
        addDataTable(dataProvider);
    }

    private void addDataTable(IDataProvider<T> dataProvider) {
        List<IColumn<T>> columns = getColumns();
        columns.add(createEditColumn());
        DataTable<T> table = new DataTable<T>("datatable", columns, dataProvider, 20);
        // table.add(new NavigationToolbar(table));
        table.addTopToolbar(new HeadersToolbar(table, null));
        add(table);
    }

    /**
     * Returns the list of all columns in the table.
     */
    protected abstract List<IColumn<T>> getColumns();

    /**
     * Creates a {@link EntityFormPanel} with the given component id and an {@link EntityModel} with a new empty (not
     * persisted) entity.
     * 
     * @param componentId
     *            the component id
     */
    protected abstract EntityFormPanel<T> createEntityForm(String componentId);

    private TableRowEditColumn<T> createEditColumn() {
        return new TableRowEditColumn<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(AjaxRequestTarget target, IModel<T> rowModel) {
                showEditDialog(target, rowModel);

            }

        };
    }

    private ModalWindow createModalWindow() {
        ModalWindow modal = new ModalWindow("modal-window");
        modal.setOutputMarkupId(true);
        modal.setWindowClosedCallback(new WindowClosedCallback() {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClose(AjaxRequestTarget target) {
                target.add(DataTablePanel.this);
            }
        });
        return modal;
    }

    private void showEditDialog(AjaxRequestTarget target, IModel<T> rowModel) {
        target.add(this);
        entityForm.setEntityModel(rowModel);
        modalWindow.show(target);
    }
}
