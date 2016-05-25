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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Spark;

public class InsertUserTest {
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
	public void NewUserShouldBeCreated() throws ParseException {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:4567/users/insertuser");				
			StringEntity input = new StringEntity("{\"id\":\"752589\",\"firstName\":\"Chataham\",\"lastName\":\"Siki\","
			+"\"email\":\"xyz\",\"address\":{\"street\":\"xz\",\"city\":\"xz\",\"zip\":\"xz\",\"state\":\"xz\","
			+"\"country\":\"xz\"},\"company\":{\"name\":\"xz\",\"website\":\"xz\"},\"profilePic\":\"xz\"}");
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
		                new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


