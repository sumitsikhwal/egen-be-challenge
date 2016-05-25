package com.sumit.java.spark.UserManagement;

import java.util.*;

import javax.json.Json;
import javax.json.JsonReader;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
/*This class contains the implementation of all the rest api operations.It makes insert,update and select data operations on
 * mongodb.
 */
public class UserService {
	private final DB db;
    private final DBCollection collection;
  
	public UserService(DB db) {
		this.db = db;
		this.collection = db.getCollection("users");
		  
	}
	/**
     * Method to find all users
     *            		           
     * @return users
     * 			  : List - List of users	           
     */

	public List<DBObject> getAllUsers() {
		List<DBObject> users = new ArrayList<>();
		DBCursor dbObjects = collection.find();
		DBObject dbObject = null;
        while (dbObjects.hasNext()) {
             dbObject = dbObjects.next();            
            if (dbObject.get("_id") == null){
            	return null;
            }
            users.add(dbObject);
        }      
        return users;		
	}
	/**
     * Method to insert a user
     * @param    
     * 			 : String - Json String 	           		           
     * @return 
     * 			  : String  	           
     */
	public String insertUser(String body) {
		Date dateCreated = new Date();		
		User user = new User();
		JSONObject jsonObj = new JSONObject(body);
		user.setId(jsonObj.getString("id"));
		user.setFirstName(jsonObj.getString("firstName"));
		user.setLastName(jsonObj.getString("lastName"));
		user.setEmail(jsonObj.getString("email"));
		Address address = new Address(); 
		JSONObject innerJsonObject = jsonObj.getJSONObject("address");
		address.setStreet(innerJsonObject.getString("street"));
		address.setCity(innerJsonObject.getString("city"));
		address.setZipcode(innerJsonObject.getString("zip"));
		address.setState(innerJsonObject.getString("state"));
		address.setCountry(innerJsonObject.getString("country"));
		Company company = new Company();
		JSONObject innerJsonObject1 = jsonObj.getJSONObject("company");
		company.setName(innerJsonObject1.getString("name"));
		company.setWebsite(innerJsonObject1.getString("website"));
		user.setProfilePic(jsonObj.getString("profilePic"));
		BasicDBObject whereQuery = new BasicDBObject();		 
		whereQuery.put("_id", user.getId());
        DBCursor cursor = collection.find(whereQuery);
        if(cursor.hasNext()) {
            return "Duplicate Record";
        }
        Map<String, Object> documentMap = new HashMap<String, Object>();       
        documentMap.put("_id", user.getId());
        documentMap.put("firstName",user.getFirstName());
        documentMap.put("lastName",user.getLastName());
        documentMap.put("email",user.getEmail());
        Map<String, Object> addrDetailMap = new HashMap<String, Object>();
        addrDetailMap.put("street",address.getStreet());
        addrDetailMap.put("city",address.getCity());
        addrDetailMap.put("zip",address.getZipcode());
        addrDetailMap.put("state",address.getState());
        addrDetailMap.put("state",address.getCountry());
        documentMap.put("address",addrDetailMap);
        documentMap.put("Date",dateCreated);
        Map<String, Object> compDetailMap = new HashMap<String, Object>();
        compDetailMap.put("name",company.getName());
        compDetailMap.put("website",company.getWebsite());
        documentMap.put("company",compDetailMap);
        documentMap.put("profilePic", user.getProfilePic());
        
        collection.insert(new BasicDBObject(documentMap));
        return "User Inserted";
	
	}
	/**
     * Method to update a user
     * @param    
     * 			 : String - Json String 	           		           
     * @return 
     * 			  : String  	           
     */
	public String updateUser(String body) {
		User user = new User();
		JSONObject jsonObj = new JSONObject(body);
		BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", jsonObj.getString("id"));
        BasicDBObject searchQuery = new BasicDBObject().append("_id",jsonObj.getString("id"));
        DBCursor cursor = collection.find(whereQuery);
    	BasicDBObject newDocument = new BasicDBObject();
        if(cursor.hasNext()) {
            user.setFirstName(jsonObj.getString("firstName"));
    		user.setLastName(jsonObj.getString("lastName"));
    		user.setEmail(jsonObj.getString("email"));
    		Address address = new Address(); 
    		JSONObject innerJsonObject = jsonObj.getJSONObject("address");
    		address.setStreet(innerJsonObject.getString("street"));
    		address.setCity(innerJsonObject.getString("city"));
    		address.setZipcode(innerJsonObject.getString("zip"));
    		address.setState(innerJsonObject.getString("state"));
    		address.setCountry(innerJsonObject.getString("country"));
    		Company company = new Company();
    		JSONObject innerJsonObject1 = jsonObj.getJSONObject("company");
    		company.setName(innerJsonObject1.getString("name"));
    		company.setWebsite(innerJsonObject1.getString("website"));
    		user.setProfilePic(jsonObj.getString("profilePic"));
            Map<String, Object> documentMap = new HashMap<String, Object>();
            documentMap.put("firstName",user.getFirstName());
            documentMap.put("lastName",user.getLastName());
            documentMap.put("email",user.getEmail());
            Map<String, Object> addrDetailMap = new HashMap<String, Object>();
            addrDetailMap.put("street",address.getStreet());
            addrDetailMap.put("city",address.getCity());
            addrDetailMap.put("zip",address.getZipcode());
            addrDetailMap.put("state",address.getState());
            addrDetailMap.put("state",address.getCountry());
            documentMap.put("address",addrDetailMap);
            Map<String, Object> compDetailMap = new HashMap<String, Object>();
            compDetailMap.put("name",company.getName());
            compDetailMap.put("website",company.getWebsite());
            documentMap.put("company",compDetailMap);
            documentMap.put("profilePic", user.getProfilePic());           
            collection.update(searchQuery, new BasicDBObject(documentMap));
            
        }
        else{
        	return "User not found";
     
        }		
		  return "User Updated";
	}
	
}

