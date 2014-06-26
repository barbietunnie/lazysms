package com.example.lazysms;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.Menu;
import android.widget.EditText;

public class SendSMS extends Activity {
	
	EditText to_phone, my_message;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.sendsms);
	     
	     to_phone = (EditText)findViewById(R.id.editText1);
	     my_message = (EditText)findViewById(R.id.editText2);
	     
	     Button btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
	      btnSendSMS.setOnClickListener(new View.OnClickListener()
	      {
	         public void onClick(View v)
	         {
	        	 
	             sendSMS(to_phone.getText().toString(), my_message.getText().toString());


	          }
	      });
	      
	      
	      
	      Button btnSelectContact = (Button) findViewById(R.id.btnSelectContact);
	      btnSelectContact.setOnClickListener(new View.OnClickListener()
	      {
	         public void onClick(View v)
	         {
	        	 
	        	// user BoD suggests using Intent.ACTION_PICK instead of .ACTION_GET_CONTENT to avoid the chooser
	             Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	             // BoD con't: CONTENT_TYPE instead of CONTENT_ITEM_TYPE
	             intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
	             startActivityForResult(intent, 1);           


	          }
	      });
	      
	      
	   }
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (data != null) {
	        Uri uri = data.getData();

	        if (uri != null) {
	            Cursor c = null;
	            try {
	                c = getContentResolver().query(uri, new String[]{ 
	                            ContactsContract.CommonDataKinds.Phone.NUMBER,  
	                            ContactsContract.CommonDataKinds.Phone.TYPE },
	                        null, null, null);

	                if (c != null && c.moveToFirst()) {
	                    String number = c.getString(0);
	                    int type = c.getInt(1);
	                    showSelectedNumber(type, number);
	                }
	            } finally {
	                if (c != null) {
	                    c.close();
	                }
	            }
	        }
	    }
	}

	public void showSelectedNumber(int type, String number) {
	    //Toast.makeText(this, type + ": " + number, Toast.LENGTH_LONG).show();
		to_phone.setText(number);
	}
	
	
	  //---sends an SMS message to another device---

	   private void sendSMS(String phoneNumber, String message)
	   {
	       SmsManager sms = SmsManager.getDefault();
	       
	       PendingIntent sentPI;
	       String SENT = "SMS_SENT";

	       sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);

	       sms.sendTextMessage(phoneNumber, null, message, sentPI, null);
	       
	       
	       //sms.sendTextMessage(phoneNumber, null, message, null, null);
	    }
	   
	   

	   @Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

}
