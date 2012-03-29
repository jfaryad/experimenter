package org.experimenter.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.experimenter.repository.entity.User;
import org.experimenter.web.common.panel.UserPanel;
import org.experimenter.web.model.UserModel;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        User user = new User();
        user.setName("Josef");
        user.setSurname("Novak");
        user.setEmail("pepa@email.com");
        add(new UserPanel("userPanel", new UserModel(user)));
    }
}
