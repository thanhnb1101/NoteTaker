package thanhnotes.com.notetaker.modules.checkin;

/**
 * Created by Jen's on 4/6/2016.
 */
public class CheckIn {
    //private variables
    String _note;
    String _address;
    Double _longitude;
    Double _latitude;

    // Empty constructor
    public CheckIn(){

    }
    // constructor
    public CheckIn( String note, String address, Double longitude, Double latitude){
        this._note = note;
        this._address = address;
        this._longitude = longitude;
        this._latitude = latitude;
    }

    // getting note
    public String getNote(){
        return this._note;
    }

    // setting note
    public void setNote(String note){
        this._note = note;
    }

    // getting address
    public String getAddress(){
        return this._address;
    }

    // setting address
    public void setAddress(String address){
        this._address = address;
    }

    // getting longitude
    public Double getLongitude(){
        return this._longitude;
    }

    // setting longitude
    public void setLongitude(Double longitude){
        this._longitude = longitude;
    }

    // getting latitude
    public Double getLatitude(){
        return this._latitude;
    }

    // setting latitude
    public void setLatitude(Double latitude){
        this._latitude = latitude;
    }
}
