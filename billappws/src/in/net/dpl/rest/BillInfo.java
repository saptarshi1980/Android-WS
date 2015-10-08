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
import in.net.dpl.dto.BillHistory;
import in.net.dpl.dto.Consumer;
import in.net.dpl.dto.FeedObjects;
import in.net.dpl.dto.Tariff;
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
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}
	
	@POST
	@Path("/CurrBill")
	@Produces("application/json")
	public String fetchCurrBill(@FormParam("conNo") String conNo)
	{
		
	String feeds=null;
	try 
	{
	ArrayList<Consumer> feedData = null;
	Project project= new Project();
	feedData = project.fetchCurrBill(conNo);
	Gson gson = new Gson();
	//System.out.println(gson.toJson(feedData));
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}
	
	@POST
	@Path("/GetTariff")
	@Produces("application/json")
	public String getTariff(@FormParam("conNo") String conNo)
	{
		
	String feeds=null;
	try 
	{
	ArrayList<Tariff> feedData = null;
	Project project= new Project();
	feedData = project.fetchTariff(conNo);
	Gson gson = new Gson();
	//System.out.println(gson.toJson(feedData));
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}
	
	@POST
	@Path("/BillHistory")
	@Produces("application/json")
	public String billHistory(@FormParam("conNo") String conNo)
	{
	String feeds=null;
	try 
	{
	ArrayList<BillHistory> feedData = null;
	Project project= new Project();
	feedData = project.billHistory(conNo);
	Gson gson = new Gson();
	//System.out.println(gson.toJson(feedData));
	feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feeds;
	}
	
	@POST
	@Path("/ConPattern")
	@Produces(MediaType.TEXT_PLAIN)
	public String conPattern(@FormParam("conNo") String conNo)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.conPattern(conNo);
	//Gson gson = new Gson();
	//System.out.println(gson.toJson(feedData));
	//feeds = gson.toJson(feedData);
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}

	@POST
	@Path("/UpdateEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmail(@FormParam("conNo") String conNo,@FormParam("emailId") String emailId)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.updateEmail(conNo, emailId);
	
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}

	@POST
	@Path("/UpdateMobile")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMobile(@FormParam("conNo") String conNo,@FormParam("mobile") String mobile)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.updateMobile(conNo,mobile);
	
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}
	
	@POST
	@Path("/CalculateBill")
	@Produces(MediaType.TEXT_PLAIN)
	public String calculateBill(@FormParam("conNo") String conNo,@FormParam("unit") String unit)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.calculateBill(conNo, unit);
	
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}
	
	@POST
	@Path("/FetchEmail")
	@Produces(MediaType.TEXT_PLAIN)
	public String fetchEmail(@FormParam("conNo") String conNo)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.fetchEmail(conNo);
	
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}

	@POST
	@Path("/FetchMobile")
	@Produces(MediaType.TEXT_PLAIN)
	public String fetchMobile(@FormParam("conNo") String conNo)
	{
	
	String feedData = null;
	try 
	{
	
	Project project= new Project();
	feedData = project.fetchMobile(conNo);
	
	}

	catch (Exception e)
	{
	System.out.println("Exception Error"); //Console 
	}
	return feedData;
	}
	

}