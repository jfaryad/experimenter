package org.experimenter.repository.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.experimenter.repository.dao.InputDao;
import org.experimenter.repository.entity.Experiment;
import org.experimenter.repository.entity.Input;
import org.experimenter.repository.entity.InputSet;
import org.experimenter.repository.entity.ProblemType;
import org.experimenter.repository.service.InputService;
import org.experimenter.repository.util.CheckSumUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputServiceImpl extends AbstractService<Input, InputDao> implements InputService {

    private static final Logger LOG = LoggerFactory.getLogger(InputServiceImpl.class);

    @Override
    public void saveWithData(Input input, File tmpDataFile) {
        checkNotNull(input);
        input.setData("tmpValue");
        input.setChecksum(CheckSumUtil.getFileCheckSum(tmpDataFile));
        saveUpdate(input);
        input.setData(storageService.saveInput(input.getId(), tmpDataFile));
        saveUpdate(input);
    }

    @Override
    protected void deleteDependencies(Input input) {
        resultService.delete(input.getResults());
    }

    @Override
    public void addInputToInputSet(Input input, InputSet inputSet) {
        if (!input.getInputSets().contains(input)) {
            input.getInputSets().add(inputSet);
            if (inputSet.getInputs() == null) {
                inputSet.setInputs(new ArrayList<Input>());
            }
            inputSet.getInputs().add(input);
        }
    }

    @Override
    public void removeInputFromInputSet(Input input, InputSet inputSet) {
        input.getInputSets().remove(inputSet);
        inputSet.getInputs().remove(input);
    }

    @Override
    public List<Input> findInputsByProblemType(ProblemType problemType) {
        checkIdNotNull(problemType);
        return problemType.getInputs();
    }

    @Override
    public List<Input> findInputsByInputSet(InputSet inputSet) {
        checkIdNotNull(inputSet);
        return inputSet.getInputs();
    }

    @Override
    protected boolean hasDependencies(Input input) {
        return !input.getInputSets().isEmpty() || !input.getResults().isEmpty();
    }

    @Override
    public void saveFromZipArchive(InputSet inputSet, File tmpDataFile) {
        List<File> unzippedFiles = storageService.unzipArchiveIntoTempDir(tmpDataFile);
        for (File inputFile : unzippedFiles) {
            String checksum;
            checksum = CheckSumUtil.getFileCheckSum(inputFile);
            Input input = baseDao.findInputByChecksum(checksum);
            if (input == null) {
                input = new Input();
                input.setName(inputFile.getName());
                input.setProblem(inputSet.getProblem());
                input.setInputSets(new ArrayList<InputSet>());
            } else if (!inputSet.getProblem().equals(input.getProblem())) {
                LOG.warn("Input with the same chcecksum already exists, but with another problem type");
                org.springframework.util.FileSystemUtils.deleteRecursively(inputFile.getParentFile());
                continue;
            }
            saveWithData(input, inputFile);
            addInputToInputSet(input, inputSet);
        }
    }

    @Override
    public List<Input> findInputsByExperiment(Experiment experiment) {
        checkIdNotNull(experiment);
        return baseDao.findInputsByExperiment(experiment);
    }
}
