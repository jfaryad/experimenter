package org.experimenter.repository.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

/**
 * A helper class that provides a method for calculating the checksum from a file.
 * 
 * @author jfaryad
 * 
 */
public class CheckSumUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CheckSumUtil.class);

    /**
     * Returns the md5 digetst of a file as a hex string.
     * 
     * @param file
     *            the file to calculate the checksum for
     */
    public static String getFileCheckSum(File file) {
        try {
            return DigestUtils.md5DigestAsHex(IOUtils.toByteArray(new FileInputStream(file)));
        } catch (Exception e) {
            LOG.error("Unable to calculate checksum for file.", e);
            throw new RuntimeException("Unable to calculate checksum for file.", e);
        }
    }
}
