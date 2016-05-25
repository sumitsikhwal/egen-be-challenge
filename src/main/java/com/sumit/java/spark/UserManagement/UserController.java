package com.sumit.java.spark.UserManagement;

import static spark.Spark.*;
import com.sumit.java.spark.UserManagement.*;

/* This class reads all the get,put,post requests from the client and returns the response back to the client
 */

public class UserController {
		
	public UserController(final UserService userservice) {
	     
		get("/users", "application/json",(req, res) -> userservice.getAllUsers(), new JsonConverter());

			
		post("/users/insertuser", "application/json", (req, res) -> userservice.insertUser(req.body()),
		new JsonConverter());
		
		put("/users/updateuser", "application/json", (req, res) -> {String retStr = userservice.updateUser(req.body());
				if (retStr == "User not found"){
					res.status(404);
					return ("user not found");
				}
				return retStr;},
				new JsonConverter());		
			
	}
}
	

