package org.experimenter.repository.service;

import org.experimenter.repository.model.ConnectionFarm;
import org.experimenter.repository.model.Experiment;
import org.experimenter.repository.model.Input;
import org.experimenter.repository.model.InputSet;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.model.User;
import org.experimenter.repository.model.UserGroup;

public interface DaoService {

    public void addUserToUserGroup(User user, UserGroup userGroup);

    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment);

    public void addInputSetToProject(InputSet inputSet, Project project);

    public void addInputToInputSet(Input input, InputSet inputSet);

    public void addFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment);
}
