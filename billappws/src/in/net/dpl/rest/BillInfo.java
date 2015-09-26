package in.net.dpl.rest;
 
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.FormParam;

/**
 * @author Crunchify.com
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import in.net.dpl.dao.Project;
import in.net.dpl.dto.Consumer;
import in.net.dpl.dto.FeedObjects;
import in.net.dpl.utility.ExamineMessage;
 
@Path("/billinfo")
public class BillInfo {
	
	@POST
	@Path("/post")
	public String getMonth(
		@FormParam("conNo") String conNo,@FormParam("meterNo") String meterNo) {
		System.out.println("Param-"+conNo);
		System.out.println("Param-"+meterNo);
		//return Response.status(200).entity("MSG is called, name : " + msg).build();
		
		return "200";
	}
	
	@POST
	@Path("/GetFeeds")
	@Produces("application/json")
	public String feed(@FormParam("conNo") String conNo,@FormParam("meterNo") String meterNo)
	{
	String feeds = null;
	String param="test1";
	meterNo="3";
	try 
	{
	ArrayList<FeedObjects> feedData = null;
	Project project= new Project();
	feedData = project.GetFeeds(param,meterNo);
	Gson gson = new Gson();
	System.out.println(gson.toJson(feedData));
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}
	
	@POST
	@Path("/ConsAuth")
	@Produces(MediaType.TEXT_PLAIN)
	public String ServiceLoginAuth(@FormParam("conNo") String conNo,@FormParam("meterNo") String meterNo) throws Exception
	{
	
	String feeds = null;
	String consumerData = null;
	
	System.out.println("Inside Web Services:");
	System.out.println("Consumer No:"+conNo+" : Meter No: "+meterNo);
	System.out.println("**************");
	
	
	try 
	{
	
	Project project= new Project();
	consumerData= project.consumerAuth(conNo, meterNo);
	//Gson gson = new Gson();
	//feeds = gson.toJson(consumerData);
	}

	catch (SQLException e)
	{
	e.printStackTrace();  
	}
	return consumerData;
	}
	
	
	@POST
	@Path("/Dashboard")
	@Produces("application/json")
	public String populateDashboard(@FormParam("conNo") String conNo)
	{
		
	String feeds=null;
	try 
	{
	ArrayList<Consumer> feedData = null;
	Project project= new Project();
	feedData = project.getDashboard(conNo);
	Gson gson = new Gson();
	System.out.println(gson.toJson(feedData));
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}

}