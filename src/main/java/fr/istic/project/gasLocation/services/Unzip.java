package fr.istic.project.gasLocation.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public interface Unzip {
    public void unzipData() throws FileNotFoundException, IOException;
    public void deleteZipFile();
    public void deleteAllFiles();
    public String getNameOftheUnzipFile();
    public Date getDateOfUnzipFile() throws ParseException;
}
