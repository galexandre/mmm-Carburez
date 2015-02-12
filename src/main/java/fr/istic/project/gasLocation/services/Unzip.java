package fr.istic.project.gasLocation.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Unzip {
    public void unzipData() throws FileNotFoundException, IOException;
    public void deleteZipFile();
    public void deleteAllFiles();
    public String getNameOftheUnzipFile();
}
