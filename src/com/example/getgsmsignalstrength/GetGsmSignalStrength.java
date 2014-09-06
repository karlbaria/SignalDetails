package com.example.getgsmsignalstrength;

import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.gsm.GsmCellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.net.TrafficStats;
import android.os.Process;

public class GetGsmSignalStrength extends Activity {

/* These variables need to be global, so we can used them onResume and onPause method to
    stop the listener */
 TelephonyManager        Tel;
 MyPhoneStateListener    MyListener;
 String IMEI;
 String operator;
 List<NeighboringCellInfo> NeighboringList;
 String cellID, lac;
 String mcc, mnc;


 /*public class OpenCellID {
	 
 Boolean error;
 String strURLSent;
 String GetOpenCellID_fullresult;
  
 String latitude;
 String longitude;
 
 public Boolean isError(){
	   return error;
	  }
 
 public void setMcc(String value){
	   mcc = value;
	  }
	   
	  public void setMnc(String value){
	   mnc = value;
	  }
	   
	  public void setCallID(int value){
	   cellID = String.valueOf(value);
	  }
	   
	  public void setCallLac(int value){
	   lac = String.valueOf(value);
	  }
	  
 public String getLocation(){
	   return(latitude + " : " + longitude);
	  }
	   
	  public void groupURLSent(){
	   strURLSent =
	    "http://www.opencellid.org/cell/get?mcc=" + mcc
	    +"&mnc=" + mnc
	    +"&cellid=" + cellID
	    +"&lac=" + lac
	    +"&fmt=txt";
	  }
	   
	  public String getstrURLSent(){
	   return strURLSent;
	  }
	   
	  public String getGetOpenCellID_fullresult(){
	   return GetOpenCellID_fullresult;
	  }
	   
	  public void GetOpenCellID() throws Exception {
	   groupURLSent();
	   HttpClient client = new DefaultHttpClient();
	   HttpGet request = new HttpGet(strURLSent);
	   HttpResponse response = client.execute(request);
	   GetOpenCellID_fullresult = EntityUtils.toString(response.getEntity()); 
	   spliteResult();
	  }
	   
	  private void spliteResult(){
	   if(GetOpenCellID_fullresult.equalsIgnoreCase("err")){
	    error = true;
	   }else{
	    error = false;
	    String[] tResult = GetOpenCellID_fullresult.split(",");
	    latitude = tResult[0];
	    longitude = tResult[1];
	   }    
	  }
}*/
	 
	 //int myLatitude, myLongitude;
	 //OpenCellID openCellID;
	 
	 SignalStrength mSignalStrength;
	 
