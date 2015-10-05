package in.net.dpl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

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

public String conPattern(String conNo) 
{
	ArrayList<ConsumptionPattern> feedData = new ArrayList<ConsumptionPattern>();
	StringBuilder str = new StringBuilder();
		try
		{
		
		
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT DATE_FORMAT(STR_TO_DATE(bill_month,'%Y%m'),'%M-%y') AS bill_month,CAST(unit AS CHAR) AS unit  FROM v_last_3_bill  WHERE con_no='"+conNo+"' ORDER BY STR_TO_DATE(bill_month,'%Y%m') LIMIT 3");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			if(str.length()>0)
			str.append("|"+rs.getString(1)+"|"+rs.getString(2));
			else
			str.append(rs.getString(1)+"|"+rs.getString(2));	
			
		}
		
		
		return str.toString();
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}finally{
		
		}
		return str.toString();
}

public String updateEmail(String conNo,String emailId) 
{
	
	
		try
		{
		
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT * from contact where con_no='"+conNo+"'");
		ResultSet rs = ps.executeQuery();
		int counter=0;
		while(rs.next())
		{
			counter++;
			
		}
		 if(counter==0){
			 
			 
			 Statement stmt=conn.createStatement();
			 stmt.executeUpdate("insert into contact(con_no,email_id) values('"+conNo+"','"+emailId+"')" );
		 }
		 
		 else{
			 
			 Statement stmt=conn.createStatement();
			 stmt.executeUpdate("update contact set email_id='"+emailId+"' where con_no='"+conNo+"'");
		 }
		
		
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}finally{
		
		}
		return "SUCCESS";
}


public String updateMobile(String conNo,String mobile) 
{
	
	
		try
		{
		
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT * from contact where con_no='"+conNo+"'");
		ResultSet rs = ps.executeQuery();
		int counter=0;
		while(rs.next())
		{
			counter++;
			
		}
		 if(counter==0){
			 
			 
			 Statement stmt=conn.createStatement();
			 stmt.executeUpdate("insert into contact(con_no,mobile_no) values('"+conNo+"','"+mobile+"')" );
		 }
		 
		 else{
			 
			 Statement stmt=conn.createStatement();
			 stmt.executeUpdate("update contact set mobile_no='"+mobile+"' where con_no='"+conNo+"'");
		 }
		
		
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}finally{
		
		}
		return "SUCCESS";
}



public String calculateBill(String conNo,String unit) 
{
	double billAmt=0.0;
	double phase=0.0;
	double load=0.0;
	double energyCharge=0.0;
	double fixedCharge=0.0;
	double meterRent=0.0;
	double mvca=0.0;
	double dutyPer=0.0;
	double dutyAmt=0.0;
	String billAmtAsString=null;
	
	
	
		try
		{
		
		String finYear=new FiscalYear().getFiscalYear();
		String catType=null;
		Connection conn=new ConnDB().make_connection();	
		PreparedStatement ps = conn.prepareStatement("SELECT cat_type,PHASE,CON_load FROM P_CONS WHERE con_no='"+conNo+"'");
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			catType=rs.getString(1);
			phase=rs.getDouble(2);
			load=rs.getDouble(3);
			
		}
		 
		 
			 Statement stmt=conn.createStatement();
			 ResultSet rsBill=stmt.executeQuery("SELECT round((ADD_AMOUNT+('"+unit+"'-LOWER_UNIT+1)*RATE))AS BILL_AMT FROM P_RATE WHERE fin_year='"+finYear+"' AND matter='E' AND CAT_TYPE='"+catType+"'AND '"+unit+"' BETWEEN LOWER_UNIT AND UPPER_UNIT" );
		  
			 while(rsBill.next())
				{
					energyCharge=rsBill.getDouble(1);
					
				}
			 
			 Statement stmt2=conn.createStatement();
			 ResultSet rsBill2=stmt2.executeQuery("SELECT rate AS rate FROM p_vrate ORDER BY STR_TO_DATE(MONTH,'%Y%m') DESC LIMIT 1" );
		  
			 while(rsBill2.next())
				{
					mvca=rsBill2.getDouble(1)*Double.parseDouble(unit);
					
				}
			 
			 Statement stmt3=conn.createStatement();
			 ResultSet rsBill3=stmt3.executeQuery("SELECT rate FROM tariff WHERE con_no='"+conNo+"' AND head='Fixed Charge'" );
		  
			 while(rsBill3.next())
				{
					fixedCharge=rsBill3.getDouble(1)*load;
					
				}
			 Statement stmt4=conn.createStatement();
			 ResultSet rsBill4=stmt4.executeQuery("SELECT AMOUNT FROM p_mrent WHERE cat_type='"+catType+"' AND PHASE='"+catType+"' AND fin_year='"+finYear+"' AND meter_type='E'" );
		  
			 while(rsBill4.next())
				{
					meterRent=rsBill4.getDouble(1);
					
				}
			 
			 Statement stmt5=conn.createStatement();
			 ResultSet rsBill5=stmt5.executeQuery("SELECT RATE FROM p_rate WHERE fin_year='"+finYear+"' AND matter='D' AND CAT_TYPE='"+catType+"' AND '"+unit+"' BETWEEN LOWER_UNIT AND UPPER_UNIT LIMIT 1" );
		  
			 while(rsBill5.next())
				{
					dutyPer=rsBill5.getDouble(1);
					
				}
			 
			 	 		 		 
			 
			 
			 dutyAmt=(energyCharge+mvca+fixedCharge)*dutyPer/100;
			 
			 
			 
			 billAmt=energyCharge+meterRent+fixedCharge+mvca+dutyAmt;
			 
			 
			 billAmtAsString=String.valueOf(Math.round(billAmt))+"|"+String.valueOf(Math.round(energyCharge))+"|"+String.valueOf(Math.round(meterRent))+"|"+String.valueOf(Math.round(fixedCharge))+"|"+String.valueOf(Math.round(mvca)+"|"+String.valueOf(Math.round(dutyAmt)));
			 
		
			 
			 
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}finally{
		
		}
		return String.valueOf(billAmtAsString);
}





}