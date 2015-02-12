package fr.istic.project.gasLocation.Controller;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import fr.istic.project.gasLocation.services.DataRetrieving;
import fr.istic.project.gasLocation.services.DataRetrievingImpl;
import fr.istic.project.gasLocation.services.Parser;
import fr.istic.project.gasLocation.services.ParserImpl;
import fr.istic.project.gasLocation.services.Unzip;
import fr.istic.project.gasLocation.services.UnzipImpl;
import android.content.Context;

public class Controller {
	 private Context myContext;
	    private DataRetrieving dd;
	    private Unzip uz;
	    private Parser p;
	    /**
	     * Contructor of a controller
	     * @param ctx a Context (android.content.Context)
	     */
	    public Controller(Context ctx){
	        this.myContext=ctx;
	    }

	    /**
	     * Donwload data from the url in parameter
	     * @param url : String
	     */
	    public void DownloadData(String url){
	        dd = new DataRetrievingImpl(myContext,url);
	        dd.download();
	    }

	    public void UnzipFile() throws IOException {
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
}
