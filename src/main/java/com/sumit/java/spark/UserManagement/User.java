package com.sumit.java.spark.UserManagement;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class User {
	  private String id ;
	  private String firstName;
	  private String lastName;
	  private String email;
	  private String City;
	  private String Zip;
	  private Address address;
	  private Date dateCreated ;
	  private String compName;
	  private String Name;
	  private String company;
	  private String compWebsite;	  
	  private String profilePic;


public User() {}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}



public Date getDateCreated() {
	return dateCreated;
}


public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}


public String getProfilePic() {
	return profilePic;
}


public void setProfilePic(String profilePic) {
	this.profilePic = profilePic;
}


}

		

