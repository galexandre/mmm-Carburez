package fr.istic.project.gasLocation.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

public class DataRetrievingImpl implements DataRetrieving {
	
	private Context myContext;
    private String url;
    private String fileLocation;

    /**
     *
     * @param ctx a Context
     * @param url an Url where is stored the data.
     */
    public DataRetrievingImpl(Context ctx, String url){
        this.myContext=ctx;
        this.url=url;
        this.fileLocation="/Download";
    }

    /**
     * Method which give a context to the class
     * @param myContext
     */
    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }

    /**
     * Download the data from the website
     */
    public void download(){
        DownloadManager.Request req = new DownloadManager.Request(Uri.parse(this.url));
        req.setTitle("Téléchargement des stations en cours...");
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            req.allowScanningByMediaScanner();
            req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        req.setDestinationInExternalPublicDir(fileLocation, "data.zip");
        DownloadManager manager = (DownloadManager) this.myContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(req);
    }

    /**
     * Return the location of the zip file
     * @return the fileLocation : String
     */
    public String getFileLocation() {
        return fileLocation;
    }


    /**
     * Give a location of the zip file
     * @param fileLocation : String
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

}