 /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_get_gsm_signal_strength);
      
      StrictMode.ThreadPolicy policy = new
			   StrictMode.ThreadPolicy.Builder().permitAll().build();
			          StrictMode.setThreadPolicy(policy);
      
			          
      /* Update the listener, and start it */
      MyListener   = new MyPhoneStateListener();
      
      Tel = ( TelephonyManager )getSystemService(Context.TELEPHONY_SERVICE);
          
      GsmCellLocation cellLocation = (GsmCellLocation) Tel.getCellLocation();
      
      Tel.listen(MyListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
      
      /*Variables here along with function values*/
      	
   		IMEI = Tel.getDeviceId();
	   	operator = Tel.getNetworkOperatorName();
	   	int cellID = cellLocation.getCid();
	    int lac = cellLocation.getLac();
	    
	   /*Get MCC and MNC of the operator*/
	    
	    String networkOperator = Tel.getNetworkOperator();
	    mcc = networkOperator.substring(0, 3); // Integer.parseInt()
        mnc = networkOperator.substring(3);	// Integer.parseInt()
	        		
        /*This text box provides MCC and MNC code of the operator*/
        TextView mc = (TextView)findViewById(R.id.applist);
        mc.setText("The MCC is " + mcc +" & The MNC is "+ mnc);
        
        /*This text box provides IMEI of the device*/
	   	TextView dID = (TextView)findViewById(R.id.deviceID);
	   	dID.setText("The IMEI is "+IMEI);
	   	
	   	/*This text box provides Operator name*/
	   	TextView opr = (TextView)findViewById(R.id.operator);
	   	opr.setText("The Operator name is "+operator);
	   	
	   	/*This text box provides LAC and Cell ID*/
	   	TextView cal = (TextView)findViewById(R.id.cal);
	   	cal.setText("The Cell ID is "+ cellID + " & LAC is "+ lac);
	   	
	    
	   long	tx=TrafficStats.getTotalTxBytes();
	   long rx=TrafficStats.getTotalRxBytes();
		
	   	TextView textGeo = (TextView)findViewById(R.id.geo);
				
		textGeo.setText("Transmitted " +tx + " Bytes "+ "Received " +rx + " Bytes");
		
	   	/*Location*/
	   	
	  /* 	TextView textGeo = (TextView)findViewById(R.id.geo);
	    TextView textRemark = (TextView)findViewById(R.id.remark);
	    
	   	openCellID = new OpenCellID();
	       
	       openCellID.setMcc(mcc);
	       openCellID.setMnc(mnc);
	       openCellID.setCallID(cellID);
	       openCellID.setCallLac(lac);
	   	
	       try {
	    	   openCellID.GetOpenCellID();
	    	    
	    	   if(!openCellID.isError()){
	    	    textGeo.setText(openCellID.getLocation());
	    	    textRemark.setText( "\n\n"
	    	      + "URL sent: \n" + openCellID.getstrURLSent() + "\n\n"
	    	      + "response: \n" + openCellID.GetOpenCellID_fullresult);
	    	   }else{
	    	    textGeo.setText("Error");
	    	   }
	    	  } catch (Exception e) {
	    	   // TODO Auto-generated catch block
	    	   e.printStackTrace();
	    	   textGeo.setText("Exception: " + e.toString());
	    	  }
	       	   */ 	  
	       
	   	
	   	/*Get neighbouring cell site details*/
	   	TextView Neighbouring = (TextView)findViewById(R.id.neighbouring);
	    NeighboringList = Tel.getNeighboringCellInfo();
	    
	    String stringNeighboring = "Neighboring List- Lac : Cid : RSSI\n";
	       for(int i=0; i < NeighboringList.size(); i++){
	         
	        String dBm;
	        int rssi = NeighboringList.get(i).getRssi();
	        if(rssi == NeighboringCellInfo.UNKNOWN_RSSI){
	         dBm = "Unknown RSSI";
	        }else{
	         dBm = String.valueOf(-113 + 2 * rssi) + " dBm";
	        }
	 
	        /*JUST ADD THIS IF LOOP LINE IN YOUR JAVA CODE*/
	        if (NeighboringList.get(i).getLac() == 0 || NeighboringList.get(i).getCid() == -1 || NeighboringList.get(i).getCid() == 65535){
	        	
	        	stringNeighboring = stringNeighboring
       		         +  " ";//+"\n"
	      
	        } //IF LOOP ENDS HERE
	        else
	        {
	        	  stringNeighboring = stringNeighboring
	        		         + String.valueOf(NeighboringList.get(i).getLac()) +" : "
	        		         + String.valueOf(NeighboringList.get(i).getCid()) +" : "
	        		         + rssi +"\n";
	        }
	        
	       }
	       
	       /*This text box provides NEIGHBOURING cell sites with signal strength*/
	       Neighbouring.setText(stringNeighboring);
	       
	       /*This provides a list of all apps installed (data use) */
	      /* TextView appList = (TextView)findViewById(R.id.applist);
	       
		   final PackageManager packageManager = getPackageManager();
		   List<ApplicationInfo> installedApplications = 
		      packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
		   
		  		  
		   String listapp = "Application Name";
		   for (ApplicationInfo appInfo : installedApplications)
		   {
			   
		      // appList.setText("Package name : " + appInfo.packageName + " Name " + appInfo.loadLabel(packageManager)+ "\n");
		       //Log.d("OUTPUT", "Name: " + appInfo.loadLabel(packageManager));
			   
			   listapp = listapp 
					   //+ String.valueOf(appInfo.packageName)+":"
					   + appInfo.loadLabel(packageManager)+ "\n";
			   
		   } 
	       /*PROVIDES LIST OF APPS installed - IGNORE this for now*/
		  // appList.setText(listapp);
      	  	  
		   
  }
  
   
  
  /* Called when the application is minimized */
  @Override
 protected void onPause()
  {
    super.onPause();
    Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
    //LISTEN_SIGNAL_STRENGTHS
 }

  /* Called when the application resumes */
 @Override
 protected void onResume()
 {
    super.onResume();
    Tel.listen(MyListener,PhoneStateListener.LISTEN_NONE);
    //
 }

 /* —————————– */
  /* Start the PhoneState listener */
 /* —————————– */
  private class MyPhoneStateListener extends PhoneStateListener
  {
    /* Get the Signal strength from the provider, each time there is an update */
    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength)
    {
       super.onSignalStrengthsChanged(signalStrength);
       
       /*THIS LOOP PROVIDES THE CURRENT SIGNAL STRENGTH EVERY TIME THE SIGNAL CHANGES*/
       if(signalStrength.getGsmSignalStrength() > 30)
       {
    	   Toast.makeText(getApplicationContext(), "The GSM Arbitrary Signal Unit (ASU) is good= "
    		         + String.valueOf(signalStrength.getGsmSignalStrength()), Toast.LENGTH_SHORT).show(); 
       }
       else if(signalStrength.getGsmSignalStrength() > 20 && signalStrength.getGsmSignalStrength() < 30)
       {
    	   Toast.makeText(getApplicationContext(), "The GSM Arbitrary Signal Unit (ASU) is bad= "
    		         + String.valueOf(signalStrength.getGsmSignalStrength()), Toast.LENGTH_SHORT).show();
    	   
       }
       else if(signalStrength.getGsmSignalStrength() < 20)
       {
    	   Toast.makeText(getApplicationContext(), "The GSM Arbitrary Signal Unit (ASU) is ugly= "
    		         + String.valueOf(signalStrength.getGsmSignalStrength()), Toast.LENGTH_SHORT).show();
       }
      
    }

  };/* End of private Class */


}
