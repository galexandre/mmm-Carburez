package fr.istic.project.gasLocation.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.location.Location;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

public class ParserImpl implements Parser {
	 private List<Station> pvd = new ArrayList<Station>();

	    private String myFileName="";

	    private float latitude;
	    private float longitude;
	    private LocalizationInterface myLI;
	    public ParserImpl(String fileName, LocalizationInterface li){
	        this.myFileName=fileName;
	        this.myLI=li;
	    }

	    private static final String ns = null;

	    /**
	     *
	     * @throws IOException
	     * @throws XmlPullParserException
	     */
	    public void parse() throws IOException, XmlPullParserException {
	        InputStream is = new FileInputStream(Environment.getExternalStorageDirectory()+"/Download/"+this.myFileName);
	        XmlPullParser xpp = Xml.newPullParser();
	        xpp.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
	        xpp.setInput(is,null);
	        xpp.nextTag();
	        readFeed(xpp);
	        is.close();
	    }

	    /**
	     *
	     * @link : developer.android.com/training/basics/network-ops/xml.html
	     * @param parser
	     */
	    public void readFeed(XmlPullParser parser) throws IOException, XmlPullParserException {
	        parser.require(XmlPullParser.START_TAG,ns,"pdv_liste");
	        while (parser.next()!=XmlPullParser.END_TAG){
	            if(parser.getEventType() != XmlPullParser.START_TAG){
	                continue;
	            }
	            
	            String name=parser.getName();
	            Log.e("Parse","pdv: "+name);
	            if(name.equals("pdv")){
	            	Station st = readStation(parser);
	                Location locationStation = new Location("test");
	                locationStation.setLatitude(st.getLatitude());
	                locationStation.setLongitude(st.getLongitude());
	                //return distance in meter
	                float distance = myLI.getCurrentPosition().distanceTo(locationStation);
	                //Log.e("Parse","locazion: lat: "+String.valueOf(myLocation.getCurrentPosition().getLatitude()) + " long: "+myLocation.getCurrentPosition().getLongitude());
	                if(distance <= 50000){//50km
	                    Log.e("Parser","distance: "+distance);
	                    pvd.add(st);
	                }
	            }
	        }
	        Log.e("Parser","Taille de la liste:"+pvd.size());
	    }

	    public Station readStation(XmlPullParser parser) throws IOException, XmlPullParserException {
	        parser.require(XmlPullParser.START_TAG,ns,"pdv");
	        String id = parser.getAttributeValue(0);
	        Log.e("Parse","Id"+id);
	        if(parser.getAttributeValue(1).equals("")){
	            this.latitude=0;
	        }else{
	            this.latitude=Float.parseFloat(parser.getAttributeValue(1))/100000;//divide by 100 000
	        }

	        if (parser.getAttributeValue(2).equals("")){
	            longitude=0;
	        }else{
	            this.longitude= Float.parseFloat(parser.getAttributeValue(2))/100000;//divide by 100 000
	        }
	        String cp = parser.getAttributeValue(3);
	        String adress="";
	        String city="";
	        int i =0;
	        Map<String,Float> carburants=new HashMap<String,Float>();
	        List<String> services=new ArrayList();
	        while(parser.next()!= XmlPullParser.END_TAG){
	            if(parser.getEventType()!= XmlPullParser.START_TAG){
	                continue;
	            }

	            String name = parser.getName();
	            Log.e("Parser","Name: "+name);
	            if(name.equals("adresse")){
	                adress=readAdress(parser);
	            }else if (name.equals("ville")){
	                city = readCity(parser);
	            }else if (name.equals("prix")) {
	                if (parser.getAttributeCount()!=0){
	                    carburants.put(parser.getAttributeValue(0), Float.valueOf(parser.getAttributeValue(3)));
	                }else{
	                    carburants.put("null", (float) 0);
	                }
	                parser.next();
	            }else{
	                skip(parser);
	            }
	        }

	        Station st = new Station(id,longitude,latitude,city,adress, carburants,cp);
	        return st;
	    }

	    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
	        if (parser.getEventType() != XmlPullParser.START_TAG) {
	            throw new IllegalStateException();
	        }
	        int depth = 1;
	        while (depth != 0) {
	            switch (parser.next()) {
	                case XmlPullParser.END_TAG:
	                    depth--;
	                    break;
	                case XmlPullParser.START_TAG:
	                    depth++;
	                    break;
	            }
	        }
	    }

	    public String readAdress(XmlPullParser parser) throws IOException, XmlPullParserException {
	        parser.require(XmlPullParser.START_TAG, ns, "adresse");
	        String title = readText(parser);
	        parser.require(XmlPullParser.END_TAG, ns, "adresse");
	        return title;
	    }

	    public String readCity(XmlPullParser parser) throws IOException, XmlPullParserException {
	        parser.require(XmlPullParser.START_TAG, ns, "ville");
	        String name = readText(parser);
	        parser.require(XmlPullParser.END_TAG, ns, "ville");
	        return name;
	    }

	    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	        String result = "";
	        if (parser.next() == XmlPullParser.TEXT) {
	            result = parser.getText();
	            parser.nextTag();
	        }
	        return result;
	    }

	    public String getMyFileName() {
	        return myFileName;
	    }

	    public void setMyFileName(String myFileName) {
	        this.myFileName = myFileName;
	    }

	    public List<Station> getPvd() {
	        return pvd;
	    }
}
