package semesterproj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jibble.pircbot.*;
import com.google.gson.*;

public class MyBotMain extends PircBot {

	public static void main(String[] args) throws Exception {
		
		// Start up the bot
		MyBot bot = new MyBot();
		
		// Enable debugging output
		bot.setVerbose(true);
		
		// Connect to IRC server
		try {
			bot.connect("irc.freenode.net");
		} catch (NickAlreadyInUseException e) {
			System.out.println("Exception: NickAlreadyInUse");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception: IOException");
			e.printStackTrace();
		} catch (IrcException e) {
			System.out.println("Exception: IrcException");
			e.printStackTrace();
		}
		
		// Join the #pircbot channel
		bot.joinChannel("#pircbot");	

		
	}
	
	// Returns current temperature in a city
	public static double getCurrentTemp(int city) throws Exception{
		
		// Setting up the API url
		String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?zip=";
		String userInput = city + ",us";
		String imperialUnits = "&units=imperial";
		String myAPItoken = "&APPID=b3126555552ac8778dbb2a7bdfbb1cc9";
		String weatherURL = myAPIurl + userInput + imperialUnits + myAPItoken;
		
		//GET request
		URL url = new URL(weatherURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String result = org.apache.commons.io.IOUtils.toString(rd);
		
		// Parsing JSON object
		JsonObject object = new JsonParser().parse(result).getAsJsonObject();
		JsonObject main = object.getAsJsonObject("main");
		double currentTemp = main.get("temp").getAsDouble();
		
		return currentTemp;
		
	}
	
	// Returns the max temperature in a city
	static double getMaxTemp(int city) throws Exception {
		
		// Setting up the API url
		String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?zip=";
		String userInput = city + ",us";
		String imperialUnits = "&units=imperial";
		String myAPItoken = "&APPID=b3126555552ac8778dbb2a7bdfbb1cc9";
		String weatherURL = myAPIurl + userInput + imperialUnits + myAPItoken;
		
		//GET request
		URL url = new URL(weatherURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String result = org.apache.commons.io.IOUtils.toString(rd);
		
		// Parsing JSON object
		JsonObject object = new JsonParser().parse(result).getAsJsonObject();
		JsonObject main = object.getAsJsonObject("main");
		double maxTemp = main.get("temp_max").getAsDouble();
		
		return maxTemp;
		
	}
	
	// Returns the low temperature in a city
	static double getMinTemp(int city) throws Exception {
		
		// Setting up the API url
		String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?zip=";
		String userInput = city + ",us";
		String imperialUnits = "&units=imperial";
		String myAPItoken = "&APPID=b3126555552ac8778dbb2a7bdfbb1cc9";
		String weatherURL = myAPIurl + userInput + imperialUnits + myAPItoken;
		
		//GET request
		URL url = new URL(weatherURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String result = org.apache.commons.io.IOUtils.toString(rd);
		
		// Parsing JSON object
		JsonObject object = new JsonParser().parse(result).getAsJsonObject();
		JsonObject main = object.getAsJsonObject("main");
		double minTemp = main.get("temp_min").getAsDouble();
		
		return minTemp;
		
	}
	
	// Returns the name of the city
	static String getCityName(int city) throws Exception {
		
		// Setting up the API url
		String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?zip=";
		String userInput = city + ",us";
		String imperialUnits = "&units=imperial";
		String myAPItoken = "&APPID=b3126555552ac8778dbb2a7bdfbb1cc9";
		String weatherURL = myAPIurl + userInput + imperialUnits + myAPItoken;
		
		//GET request
		URL url = new URL(weatherURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String result = org.apache.commons.io.IOUtils.toString(rd);
		
		// Parsing JSON object
		JsonObject object = new JsonParser().parse(result).getAsJsonObject();
		String cityName = object.get("name").getAsString();
		
		return cityName;
		
	}
	
	static String getRandomZipCode(int city) throws Exception {
		
		int randomZip = (int)(Math.random() * ((10 - 0) + 1));
		
		// Setting up the API url
		String myAPIurl = "https://www.zipcodeapi.com/rest/";
		String myAPItoken = "1qN0S3LHFnV8tLM423ni7LiY6xkXf97b1X6CIG6AE6KyBAtYkFU2P9HhZkgn1EFI";
		String aPIurlEnd = "/radius.json/75040/10/mile";
		String zipURL = myAPIurl + myAPItoken + aPIurlEnd;
		
		//GET request
		URL url = new URL(zipURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String result = org.apache.commons.io.IOUtils.toString(rd);
		
		// Parsing JSON object
		JsonObject object = new JsonParser().parse(result).getAsJsonObject();
		JsonArray zipCodesArray = object.getAsJsonArray("zip_codes");
		JsonObject getZip = zipCodesArray.get(randomZip).getAsJsonObject();
		String cityName = getZip.get("zip_code").getAsString();
		
		return cityName;
	}
	
}
