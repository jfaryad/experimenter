package org.experimenter.web.datatable.column;

import org.apache.commons.io.FilenameUtils;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A {@link PropertyColumn} that of a property representing a file path. The column displays only the last part of the
 * path: the short file name.
 * 
 * @author jfaryad
 * 
 * @param <T>
 *            the type of the bean whose property is read
 */
public class FileNamePropertyColumn<T> extends PropertyColumn<T, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * 
     * @param headerModel
     *            model of the text in the column header
     * @param propertyExpression
     *            the property expression
     */
    public FileNamePropertyColumn(IModel<String> headerModel, String propertyExpression) {
        super(headerModel, propertyExpression);
    }

    @Override
    public IModel<Object> getDataModel(final IModel<T> rowModel) {
        final PropertyModel<String> propertyModel = new PropertyModel<String>(rowModel, getPropertyExpression());
        return new IModel<Object>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void detach() {
                propertyModel.detach();
            }

            @Override
            public Object getObject() {
                String filePath = propertyModel.getObject();
                if (filePath != null) {
                    filePath = FilenameUtils.getName(filePath);
                }
                return filePath;
            }

            @Override
            public void setObject(Object object) {
                // do nothing, this is supposed to be read only.
            }
        };
    }

}
