package in.net.dpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import in.net.dpl.dto.*;
import in.net.dpl.utility.*;

public class Project 
{
public ArrayList<FeedObjects> GetFeeds(String param1,String param2) throws Exception
	{
		ArrayList<FeedObjects> feedData = new ArrayList<FeedObjects>();
			try
			{
			
			
			Connection conn=new ConnDB().make_connection();	
			PreparedStatement ps = conn.prepareStatement("SELECT title,description,url,id FROM website ORDER BY id DESC");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			FeedObjects feedObject = new FeedObjects();
			feedObject.setTitle(rs.getString("title"));
			feedObject.setDescription(rs.getString("description"));
			feedObject.setUrl(rs.getString("url"));
			feedObject.setId(rs.getString("id"));
			
			feedData.add(feedObject);
			}
			return feedData;
			}
			catch(Exception e)
			{
			throw e;
			}finally{
			
			}
	}

public String consumerAuth(String consumerNo,String meterNo) throws Exception
{
	String name=null;
	String conNo=null;
	
	
		try
		{
		
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT b.con_no,b.meter_no,a.name as name FROM m_party a,p_cons b WHERE b.con_no='"+consumerNo+"' AND b.meter_no='"+meterNo+"' and a.party_code=b.party_code ORDER BY a.party_code ");
		ResultSet rs = ps.executeQuery();
		int counter=0;
		while(rs.next())
		{
		counter++;	
		name=rs.getString(3);
		conNo=rs.getString(1);
		
		}
		
		if(counter>0){
		return name+"|"+conNo;
		
		}
		else return "UNAUTHORIZED";
		
		}
		catch(Exception e)
		{
		throw e;
		}
}

public ArrayList<Consumer> getDashboard(String conNo) {
	
 System.out.println("Inside Dash Borad WS, Con No-"+conNo);
	
	ArrayList<Consumer> feedData = new ArrayList<Consumer>();
	try
		{
		
		String partyCode,partyName,address,reference,category,tariff,phase,demand,mf;
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT party_code,party_name,address,reference,category,tariff,con_phase,demand,mf FROM v_dashboard WHERE con_no='"+conNo+"'");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			
		Consumer consumer = new Consumer();
		partyCode=rs.getString(1);
		partyName=rs.getString(2);
		address=rs.getString(3);
		reference=rs.getString(4);
		category=rs.getString(5);
		tariff=rs.getString(6);
		phase=rs.getString(7);
		demand=rs.getString(8);
		mf=rs.getString(9);
		consumer.setPartyCode(partyCode);
		consumer.setConsumerName(partyName);
		consumer.setAddress(address);
		consumer.setReference(reference);
		consumer.setCategory(category);
		consumer.setCategory(category);
		consumer.setTariffType(tariff);
		consumer.setPhase(phase);
		consumer.setContractDemand(demand);
		consumer.setMulFactor(mf);
		feedData.add(consumer);
		
		}
		return feedData;
		}
		
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	
	return null;
}


public ArrayList<Consumer> fetchCurrBill(String conNo) {
	
	
		
		ArrayList<Consumer> feedData = new ArrayList<Consumer>();
		try
			{
			
			String month,lastReading,presentReading,meterStatus,amountDue,tariff,phase,demand,mf,unit,due1,due2;
			Connection conn=new ConnDB().make_connection();	
			PreparedStatement ps = conn.prepareStatement("SELECT MONTH,last_read,curr_read,mf,meter_status,unit,bill_amount,DATE_FORMAT(due_date1,'%d-%m-%Y') AS due_date1,DATE_FORMAT(due_date2,'%d-%m-%Y') AS due_date2 FROM v_curr_bill where con_no='"+conNo+"'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				
			Consumer consumer = new Consumer();
			month=rs.getString(1);
			lastReading=rs.getString(2);
			presentReading=rs.getString(3);
			mf=rs.getString(4);
			meterStatus=rs.getString(5);
			unit=rs.getString(6);
			amountDue=rs.getString(7);
			due1=rs.getString(8);
			due2=rs.getString(9);
			consumer.setMonth(month);
			consumer.setPrevReading(lastReading);
			consumer.setCurrReading(presentReading);
			consumer.setMulFactor(mf);
			consumer.setMeterStatus(meterStatus);
			consumer.setUnit(unit);
			consumer.setAmountDue(amountDue);
			consumer.setDue1(due1);
			consumer.setDue2(due2);
			feedData.add(consumer);
			
			}
			return feedData;
			}
			
			catch(SQLException e)
			{
			e.printStackTrace();
			}
		
		return null;
	}

public ArrayList<Tariff> fetchTariff(String conNo) {
	
	
	
	ArrayList<Tariff> feedData = new ArrayList<Tariff>();
	try
		{
		
		String head,consumption,rate;
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT head,consumption,rate FROM tariff where con_no='"+conNo+"'");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			
		Tariff tariff = new Tariff();
		head=rs.getString(1);
		consumption=rs.getString(2);
		rate=rs.getString(3);
		tariff.setHead(head);
		tariff.setConsumption(consumption);
		tariff.setRate(rate);
		feedData.add(tariff);
		
		}
		return feedData;
		}
		
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	
	return null;
}


public ArrayList<BillHistory> billHistory(String conNo) 
{
	ArrayList<BillHistory> feedData = new ArrayList<BillHistory>();
		try
		{
		
		
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT con_no,party_code,name,MONTH,last_read,curr_read,mf,meter_status,unit,bill_amount,DATE_FORMAT(due_date1,'%d-%m-%Y') AS due_date1,DATE_FORMAT(due_date2,'%d-%m-%Y') AS due_date2 FROM v_last_3_bill WHERE con_no='"+conNo+"' ORDER BY bill_month DESC LIMIT 3");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
		BillHistory feedObject = new BillHistory();
		feedObject.setConNo(rs.getString("con_no"));
		feedObject.setPartyCode(rs.getString("party_code"));
		feedObject.setName(rs.getString("name"));
		feedObject.setBillMonth(rs.getString("month"));
		feedObject.setLastRead(rs.getString("last_read"));
		feedObject.setCurrRead(rs.getString("curr_read"));
		feedObject.setMf(rs.getString("mf"));
		feedObject.setMeterStatus(rs.getString("meter_status"));
		feedObject.setUnit(rs.getString("unit"));
		feedObject.setBillAmount(rs.getString("bill_amount"));
		feedObject.setDueDate1(rs.getString("due_date1"));
		feedObject.setDueDate2(rs.getString("due_date2"));
		feedData.add(feedObject);
		}
		return feedData;
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}finally{
		
		}
		return feedData;
}


}