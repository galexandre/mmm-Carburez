package fr.istic.project.gasLocation.controller;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import fr.istic.project.gasLocation.services.DataRetrieving;
import fr.istic.project.gasLocation.services.DataRetrievingImpl;
import fr.istic.project.gasLocation.services.Parser;
import fr.istic.project.gasLocation.services.ParserImpl;
import fr.istic.project.gasLocation.services.Unzip;
import fr.istic.project.gasLocation.services.UnzipImpl;
import android.content.Context;

public class DownloadController {
	 private Context myContext;
	    private DataRetrieving dd;
	    private Unzip uz;
	    private Parser p;
	    private String url;
	    
	    /**
	     * Contructor of a controller
	     * @param ctx a Context (android.content.Context)
	     */
	    public DownloadController(Context ctx, String url){
	        this.myContext=ctx;
	        this.url=url;
	    }
	    
	    public void execute(){
	    	downloadData(url);
	    	try {
				unzipFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				parseXmlFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    /**
	     * Donwload data from the url in parameter
	     * @param url : String
	     */
	    public void downloadData(String url){
	        dd = new DataRetrievingImpl(myContext,url);
	        dd.download();
	    }

	    public void unzipFile() throws IOException {
	        uz = new UnzipImpl("/Download","data.zip");
	        uz.unzipData();
	    }

	    public void deleteZipFile(){
	        //uz.deleteZipFile();
	    }

	    public void parseXmlFile() throws IOException, XmlPullParserException {
	        p = new ParserImpl(uz.getNameOftheUnzipFile());
	        p.parse();
	    }
	    
	    public ParserImpl getParser()
	    {
	    	return (ParserImpl)p;
	    }
	    
	    
}
