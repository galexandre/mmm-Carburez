package fr.istic.project.gasLocation.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;


public interface DataRetrieving {
	public void downloadData() throws FileNotFoundException, IOException,URISyntaxException;
	public String getLinkOfData();
	public void unzip();
}
