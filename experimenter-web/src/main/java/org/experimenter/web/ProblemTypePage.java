package org.experimenter.web;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ProblemTypeTable;
import org.experimenter.web.provider.ProblemTypeProvider;

/**
 * Page for problemType administration.
 * 
 * @author jakub
 * 
 */
@AuthorizeInstantiation("USER")
public class ProblemTypePage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ProblemTypePage(final PageParameters parameters) {
        super(parameters, "ProblemTypes");
        add(new ProblemTypeTable("problemType-table", new ProblemTypeProvider()));
    }
}
