package org.experimenter.repository.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class CheckSumUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CheckSumUtil.class);

    public static String getFileCheckSum(File file) {
        try {
            return DigestUtils.md5DigestAsHex(IOUtils.toByteArray(new FileInputStream(file)));
        } catch (Exception e) {
            LOG.error("Unable to calculate checksum for file.", e);
            throw new RuntimeException("Unable to calculate checksum for file.", e);
        }
    }
}
