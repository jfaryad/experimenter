package org.experimenter.web;

import org.apache.wicket.Component;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class Menu extends Panel {

    private static final long serialVersionUID = 1L;

    public Menu(String id) {
        super(id);

        add(authorizedLink("homePage", HomePage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("userPage", UserPage.class, Roles.ADMIN));
        add(authorizedLink("userGroupPage", UserGroupPage.class, Roles.ADMIN));
        add(authorizedLink("projectPage", ProjectPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("programPage", ProgramPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("applicationPage", ApplicationPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("experimentPage", ExperimentPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("connectionFarmPage", ConnectionFarmPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("connectionPage", ConnectionPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("computerPage", ComputerPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("inputPage", InputPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("inputSetPage", InputSetPage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("problemTypePage", ProblemTypePage.class, Roles.USER, Roles.ADMIN));
        add(authorizedLink("chartPage", ChartPage.class, Roles.USER, Roles.ADMIN));
    }

    private Component authorizedLink(String id, Class<? extends WebPage> targetPageClass, String... allowedRoles) {
        WebMarkupContainer container = new WebMarkupContainer(id);
        BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>(id + "-link", targetPageClass);
        container.add(link);
        link.setAutoEnable(true);
        ComponentAuthorization.authorizeRender(container, allowedRoles);
        return container;
    }
}
