package timo;

import java.io.Serializable;

public class SmartPost implements Serializable {
	/**
	 * SmartPost info from XML, inner class for Geopoint data.
	 * */
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
		return this.city;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCode() {
		return this.code;
	}

	public String getAvailability() {
		return this.availability;
	}

	public String getPostoffice() {
		return this.postoffice;
	}

	public Geopoint getGp() {
		return this.gp;
	}
	
	@Override
	public String toString() {
		return this.city + " " + this.postoffice;
	}

	class Geopoint implements Serializable {
		private Float lat;
		private Float lng;
		
		public Geopoint(String latitude, String longitude) {
			try {
				Float lat = Float.parseFloat(latitude);
				Float lng = Float.parseFloat(longitude);
				this.lat = lat;
				this.lng = lng;
			} catch (NumberFormatException e) {
				// We screwed dawg.
			}
		}

		public Float getLat() {
			return lat;
		}

		public Float getLng() {
			return lng;
		}
		
	}
}
