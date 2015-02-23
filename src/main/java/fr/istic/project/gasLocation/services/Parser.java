package fr.istic.project.gasLocation.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

public interface Parser {
	 public void parse() throws FileNotFoundException, XmlPullParserException, IOException;
	 public List<Station> getPvd();
	 public List<Float> getDistances();
}
