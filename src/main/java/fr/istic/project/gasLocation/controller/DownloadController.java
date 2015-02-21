package fr.istic.project.gasLocation.controller;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import fr.istic.project.gasLocation.dao.DatabaseHelper;
import fr.istic.project.gasLocation.models.Station;
import fr.istic.project.gasLocation.services.DataRetrieving;
import fr.istic.project.gasLocation.services.DataRetrievingImpl;
import fr.istic.project.gasLocation.services.LocalizationInterface;
import fr.istic.project.gasLocation.services.Parser;
import fr.istic.project.gasLocation.services.ParserImpl;
import fr.istic.project.gasLocation.services.Unzip;
import fr.istic.project.gasLocation.services.UnzipImpl;
import fr.istic.project.gasLocation.services.location.Localization;

public class DownloadController {
	 private Context myContext;
	    private DataRetrieving dd;
	    private Unzip uz;
	    private Parser p;
	    private String url;
	    DatabaseHelper helper;
	    private LocalizationInterface li;
	    
	    /**
	     * Contructor of a controller
	     * @param ctx a Context (android.content.Context)
	     */
	    public DownloadController(Context ctx, String url){
	        this.myContext=ctx;
	        this.url=url;
	        li = new Localization(ctx);
	        helper = OpenHelperManager.getHelper(myContext, DatabaseHelper.class);
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
	    	persistData();
	    	deleteZipFile();
	    }
	    
	    public DatabaseHelper getDatabaseHelper(){
	    	return helper;
	    }
	    
	    private void persistData(){
	        ParserImpl p = getParser();
	         for(fr.istic.project.gasLocation.services.Station s : p.getPvd()){
	         	Station stat = new Station((double)s.getLatitude(), (double)s.getLongitude(), s.getZipcode(), s.getAdress(), s.getCity(), "", "", "", "", s.getSp95Prix(), s.getSp98Prix(), s.getE85Prix(), s.getGasolePrix(),  s.getGplPrix());
	         	helper.addStation(stat);
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
	        uz.deleteZipFile();
	    }

	    public void parseXmlFile() throws IOException, XmlPullParserException {
	        p = new ParserImpl(uz.getNameOftheUnzipFile(),li);
	        p.parse();
	    }
	    
	    public ParserImpl getParser()
	    {
	    	return (ParserImpl)p;
	    }
	    
	    
}
