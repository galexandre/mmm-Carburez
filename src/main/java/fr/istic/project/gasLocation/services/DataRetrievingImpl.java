package fr.istic.project.gasLocation.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataRetrievingImpl implements DataRetrieving {
	
	//private String data;
	private String urlOfData;
	/**
	 * 
	 */
	public DataRetrievingImpl(){
		this.urlOfData="http://donnees.roulez-eco.fr/opendata/jour";
	}
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	public void downloadData() throws FileNotFoundException, IOException, URISyntaxException {
		System.err.println("let's go");
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet();
		httpget.setURI(new URI(urlOfData));
		//HttpResponse resp = client.execute(httpget);
		//System.err.println("launch entity");
		//resp.getEntity().writeTo(new FileOutputStream(new File("tmp","data.zip")));;
	}
	/**
	 * @return the current localization of the file 
	 * which contains the data.
	 */
	public String getLinkOfData() {
		return urlOfData;
	}
	/**
	 * Unzip the file
	 */
	public void unzip() {
		System.out.println("I unzip the file");
		//InputStream is;
	}
}
