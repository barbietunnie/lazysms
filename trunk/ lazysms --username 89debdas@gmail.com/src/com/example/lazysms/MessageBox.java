package com.example.lazysms;

/***
 *    Application Name : MessageBox 
 *    Author : Vimal Rughani
 *    Website : http://pulse7.net
 *    For more details visit http://pulse7.net/android/read-sms-message-inbox-sent-draft-android/
 */
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
//import android.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MessageBox extends Activity implements OnClickListener {

	// GUI Widget
	Button btnSent, btnInbox, btnDraft;
	TextView lblMsg, lblNo;
	ListView lvMsg;

	// Cursor Adapter
	SimpleCursorAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messagebox);

		// Init GUI Widget
		btnInbox = (Button) findViewById(R.id.btnInbox);
		btnInbox.setOnClickListener(this);

		btnSent = (Button) findViewById(R.id.btnSentBox);
		btnSent.setOnClickListener(this);

		btnDraft = (Button) findViewById(R.id.btnDraft);
		btnDraft.setOnClickListener(this);

		lvMsg = (ListView) findViewById(R.id.lvMsg);

	}

	@Override
	public void onClick(View v) {

		if (v == btnInbox) {

			// Create Inbox box URI
			Uri inboxURI = Uri.parse("content://sms/inbox");

			// List required columns
			String[] reqCols = new String[] { "_id", "address", "body" };

			// Get Content Resolver object, which will deal with Content
			// Provider
			ContentResolver cr = getContentResolver();

			// Fetch Inbox SMS Message from Built-in Content Provider
			Cursor c = cr.query(inboxURI, reqCols, null, null, null);

			// Attached Cursor with adapter and display in listview
			adapter = new SimpleCursorAdapter(this, R.layout.row, c,
					new String[] { "_id","body", "address" }, new int[] {
					R.id.msg_id, R.id.lblMsg, R.id.lblNumber }, 0);
			lvMsg.setAdapter(adapter);
			
			lvMsg.setOnItemClickListener(new OnItemClickListener() {
			     
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    // getting values from selected ListItem
                    String msg_id = ((TextView) view.findViewById(R.id.msg_id)).getText()
                            .toString();
     
                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(),
                    		MessageDetails.class);
                    // sending pid to next activity
                    in.putExtra("message_id", msg_id);
     
                    // starting new activity and expecting some response back
                    startActivityForResult(in, 100);
                }
            });

		}

		if (v == btnSent) {

			// Create Sent box URI
			Uri sentURI = Uri.parse("content://sms/sent");

			// List required columns
			String[] reqCols = new String[] { "_id", "address", "body" };

			// Get Content Resolver object, which will deal with Content
			// Provider
			ContentResolver cr = getContentResolver();

			// Fetch Sent SMS Message from Built-in Content Provider
			Cursor c = cr.query(sentURI, reqCols, null, null, null);

			// Attached Cursor with adapter and display in listview
			adapter = new SimpleCursorAdapter(this, R.layout.row, c,
					new String[] { "_id", "body", "address" }, new int[] {
					R.id.msg_id, R.id.lblMsg, R.id.lblNumber }, 0);
			lvMsg.setAdapter(adapter);
			
			
			
			
		     
			lvMsg.setOnItemClickListener(new OnItemClickListener() {
     
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    // getting values from selected ListItem
                    String msg_id = ((TextView) view.findViewById(R.id.msg_id)).getText()
                            .toString();
     
                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(),
                    		MessageDetails.class);
                    // sending pid to next activity
                    in.putExtra("message_id", msg_id);
     
                    // starting new activity and expecting some response back
                    startActivityForResult(in, 100);
                }
            });

		}

		if (v == btnDraft) {
			// Create Draft box URI
			Uri draftURI = Uri.parse("content://sms/draft");

			// List required columns
			String[] reqCols = new String[] { "_id", "address", "body" };

			// Get Content Resolver object, which will deal with Content
			// Provider
			ContentResolver cr = getContentResolver();

			// Fetch Sent SMS Message from Built-in Content Provider
			Cursor c = cr.query(draftURI, reqCols, null, null, null);

			// Attached Cursor with adapter and display in listview
			adapter = new SimpleCursorAdapter(this, R.layout.row, c,
					new String[] { "_id","body", "address" }, new int[] {
					R.id.msg_id, R.id.lblMsg, R.id.lblNumber }, 0);
			lvMsg.setAdapter(adapter);
			
			
			lvMsg.setOnItemClickListener(new OnItemClickListener() {
			     
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    // getting values from selected ListItem
                    String msg_id = ((TextView) view.findViewById(R.id.msg_id)).getText()
                            .toString();
     
                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(),
                    		MessageDetails.class);
                    // sending pid to next activity
                    in.putExtra("message_id", msg_id);
     
                    // starting new activity and expecting some response back
                    startActivityForResult(in, 100);
                }
            });

		}

	}
}
