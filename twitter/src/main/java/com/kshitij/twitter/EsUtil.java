package com.kshitij.twitter;

import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.network.InetAddresses;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONObject;

public class EsUtil {
	
	public static void insertIntoES(String hostName, int port, String index, String type,String id, JSONObject jsonObject) throws IOException {
		RestClient restClient = RestClient.builder( new HttpHost(hostName, port, "http")).build();
		HttpEntity entity = new NStringEntity(jsonObject.toString(),ContentType.APPLICATION_JSON);
		Response indexResponse = restClient.performRequest("PUT","/"+index+"/"+type+"/"+id,Collections.<String, String>emptyMap(),entity);
		//System.out.println("/"+index+"/"+type+"/"+id);
		//System.out.println("Inserted in ES");
	}
}
