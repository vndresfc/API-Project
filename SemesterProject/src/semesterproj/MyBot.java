package semesterproj;

import org.jibble.pircbot.*;

public class MyBot extends PircBot{

	
	// Constructor with bot name
	public MyBot(){
		this.setName("myWeatherBot");
	}
	
	// Function when someone sends a message in the IRC channel
	public void onMessage(String channel, String sender, String login, String hotname, String message) {
		
		// When someone sends "time"
		if(message.equalsIgnoreCase("time")) {
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": the time is now " + time);
		}
		
		// When someone sends "hello"
		if(message.equalsIgnoreCase("hello")) {
			sendMessage("#pircbot", "Hello, " + sender + "! ");
		}
		
		if(message.contains("zip") || message.contains("Zip")) {
			
			// Removes everything except the zip code from the message String
			String clean = message.replaceAll("\\D+", "");
			int cityZip = Integer.parseInt(clean);
			
			try {
				sendMessage("#pircbot", "Here's a random zipcode near " + MyBotMain.getCityName(cityZip) + ": " + MyBotMain.getRandomZipCode(cityZip));
			}
			catch (Exception e) {
				sendMessage("#pircbot", "Something went wrong...");
				e.printStackTrace();
			}
		}
		
		// When someone's message contains the word "weather"
		if(message.contains("weather") || message.contains("Weather")) {
			
			// Removes everything except the zip code from the message String
			String clean = message.replaceAll("\\D+", "");
			int cityZip = Integer.parseInt(clean);
			
			// Output of weather info
			try {
				sendMessage("#pircbot", "The current temperature in " + MyBotMain.getCityName(cityZip) + " is " + MyBotMain.getCurrentTemp(cityZip) + "°F"
						+ " with a high of " + MyBotMain.getMaxTemp(cityZip) + "°F" + " and a low of " + MyBotMain.getMinTemp(cityZip) + "°F");
			} catch (Exception e) {
				sendMessage("#pircbot", "Something went wrong...");
				e.printStackTrace();
			}
		
		}
		
		// Bot exits the server when someone types "quit"
		if(message.equalsIgnoreCase("quit")) {
			quitServer();
		}
	
	}
}
