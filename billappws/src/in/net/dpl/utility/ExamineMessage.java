package in.net.dpl.utility;




public class ExamineMessage {

	String merchantID=null;
    String customerID=null;
    String txnRefNo=null;
    String bankRefNo=null;
    String txnAmt=null;
    String bankID=null;
    String bankMerchantID=null;
    String txnType=null;
    String currencyName=null;
    String itemCode=null;
    String securityType=null;
    String securityID=null;
    String securityPassword=null;
    String transactionDate=null;
    String authStatus=null;
    String settlementType=null;
    String addInfo1=null;
    String addInfo2=null;
    String addInfo3=null;
    String addInfo4=null;
    String addInfo5=null;
    String addInfo6=null;
    String addInfo7=null;
    String errorStatus=null;
    String errorDescription=null;
    String ChecksumKey ="a3VkNtgWZNwu";
    
    public String examineMsg(String msg){
    	StringBuffer sbmsg=new StringBuffer(msg);
		String checksumBD=msg.substring(msg.lastIndexOf("|")+1, msg.length());
		String originalMsg=msg.substring(0,msg.lastIndexOf("|"));
		String checkSumDPL=new ChecksumBillDesk().HmacSHA256(msg, ChecksumKey);
		
		System.out.println("CheckSum Bd-"+checksumBD);
		System.out.println("CheckSum DPL-"+checkSumDPL);
		System.out.println("Message part only-"+originalMsg);
		
		if(checksumBD.matches(checkSumDPL)){
			System.out.println("Checksum Matched, Authorised Transaction. Transaction Reports will be updated");
			extractString(originalMsg);
			//INSERT RECORD HERE
			
			return "SUCCESS";
			
		}
		else{
			System.out.println("Checksum Mis Match, Transaction Declined. ");
			return "FALSE";
			
		}
		
		
		
	}
	
	
		public void extractString(String msg){
	    
	    int i=0;
	    
	    
	    
	    
	    String[] value_split = msg.split("\\|");
	    for (String string : value_split) {

	        
	        System.out.println(string);

	    }
	    for(i=0;i<value_split.length;i++){
	        
	        if(i==0){
	            this.merchantID=value_split[i];
	        }
	        else if(i==1){
	        	 this.customerID=value_split[i];
	        }
	        else if(i==2){
	        	 this.txnRefNo=value_split[i];
	        }
	        
	        else if(i==3){
	        	 this.bankRefNo=value_split[i];
	        }
	        else if(i==4){
	        	 this.txnAmt=value_split[i];
	        }
	        else if(i==5){
	        	 this.bankID=value_split[i];
	        }
	        else if(i==6){
	            this.bankMerchantID=value_split[i];
	        }
	        else if(i==6){
	            this.txnType=value_split[i];
	        }
	        else if(i==7){
	            this.currencyName=value_split[i];
	        }
	        else if(i==8){
	            this.itemCode=value_split[i];
	        }
	        else if(i==9){
	            this.securityType=value_split[i];
	        }
	        else if(i==10){
	            this.securityID=value_split[i];
	        }
	        else if(i==11){
	            this.securityPassword=value_split[i];
	        }
	        else if(i==12){
	            this.transactionDate=value_split[i];
	        }
	        else if(i==13){
	            this.authStatus=value_split[i];
	        }
	        else if(i==14){
	            this.settlementType=value_split[i];
	        }
	        else if(i==15){
	            this.addInfo1=value_split[i];
	        }
	        else if(i==16){
	            this.addInfo2=value_split[i];
	        }
	        else if(i==17){
	            this.addInfo3=value_split[i];
	        }
	        else if(i==18){
	            this.addInfo4=value_split[i];
	        }
	        else if(i==19){
	            this.addInfo5=value_split[i];
	        }
	        else if(i==20){
	            this.addInfo6=value_split[i];
	        }
	        else if(i==21){
	            this.addInfo7=value_split[i];
	        }
	        else if(i==22){
	            this.errorStatus=value_split[i];
	        }
	        else if(i==23){
	            this.errorDescription=value_split[i];
	        }
	        
	    }
	    
	    
	    System.out.println("Merchant id "+merchantID);
	    System.out.println("Consumer id="+customerID);
	    System.out.println("TXN Ref No="+txnRefNo);
	    System.out.println("Bank Ref No="+bankRefNo);
	    System.out.println("TXN Amt="+txnAmt);
	    System.out.println("Bank ID="+bankID);
	    System.out.println("Ban Merchant ID="+bankMerchantID);
	    System.out.println("TXN Type="+txnType);
	    System.out.println("Currency Name="+currencyName);
	    System.out.println("Item code="+itemCode);
	    System.out.println("Security Type="+securityType);
	    System.out.println("Security ID="+securityID);
	    System.out.println("Security Password="+securityPassword);
	    System.out.println("Transaction date="+transactionDate);
	    System.out.println("Auth Status="+authStatus);
	    System.out.println("Settlement Type="+settlementType);
	    System.out.println("Add info 1 ="+addInfo1);
	    System.out.println("Add info 2 ="+addInfo2);
	    System.out.println("Add info 3 ="+addInfo3);
	    System.out.println("Add info 4 ="+addInfo4);
	    System.out.println("Add info 5 ="+addInfo5);
	    System.out.println("Add info 6 ="+addInfo6);
	    System.out.println("Add info 7 ="+addInfo7);
	    System.out.println("Error Status ="+errorStatus);
	    System.out.println("Description ="+errorDescription);
	    
	    
	    
	    

	}
    }

