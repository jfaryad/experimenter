package org.experimenter.web.provider;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.ProblemTypeService;
import org.experimenter.web.model.ProblemTypeModel;

/**
 * Default provider of the {@link ProblemType} entity.
 * 
 * @author jakub
 * 
 */
public class ProblemTypeProvider extends EntityDataProvider<ProblemType> {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private ProblemTypeService problemTypeService;

    @Override
    public IModel<ProblemType> model(ProblemType problemType) {
        return new ProblemTypeModel(problemType);
    }

    @Override
    protected List<ProblemType> load() {
        // loads all problemTypes
        return problemTypeService.findByExample(new ProblemType());
    }

}
