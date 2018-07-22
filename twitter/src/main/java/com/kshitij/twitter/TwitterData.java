package com.kshitij.twitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.json.DataObjectFactory;

public class TwitterData {

	public static void getTwitterData(Twitter authObject, String topic) {
		System.out.println("Inside tweet data method: "+authObject + " , "+topic);
		int i = 0;
		int j = 0;
		try {
			Query query = new Query(topic);
			QueryResult result = null;
			do {
				result = authObject.search(query);
				List<Status> tweets = result.getTweets();
				for(Status tweet: tweets) {
					j++;
					JSONObject jsonObject = getTweetObject(tweet);
					EsUtil.insertIntoES("localhost",9200, "twitter","tweets", jsonObject.get("id").toString(), jsonObject);
					if(j%200==0) {
						System.out.println("total number of tweets:  "+j);
						//System.out.println(x);
					}
				}
				i++;
				System.out.println("Inserted"+ i+ "batch of tweets");
			} 
			while (result.hasNext() == true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to search tweets: " + e.getMessage());
		}
		//System.out.println(tweetList.size());
		//return tweetList;
	}
	
	public static JSONObject getTweetObject(Status tweet) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", Long.toString(tweet.getId()));
		jsonObject.put("createdAt", tweet.getCreatedAt().toString());
		jsonObject.put("text",tweet.getText());
		jsonObject.put("user",tweet.getUser());
		
		return jsonObject;
	}
}