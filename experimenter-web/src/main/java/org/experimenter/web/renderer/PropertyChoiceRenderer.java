package org.experimenter.web.renderer;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.PropertyModel;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;

public class PropertyChoiceRenderer<T extends Entity> implements IChoiceRenderer<T> {

    private static final long serialVersionUID = 1L;

    private String property;

    public static final PropertyChoiceRenderer<ProblemType> PROBLEM_TYPE = new PropertyChoiceRenderer<ProblemType>(
            "name");
    public static final PropertyChoiceRenderer<Project> PROJECT = new PropertyChoiceRenderer<Project>("name");
    public static final PropertyChoiceRenderer<Program> PROGRAM = new PropertyChoiceRenderer<Program>("name");
    public static final PropertyChoiceRenderer<Application> APPLICATION = new PropertyChoiceRenderer<Application>(
            "version");

    public PropertyChoiceRenderer(String property) {
        this.property = property;
    }

    public Object getDisplayValue(T object) {
        return getPropertyValue(object);
    }

    public String getIdValue(T object, int index) {
        return object.getId().toString();
    }

    private Object getPropertyValue(T object) {
        try {
            return new PropertyModel<T>(object, property).getObject();
        } catch (Exception err) {
            return object.toString();
        }
    }
}
