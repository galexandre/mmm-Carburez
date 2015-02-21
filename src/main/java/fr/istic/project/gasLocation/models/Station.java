package fr.istic.project.gasLocation.models;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



/**
 * Classe to manage station objects
 * @author sylvain
 *
 */

@DatabaseTable(tableName="Station")
public class Station implements Parcelable{
	
	private Map<String, Double> gases;
	
	@DatabaseField(generatedId=true)
	private int idStation;
	
	@DatabaseField(canBeNull=false)
	private double latitude;
	
	@DatabaseField(canBeNull=false)
	private double longitude;
	
	@DatabaseField(canBeNull=false)
	private String postalCode;
	
	@DatabaseField(canBeNull=false)
	private String address;
	
	@DatabaseField(canBeNull=false)
	private String town;
	
	@DatabaseField(canBeNull=false)
	private String startHour;
	
	@DatabaseField(canBeNull=false)
	private String endHours;
	
	@DatabaseField(canBeNull=true)
	private String exceptDays;
	
	@DatabaseField(canBeNull=true)
	private String services;
	
	@DatabaseField(canBeNull=false)
	private Double sp95Prix;
	
	@DatabaseField(canBeNull=true)
	private Double sp98Prix;
	
	@DatabaseField(canBeNull=true)
	private Double e85Prix;
	
	@DatabaseField(canBeNull=true)
	private Double gasolePrix;
	
	@DatabaseField(canBeNull=true)
	private Double gplPrix;
	
	//private ArrayList<Gas> gasList = new ArrayList<Gas>();
	
	public Station(){
		this.gases = new HashMap<String, Double>();
	}

	public Station(double latitude, double longitude,
			String postalCode, String address, String town, String startHour,
			String endHours, String exceptDays, String services, Double sp95Prix, Double sp98Prix, Double e85Prix, Double gasolePrix, Double gplPrix) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.postalCode = postalCode;
		this.address = address;
		this.town = town;
		this.startHour = startHour;
		this.endHours = endHours;
		this.exceptDays = exceptDays;
		this.services = services;
		this.sp95Prix = sp95Prix;
		this.sp98Prix = sp98Prix;
		this.e85Prix = e85Prix;
		this.gasolePrix = gasolePrix;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setCodePostal(String codePostal) {
		this.postalCode = codePostal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getEndHours() {
		return endHours;
	}
	public void setEndHours(String endHours) {
		this.endHours = endHours;
	}
	public String getExceptDays() {
		return exceptDays;
	}
	public void setExceptDays(String exceptDays) {
		this.exceptDays = exceptDays;
	}
	public String getServices() {
		return services;
	}
	
	public int getIdStation()
	{
		return idStation;
	}
	public void setServices(String services) {
		this.services = services;
	}
	
	

	public Double getGplPrix() {
		return gplPrix;
	}

	public void setGplPrix(Double gplPrix) {
		this.gplPrix = gplPrix;
	}

	public Double getSp95Prix() {
		return sp95Prix;
	}

	public void setSp95Prix(Double sp95Prix) {
		this.sp95Prix = sp95Prix;
	}

	public Double getSp98Prix() {
		return sp98Prix;
	}

	public void setSp98Prix(Double sp98Prix) {
		this.sp98Prix = sp98Prix;
	}

	public Double getE85Prix() {
		return e85Prix;
	}

	public void setE85Prix(Double e85Prix) {
		this.e85Prix = e85Prix;
	}

	public Double getGasolePrix() {
		return gasolePrix;
	}

	public void setGasolePrix(Double gasolePrix) {
		this.gasolePrix = gasolePrix;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
//		dest.writeInt(idStation);
		dest.writeDouble(latitude);
		dest.writeDouble(longitude);
		dest.writeString(postalCode);
		dest.writeString(address);
		dest.writeString(town);
		dest.writeString(startHour);
		dest.writeString(endHours);
		dest.writeString(exceptDays);
		dest.writeString(services);
	}

	public Map<String, Double> getGases()
	{
		return this.gases;
	}
	
    private Station(Parcel in){
		this.latitude = in.readDouble();
		this.longitude = in.readDouble();
		this.postalCode = in.readString();
		this.address = in.readString();
		this.town = in.readString();
		this.startHour = in.readString();
		this.endHours = in.readString();
		this.exceptDays = in.readString();
		this.services = in.readString();
    }
 
    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
 
        public Station createFromParcel(Parcel source) {
            return new Station(source);
        }
 
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
	
}
