package org.experimenter.repository.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.experimenter.repository.exception.ZipException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A helper class for working with zip archives.
 * 
 * @author jfaryad
 * 
 */
public class ZipUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ZipUtil.class);

    /**
     * Puts all the given files into a zip archive flattening the directory tree. That means, that all files will be
     * added to the root directory of the zip file.
     * 
     * @param fileList
     *            the list of files to zip
     * @param zipFile
     *            the zip file to write to
     */
    public static void zipFileListFlat(List<File> fileList, File zipFile) {

        byte[] buffer = new byte[1024];

        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            LOG.info("Output to Zip : " + zipFile);
            for (File file : fileList) {
                LOG.info("File Added : " + file.getName());
                ZipEntry ze = new ZipEntry(file.getName());
                zos.putNextEntry(ze);

                FileInputStream in = new FileInputStream(file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
            }

            zos.closeEntry();
            zos.close();

            LOG.info("Done");
        } catch (IOException e) {
            LOG.error("Error creating zip archive.", e);
            throw new ZipException(e);
        }
    }

    /**
     * Unzips the given archive into the provided destination folder.
     * 
     * @param zipArchive
     *            the archive to unzip
     * @param destinationFolder
     *            the output folder
     * @return list of unzipped files
     */
    public static List<File> unzip(File zipArchive, File destinationFolder) {
        byte[] buffer = new byte[1024];
        List<File> list = new ArrayList<File>();
        try {

            // get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipArchive));
            // get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {

                // create output directory is not exists
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdir();
                }

                String fileName = ze.getName();
                File newFile = new File(destinationFolder, fileName);

                LOG.info("file unzip : " + newFile.getAbsoluteFile());
                list.add(newFile);

                // create all non exists folders
                // else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            LOG.info("Done");

        } catch (IOException ex) {
            LOG.error("Error unzipping archive", ex);
            throw new ZipException(ex);
        }
        return list;
    }
}
