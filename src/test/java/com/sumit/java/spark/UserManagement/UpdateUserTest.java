package com.sumit.java.spark.UserManagement;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.MalformedURLException;

	import org.apache.http.HttpResponse;
	import org.apache.http.client.ClientProtocolException;
	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.client.methods.HttpPost;
	import org.apache.http.client.methods.HttpPut;
	import org.apache.http.entity.StringEntity;
	import org.apache.http.impl.client.DefaultHttpClient;
	import org.json.JSONObject;
	import org.junit.AfterClass;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;

	import spark.Spark;

	public class UpdateUserTest  {
		@BeforeClass
		public static void beforeClass() {
			try {
				Main.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@AfterClass
		public static void afterClass() {
			Spark.stop();
		}

		@Test
		public void UserShouldBeUpdated() throws ParseException {
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPut putRequest = new HttpPut("http://localhost:4567/users/updateuser");
				
				StringEntity input = new StringEntity("{\"id\":\"7525223\",\"firstName\":\"update\",\"lastName\":\"Siki\","
						+"\"email\":\"update\",\"address\":{\"street\":\"update\",\"city\":\"update\",\"zip\":\"Mccallum\",\"state\":\"xz\","
						+"\"country\":\"xz\"},\"company\":{\"name\":\"Courts\",\"website\":\"Chatham\"},\"profilePic\":\"xz\"}");
				input.setContentType("application/json");
				putRequest.setEntity(input);

				HttpResponse response = httpClient.execute(putRequest);

				BufferedReader br = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));

				String output;
				
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
				}
				
				httpClient.getConnectionManager().shutdown();
				
			  } catch (MalformedURLException e) {

				e.printStackTrace();
			
			  } catch (IOException e) {

				e.printStackTrace();

			  }
	}
	}


