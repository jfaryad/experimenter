package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.experimenter.repository.entity.Program;
import org.experimenter.repository.entity.Project;
import org.experimenter.web.model.ProgramModel;
import org.experimenter.web.model.aggregate.AvailablePrograms;

/**
 * Default provider of the {@link Program} entity.
 * 
 * @author jakub
 * 
 */
public class ProgramProvider extends EntityDataProvider<Program> {

    private static final long serialVersionUID = 1L;

    private final IModel<List<Program>> innerProgramModel;

    public ProgramProvider(IModel<Project> projectFilter) {
        innerProgramModel = new AvailablePrograms().filterBy("project", projectFilter);
    }

    @Override
    public IModel<Program> model(Program program) {
        return new ProgramModel(program);
    }

    @Override
    protected List<Program> load() {
        return innerProgramModel.getObject();
    }

    @Override
    public void detach() {
        super.detach();
        innerProgramModel.detach();
    }

}
