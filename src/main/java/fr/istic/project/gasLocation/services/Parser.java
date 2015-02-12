package fr.istic.project.gasLocation.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

public interface Parser {
	 public void parse() throws FileNotFoundException, XmlPullParserException, IOException;
}
