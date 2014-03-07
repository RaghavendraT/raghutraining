package com.commodities.client;

import java.io.IOException;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.ws.rs.core.MediaType;

import com.commodities.common.CommodityData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CommodityClient {
	  public static void addEntryUI (CommodityData newData) 
	    {
		System.out.println("got it here");
		// Create client configuration
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
									Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		// Create Web-resources //need to change
		WebResource webResource = client
			.resource("http://localhost:8080/commodities-1/rest/commodityService/add");
		
		//create client response 
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class , newData);
		
		//check response from server
		if ( response.getStatus() != 201)
		{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
		
		//if all well, get response from server
		System.out.println("Response from server.... "+ response.getEntity(String.class));
		
	    }
	  
	  //view entry of CommodityData
	   public static  CommodityData viewEntryUI (final long temp_id ) throws IOException
	    {
		
		// Create client config for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,	Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		// Create Web resources
		WebResource webResource = client
				.resource("http://localhost:8080/commodities-1/rest/commodityService/getById?id="+temp_id);
				
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class );
		
		//check response from server
		if ( response.getStatus() != 200)
		{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
			
			
		//if all well, get response from server
		return response.getEntity(CommodityData.class);

	    }


	 //view entry of CommodityData by Name
	   public static CommodityData viewEntryUI (final String temp_name ) throws IOException
	    {
		
		// Create client config for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,	Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		// Create Web resources
		WebResource webResource = client
				.resource("http://localhost:8080/commodities-1/rest/commodityService/getByName?name="+temp_name);
				
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class );
		
		//check response from server
		if ( response.getStatus() != 200)
		{
		    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
			
			
		//if all well, get response from server
		return response.getEntity(CommodityData.class);

	    }


	   //update CommodityData data
	   public static void updateEntryUI (final long temp_id, final CommodityData updatedCommodityData) throws IOException 
	   {
		// Create client config for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

			// Create Web resources
		WebResource webResource = client
			.resource("http://localhost:8080/commodities-1/rest/commodityService/update?id="+temp_id);
		
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).put(ClientResponse.class , updatedCommodityData);
		
		//check response from server
		if ( response.getStatus() != 200)
		{
			    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
			
			
		//if all well, get response from server
		System.out.println("Response from server.... "+ response.getEntity(String.class));
		
	   }

	   //Delete entry of CommodityData
	   public static void deleteEntryUI (final long temp_id) throws IOException
	   {
		// Create client config for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
							Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		
		// Create Web resources
		WebResource webResource = client
				.resource("http://localhost:8080/commodities-1/rest/commodityService/delete?id="+temp_id);
				
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).delete(ClientResponse.class  );
		//check response from server
		if ( response.getStatus() != 200)
		{
			    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}

	   }


	   public static List<CommodityData> listOfAllCommodityDataUI () 
	   {
		
		
		// Create client configuration for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		WebResource webResource = client
			.resource("http://localhost:8080/commodities-1/rest/commodityService/list");
			
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class  );
		
		//check response from server
		if ( response.getStatus() != 200)
		{
			    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
		
		return response.getEntity(new GenericType<List<CommodityData>>() {});
		

	   }
	   
	   public static List<CommodityData> listOfExpCommodityDataUI () 
	   {
		
		
		// Create client configuration for Client
		ClientConfig clientConfig = new DefaultClientConfig();

		// make mapping
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);

		// create client
		Client client = Client.create(clientConfig);

		WebResource webResource = client
			.resource("http://localhost:8080/commodities-1/rest/commodityService/listOfExp");
			
		ClientResponse response = webResource.type("application/json").accept(new MediaType("application", "json")).get(ClientResponse.class  );
		
		//check response from server
		if ( response.getStatus() != 200)
		{
			    throw new RuntimeErrorException(null, "Failed : HTTP ERROR CODE " + response.getStatus()) ; 
		}
		
		return response.getEntity(new GenericType<List<CommodityData>>() {});
		

	   }
}
