package com.example.lazysms;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ContentResolver;
import android.database.Cursor;

public class MessageDetails extends Activity {

	TextView message_body;
	
	TextToSpeech ttobj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_details);
		
		Intent i = getIntent();
        String smsID = i.getStringExtra("message_id");
        
        
        Uri myMessage = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(myMessage, new String[] { "_id", "address", "date", "body","read" },"_id = "+smsID, null, null);

        c.moveToFirst();

        String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();

        String ReadStatus = c.getString(c.getColumnIndex("read"));
        String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();

        c.close();
        
        message_body = (TextView) findViewById(R.id.textView1);
        
        message_body.setText(Body);
        
        
        Button btnSpeechToText = (Button) findViewById(R.id.button1);
		
		btnSpeechToText.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {

	        		
	        	ttobj=new TextToSpeech(getApplicationContext(), 
	        		      new TextToSpeech.OnInitListener() {
	        		      @Override
	        		      public void onInit(int status) {
	        		         if(status != TextToSpeech.ERROR){
	        		             ttobj.setLanguage(Locale.UK);
	        		            }				
	        		         }
	        		      });
	        	
	        	
	        }
	    });
		
		
	}
	
	
	@Override
	   public void onPause(){
	      if(ttobj !=null){
	         ttobj.stop();
	         ttobj.shutdown();
	      }
	      super.onPause();
	   }
	  
	   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void speakText(View view){
	      String toSpeak = message_body.getText().toString();
	      Toast.makeText(getApplicationContext(), toSpeak, 
	      Toast.LENGTH_SHORT).show();
	      ttobj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

	   }
	
}
