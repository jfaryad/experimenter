package org.experimenter.web.datatable;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.experimenter.repository.DeleteDependentException;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.service.EntityService;
import org.experimenter.web.ExperimenterSession;
import org.experimenter.web.common.panel.EntityFormPanel;
import org.experimenter.web.datatable.column.TableRowDeleteColumn;
import org.experimenter.web.datatable.column.TableRowEditColumn;
import org.experimenter.web.model.EntityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all data tables in the experimenter application.
 * <p>
 * It also adds support for being injected with the @SpringBean annotation.
 * 
 * @author jakub
 * 
 * @param <T>
 */
public abstract class DataTablePanel<T extends Entity> extends Panel {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(DataTablePanel.class);

    private final ModalWindow modalWindow;
    private final EntityFormPanel<T> entityForm;
    protected Component feedback;
    protected DataTable<T, String> table;

    public DataTablePanel(String id, IDataProvider<T> dataProvider) {
        super(id);
        setOutputMarkupId(true);
        Injector.get().inject(this);

        modalWindow = createModalWindow();
        add(modalWindow);

        entityForm = createEntityForm(modalWindow.getContentId());
        entityForm.setModalContainer(modalWindow);

        modalWindow.setContent(entityForm);
        addDataTable(dataProvider);

        add(createAddLink());
    }

    private void addDataTable(IDataProvider<T> dataProvider) {

        add(feedback = new FeedbackPanel("feedback").setOutputMarkupId(true));
        List<IColumn<T, String>> columns = getColumns();

        if (isEditable()) {
            columns.add(createEditColumn());
        }

        if (ExperimenterSession.get().getCurrentUser().getIsAdmin()) {
            columns.add(createDeleteColumn());
        }

        table = new DataTable<T, String>("datatable", columns, dataProvider, 12);
        table.addTopToolbar(new NavigationToolbar(table));
        table.addTopToolbar(new HeadersToolbar<String>(table, null));
        table.setOutputMarkupId(true);
        add(table);
    }

    /**
     * Returns the list of all columns in the table.
     */
    protected abstract List<IColumn<T, String>> getColumns();

    /**
     * Creates a {@link EntityFormPanel} with the given component id and an {@link EntityModel} with a new empty (not
     * persisted) entity.
     * 
     * @param componentId
     *            the component id
     */
    protected abstract EntityFormPanel<T> createEntityForm(String componentId);

    /**
     * Determines whether it's possible to create a new instance of this entity using a "new" button.
     * 
     * @return true, if the "new" button should be displayed
     */
    protected boolean enableAdding() {
        return true;
    }

    /**
     * Is called when the entity form is being displayed. The new entity instance will be set as the form object. <br>
     * Usually, it the entity should be empty, but you can preset some of it's properties, if you want the entity form
     * to have some default values preselected.
     * 
     * @return a new entity instance
     */
    protected abstract T getNewEntity();

    /**
     * Determines whether there should be an edit column. Some entities can't be edited after being created
     * 
     * @return
     */
    protected boolean isEditable() {
        return true;
    }

    protected int getInitialModalWindowHeight() {
        return 230;
    }

    protected int getInitialModalWindowWidth() {
        return 590;
    }

    protected abstract EntityService<T> getEntityService();

    private AjaxLink<String> createAddLink() {
        AjaxLink<String> link = new AjaxLink<String>("add-link") {

            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                showEditDialog(target, getNewEntity());
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                setVisibilityAllowed(enableAdding());
            }
        };
        return link;
    }

    private TableRowEditColumn<T> createEditColumn() {
        return new TableRowEditColumn<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(AjaxRequestTarget target, IModel<T> rowModel) {
                entityForm.modelChanged();
                showEditDialog(target, rowModel.getObject());
            }

        };
    }

    private TableRowDeleteColumn<T> createDeleteColumn() {
        return new TableRowDeleteColumn<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(AjaxRequestTarget target, IModel<T> rowModel) {
                try {
                    getEntityService().tryDelete(rowModel.getObject());
                } catch (DeleteDependentException ex) {
                    LOG.error("Error deleting the item. Other objects are still bound to it.", ex);
                    error("Error deleting the item. Other objects are still bound to it.");
                }
                target.add(DataTablePanel.this);
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
        modal.setInitialHeight(getInitialModalWindowHeight());
        modal.setInitialWidth(getInitialModalWindowWidth());
        return modal;
    }

    private void showEditDialog(AjaxRequestTarget target, T object) {
        target.add(this);
        entityForm.setFormObject(object);
        modalWindow.show(target);
    }
}
