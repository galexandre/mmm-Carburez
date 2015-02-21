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
	private Double sp95Price;
	
	@DatabaseField(canBeNull=true)
	private Double sp98Price;
	
	@DatabaseField(canBeNull=true)
	private Double e85Price;
	
	@DatabaseField(canBeNull=true)
	private Double gazolePrice;
	
	@DatabaseField(canBeNull=true)
	private Double gplPrice;
	
	private Map<String, Double> mapGasPrice;
	
	public Station(){
		
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
		this.sp95Price = sp95Prix;
		this.sp98Price = sp98Prix;
		this.e85Price = e85Prix;
		this.gazolePrice = gasolePrix;
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

	public Double getSp95Price() {
		return sp95Price;
	}

	public void setSp95Price(Double sp95Price) {
		this.sp95Price = sp95Price;
	}

	public Double getSp98Price() {
		return sp98Price;
	}

	public void setSp98Price(Double sp98Price) {
		this.sp98Price = sp98Price;
	}

	public Double getE85Price() {
		return e85Price;
	}

	public void setE85Price(Double e85Price) {
		this.e85Price = e85Price;
	}

	public Double getGazolePrice() {
		return gazolePrice;
	}

	public void setGazolePrice(Double gazolePrice) {
		this.gazolePrice = gazolePrice;
	}

	public Double getGplPrice() {
		return gplPrice;
	}

	public void setGplPrice(Double gplPrice) {
		this.gplPrice = gplPrice;
	}

	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
		dest.writeDouble(sp95Price);
		dest.writeDouble(sp98Price);
		dest.writeDouble(e85Price);
		dest.writeDouble(gazolePrice);
		// TODO Adapt parceable!!!
	}

	public Map<String, Double> getGases()
	{
		if (mapGasPrice == null ) {
			mapGasPrice = new HashMap<String, Double>();
			if (sp95Price != 0.0) mapGasPrice.put("SP95", sp95Price);
			if (sp98Price != 0.0) mapGasPrice.put("SP98", sp98Price);
			if (gazolePrice != 0.0) mapGasPrice.put("Gazole", gazolePrice);
			if (e85Price != 0.0) mapGasPrice.put("E85", e85Price);
//			if (gplPrice != 0.0) mapGasPrice.put("GPL", sp95Price);  TODO GPL?
		}
		
		return mapGasPrice;
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
		this.sp95Price = in.readDouble();
		this.sp98Price = in.readDouble();
		this.e85Price = in.readDouble();
		this.gazolePrice = in.readDouble();
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
