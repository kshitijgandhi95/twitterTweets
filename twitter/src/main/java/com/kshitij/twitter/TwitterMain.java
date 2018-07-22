package com.kshitij.twitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import twitter4j.Twitter;

public class TwitterMain {
	private static String AccessToken = "790420428128788481-pxcGw64IQRygQDfa335s8YQC5NNFhIV";
	private static String AccessSecret = "5a8WfBl9AvGlzQaiUPSTEXW9hknfLgjvahv158pJPvUbL";
	private static String ConsumerKey = "s5qWW9ApEfRfUOfqpEQd9n55y";
	private static String ConsumerSecret = "vAlaJi4BxTGpDCqqgdXofNlhczXKFQvYyER0sWWnl2ZDPkUVOU";

	private static HashMap authTokenMap = new HashMap();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		authTokenMap.put("AccessToken", AccessToken);
		authTokenMap.put("AccessSecret", AccessSecret);
		authTokenMap.put("ConsumerKey", ConsumerKey);
		authTokenMap.put("ConsumerSecret", ConsumerSecret);

		TwitterAuth twitterAuth = new TwitterAuth();
		TwitterData twitterData = new TwitterData();
		Twitter authObject = null;
		
		String topic = "USA";
		ArrayList tweets = new ArrayList();
		try {
			authObject = twitterAuth.authenticate(authTokenMap);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		try {
			twitterData.getTwitterData(authObject, topic);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		//System.out.println("Tweets related to " + topic + " : " + tweets);

	}

}