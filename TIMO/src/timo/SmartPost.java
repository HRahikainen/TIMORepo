package timo;

public class SmartPost {
	private String code;
	private String city;
	private String address;
	private String availability;
	private String postoffice;
	private String lat;
	private String lng;
	// Osaluokka GeoPoint?
	
	public SmartPost(String code, String city, String addr, String avail, String po, String lat, String lng) {
		this.code = code;
		this.city = city;
		this.address= addr;
		this.availability = avail;
		this.postoffice = po;
		this.lat = lat;
		this.lng = lng;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

}
