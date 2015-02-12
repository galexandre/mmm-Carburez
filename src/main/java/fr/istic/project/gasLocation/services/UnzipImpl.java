package fr.istic.project.gasLocation.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.os.Environment;
import android.util.Log;

public class UnzipImpl implements Unzip{
	 private String locationZipFile;
	    private String nameOfFile;
	    private InputStream is;
	    private ZipInputStream zi;
	    private ZipEntry ze=null;
	    private byte[] buff = new byte[1024];
	    private int i=0;
	    private String nameOftheUnzipFile="";

	    public UnzipImpl(String dataLocation, String nameOfZipFile){
	        this.locationZipFile=dataLocation;
	        this.nameOfFile=Environment.getExternalStorageDirectory().toString()+locationZipFile+"/"+nameOfZipFile;
	    }

	    public void unzipData() throws IOException {
	        Log.e("Zip","Starting unzip: "+nameOfFile);
	        is=new FileInputStream(nameOfFile);
	        zi = new ZipInputStream(is);


	        ze=zi.getNextEntry();
	        this.nameOftheUnzipFile=ze.getName();
	        Log.e("Zip","Name of the file: "+this.nameOftheUnzipFile);
	        while(ze!=null){
	            FileOutputStream foutput = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+this.locationZipFile+"/"+ze.getName());
	            while ((i=zi.read(this.buff))!=-1){
	                foutput.write(this.buff,0,i);
	            }
	            zi.closeEntry();
	            foutput.close();
	            ze=zi.getNextEntry();
	        }
	        zi.close();
	    }

	    public void deleteZipFile(){
	        File dir = new File(this.nameOfFile);
	        dir.delete();
	    }

	    /**
	     * Delete all the files in the path
	     */
	    public void deleteAllFiles(){
	        //Just see the differents files in the current directory
	        String path = Environment.getExternalStorageDirectory().toString()+this.locationZipFile;
	        File f = new File(path);
	        File files[] = f.listFiles();
	        for(int k=0; k<files.length;k++){
	            File e = files[k];
	            e.delete();
	        }
	    }


	    public String getNameOftheUnzipFile() {
	        return nameOftheUnzipFile;
	    }


}
