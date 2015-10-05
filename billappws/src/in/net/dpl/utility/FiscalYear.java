package in.net.dpl.utility;

import java.util.Calendar;

public class FiscalYear {

	
	public String getFiscalYear(){
		
		
		int year = Calendar.getInstance().get(Calendar.YEAR);

	    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
	    
	    if (month < 4) {
	        
	        return (year - 1) + "-" + year;
	    } else {
	        
	    	return year + "-" + (year + 1);
	    }
	}
}
