package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class DataRetrievingImpl implements DataRetrieving {
	
	private String data;
	private String urlOfData;
	/**
	 * 
	 */
	public DataRetrievingImpl(){}
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void downloadData() throws FileNotFoundException, IOException {
		urlOfData="http://donnees.roulez-eco.fr/opendata/jour";
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(urlOfData);
		HttpResponse resp = client.execute(httpget);
		resp.getEntity().writeTo(new FileOutputStream(new File("tmp","data.zip")));;
	}
	/**
	 * @return the current localization of the file 
	 * which contains the data.
	 */
	public String getLinkOfData() {
		return data;
	}
}
