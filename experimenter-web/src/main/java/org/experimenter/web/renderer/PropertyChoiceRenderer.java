package org.experimenter.web.renderer;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.PropertyModel;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Computer;
import org.experimenter.repository.entity.ConnectionFarm;
import org.experimenter.repository.entity.Entity;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.repository.entity.User;
import org.experimenter.repository.entity.UserGroup;

public class PropertyChoiceRenderer<T extends Entity> implements IChoiceRenderer<T> {

    private static final long serialVersionUID = 1L;

    private final String property;

    public static final PropertyChoiceRenderer<ProblemType> PROBLEM_TYPE_RENDERER = new PropertyChoiceRenderer<ProblemType>(
            "name");
    public static final PropertyChoiceRenderer<Project> PROJECT_RENDERER = new PropertyChoiceRenderer<Project>("name");
    public static final PropertyChoiceRenderer<Program> PROGRAM_RENDERER = new PropertyChoiceRenderer<Program>("name");
    public static final PropertyChoiceRenderer<Application> APPLICATION_RENDERER = new PropertyChoiceRenderer<Application>(
            "version");
    public static final PropertyChoiceRenderer<Application> APPLICATION_FULL_NAME_RENDERER = new PropertyChoiceRenderer<Application>(
            "fullName");
    public static final PropertyChoiceRenderer<Computer> COMPUTER_RENDERER = new PropertyChoiceRenderer<Computer>(
            "address");
    public static final PropertyChoiceRenderer<ConnectionFarm> CONNECTION_FARM_RENDERER = new PropertyChoiceRenderer<ConnectionFarm>(
            "name");
    public static final PropertyChoiceRenderer<Input> INPUT_RENDERER = new PropertyChoiceRenderer<Input>("name");
    public static final PropertyChoiceRenderer<InputSet> INPUT_SET_RENDERER = new PropertyChoiceRenderer<InputSet>(
            "name");
    public static final PropertyChoiceRenderer<UserGroup> USERGROUP_RENDERER = new PropertyChoiceRenderer<UserGroup>(
            "name");
    public static final PropertyChoiceRenderer<User> USER_RENDERER = new PropertyChoiceRenderer<User>("login");

    public PropertyChoiceRenderer(String property) {
        this.property = property;
    }

    @Override
    public Object getDisplayValue(T object) {
        return getPropertyValue(object);
    }

    @Override
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
