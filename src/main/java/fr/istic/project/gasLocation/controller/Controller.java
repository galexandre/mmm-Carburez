package fr.istic.project.gasLocation.controller;

import java.util.List;

public class Controller {
	
	DownloadController myDC;
	
	public Controller(DownloadController c){
		this.myDC=c;
	}
	
	public List<fr.istic.project.gasLocation.services.Station> getAllStations(){
        return this.myDC.getParser().getPvd();
    }

    public List<Float> getDistance(){
        return this.myDC.getParser().getDistances();
    }
}
