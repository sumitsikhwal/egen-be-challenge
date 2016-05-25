package com.sumit.java.spark.UserManagement;


	public class Address {
	     
	    private String street;
	    private String city;
	    private String zipcode;
	    private String state;
	    private String country;
	    
	     
	    public String getStreet() {
	        return street;
	    }
	    public void setStreet(String street) {
	        this.street = street;
	    }
	    public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCity() {
	        return city;
	    }
	    public void setCity(String city) {
	        this.city = city;
	    }
	    public String getZipcode() {
	        return zipcode;
	    }
	    public void setZipcode(String zip) {
	        this.zipcode = zip;
	    }
	     
	    @Override
	    public String toString(){
	        return getStreet() + ", "+getCity()+", "+getZipcode();
	    }
	}

