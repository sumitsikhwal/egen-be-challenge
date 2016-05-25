package com.sumit.java.spark.UserManagement;


	import com.mongodb.*;

	import spark.Route;

	import static spark.Spark.setIpAddress;
	import static spark.Spark.setPort;
	import static spark.SparkBase.staticFileLocation;

	import spark.Request;
	import spark.Response;
	import spark.Route;
	 
	import static spark.Spark.*;
	/*This class starts the jetty server and connects to mongo db database.
	 */
	public class Main {
		

	    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
	    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 4567;
	 
	    public static void main(String[] args) throws Exception {
	        setIpAddress(IP_ADDRESS);
	        setPort(PORT);
	        staticFileLocation("/public");
	        new UserController(new UserService(mongo()));	
	    }
	 
	    private static DB mongo() throws Exception {
	        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
	        if (host == null) {
	            MongoClient mongoClient = new MongoClient("localhost");
	            return mongoClient.getDB("UserManagement");
	        }
	        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
	        String dbname = System.getenv("OPENSHIFT_APP_NAME");
	        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
	        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
	        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
	        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
	        mongoClient.setWriteConcern(WriteConcern.SAFE);
	        DB db = mongoClient.getDB(dbname);
	        if (db.authenticate(username, password.toCharArray())) {
	            return db;
	        } else {
	            throw new RuntimeException(" Unable to authenticate with MongoDB");
	        }
	    }
	    
	}



	