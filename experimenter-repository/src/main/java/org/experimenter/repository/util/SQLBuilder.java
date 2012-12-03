package org.experimenter.repository.util;

public class SQLBuilder {

    public static final String INSERT_RESULT = "" +
            "insert into \n" +
            "    result \n" +
            "        (experiment_id, input_id, param, value) \n" +
            "    values \n" +
            "        (:experimentId, :inputId, :param, :value)\n";

    public static final String ALL_RESULTS_FOR_EXPERIMENT = "" +
            "select \n" +
            "    experiment_id, \n" +
            "    input_id, \n" +
            "    param, \n" +
            "    value \n" +
            "from \n" +
            "    result \n" +
            "where " +
            "    experiment_id = :experimentId \n";

}
