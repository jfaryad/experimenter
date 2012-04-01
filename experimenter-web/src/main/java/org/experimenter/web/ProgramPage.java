package org.experimenter.web;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.web.datatable.ProgramTable;
import org.experimenter.web.provider.ProgramProvider;

/**
 * Page for program administration.
 * 
 * @author jakub
 * 
 */
public class ProgramPage extends AbstractExperimenterPage {
    private static final long serialVersionUID = 1L;

    public ProgramPage(final PageParameters parameters) {
        super(parameters, "Programs");
        add(new ProgramTable("program-table", new ProgramProvider()));
    }
}
