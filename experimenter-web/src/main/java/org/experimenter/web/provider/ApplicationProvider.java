package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Application;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.model.ApplicationModel;
import org.experimenter.web.model.aggregate.AvailableApplications;

/**
 * Default provider of the {@link Application} entity.
 * 
 * @author jakub
 * 
 */
public class ApplicationProvider extends EntityDataProvider<Application> {

    private static final long serialVersionUID = 1L;

    private final IModel<List<Application>> innerApplicationModel;

    public ApplicationProvider(IModel<Program> programFilter) {
        innerApplicationModel = new AvailableApplications().filterBy("program", programFilter);
    }

    public ApplicationProvider(IModel<Project> projectFilter, IModel<Program> programFilter) {
        innerApplicationModel = new AvailableApplications()
                .filterBy("program", programFilter)
                .filterBy("program.project", projectFilter);
    }

    @Override
    public IModel<Application> model(Application application) {
        return new ApplicationModel(application);
    }

    @Override
    protected List<Application> load() {
        return innerApplicationModel.getObject();
    }

    @Override
    public void detach() {
        super.detach();
        innerApplicationModel.detach();
    }

}
