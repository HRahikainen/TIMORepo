package timo;

public class SmartPost {
	private String code;
	private String city;
	private String address;
	private String availability;
	private String postoffice;
	private Geopoint gp;

	
	public SmartPost(String code, String city, String addr, String avail, String po, String lat, String lng) {
		this.code = code;
		this.city = city;
		this.address= addr;
		this.availability = avail;
		this.postoffice = po;
		this.gp = new Geopoint(lat, lng);
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getCode() {
		return code;
	}

	public String getAvailability() {
		return availability;
	}

	public String getPostoffice() {
		return postoffice;
	}

	public Geopoint getGp() {
		return gp;
	}

	class Geopoint {
		private String lat;
		private String lng;
		
		public Geopoint(String lat, String lng) {
			this.lat = lat;
			this.lng = lng;
		}
	}
}
