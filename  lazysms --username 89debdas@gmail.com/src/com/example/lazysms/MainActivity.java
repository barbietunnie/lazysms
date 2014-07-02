package com.example.lazysms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnInbox = (Button) findViewById(R.id.btnInbox);
		
		btnInbox.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), MessageBox.class);
	            startActivity(i);
	        }
	    });
		
		
		Button btnSendSMS = (Button) findViewById(R.id.btnWriteSms);
		
		btnSendSMS.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), SendSMS.class);
	            startActivity(i);
	        }
	    });
		
		
		Button btnTextToSpeech = (Button) findViewById(R.id.btntexttovoice);
		
		btnTextToSpeech.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), TextToVoice.class);
	            startActivity(i);
	        }
	    });
		
		Button btnSpeechToText = (Button) findViewById(R.id.btnvoicetotext);
		
		btnSpeechToText.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), SpeechToText.class);
	            startActivity(i);
	        }
	    });
		
		
		Button btnsmsbackup = (Button) findViewById(R.id.btnsmsbackup);
		
		btnsmsbackup.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), SmsSync.class);
	            startActivity(i);
	        }
	    });
		
		Button btntranslate = (Button) findViewById(R.id.btntranslate);
		
		btntranslate.setOnClickListener(new View.OnClickListener() {
	        
	        public void onClick(View arg0) {
	        	Intent i = new Intent(getApplicationContext(), LanguageTranslate.class);
	            startActivity(i);
	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
