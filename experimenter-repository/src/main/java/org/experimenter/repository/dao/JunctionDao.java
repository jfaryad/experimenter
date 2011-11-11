package org.experimenter.repository.dao;

import org.experimenter.repository.model.ConnectionFarm;
import org.experimenter.repository.model.Experiment;
import org.experimenter.repository.model.Input;
import org.experimenter.repository.model.InputSet;
import org.experimenter.repository.model.Project;
import org.experimenter.repository.model.User;
import org.experimenter.repository.model.UserGroup;

public interface JunctionDao {
    public void addUserToUserGroup(User user, UserGroup userGroup);

    public void addInputToInputSet(Input input, InputSet inputSet);

    public void addInputSetToExperiment(InputSet inputSet, Experiment experiment);

    public void addInputSetToProject(InputSet inputSet, Project project);

    public void addConnectionFarmToExperiment(ConnectionFarm connectionFarm, Experiment experiment);

    public void removeUserFromUserGroup(User user, UserGroup userGroup);

    public void removeInputFromInputSet(Input input, InputSet inputSet);

    public void removeInputSetFromExperiment(InputSet inputSet, Experiment experiment);

    public void removeInputSetFromProject(InputSet inputSet, Project project);

    public void removeConnectionFarmFromExperiment(ConnectionFarm connectionFarm, Experiment experiment);
}
