package com.example.lazysms;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LanguageTranslate extends Activity {
	
	private int check_code = 0;
    Spinner lang;
    Button say;
    EditText text;
    TextView translatedText;
    String original=null;
    String translated=null;
    String langSelected;
    private TextToSpeech convert;
    
    private static String json_url = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    String lang_to_translate = "";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.language_translate);
          
           lang=(Spinner)findViewById(R.id.selectLanguage);
           say=(Button)findViewById(R.id.say);
           text=(EditText)findViewById(R.id.text);
           translatedText=(TextView)findViewById(R.id.translatedtext);
           
           say.setOnClickListener(new OnClickListener() {
        	   
        	   @Override
               public void onClick(View v) {
        		   
        		   langSelected=String.valueOf(lang.getSelectedItem());
        		   
        		   if (text!=null && text.length()>0) {    
                       try
                       {
                              if(langSelected.equalsIgnoreCase("RUSSIAN"))
                              {
                            	  lang_to_translate = "en-ru";
                              }
                              else if(langSelected.equalsIgnoreCase("FRENCH"))
                              {
                            	  lang_to_translate = "en-fr";
                              }
                              else if(langSelected.equalsIgnoreCase("GERMAN"))
                              {
                            	  lang_to_translate = "en-de";
                              }
                              else if(langSelected.equalsIgnoreCase("ITALIAN"))
                              {
                            	  lang_to_translate = "en-it";
                              }
                              else if(langSelected.equalsIgnoreCase("TURKISH"))
                              {
                            	  lang_to_translate = "en-tr";
                              }
                              else if(langSelected.equalsIgnoreCase("UKRAINIAN"))
                              {
                            	  lang_to_translate = "en-uk";
                              }
                       }
                       catch(Exception e)
                       {
                              Log.i("Error in translation.........",e.toString());
                       }
                       
                       new LanguageTrns().execute();
        		   }
        	   }
           });
           
	}

	
	 class LanguageTrns extends AsyncTask<String, String, String> {
		 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            
	            
	            pDialog = new ProgressDialog(LanguageTranslate.this);
	            pDialog.setMessage("Loading. Please wait...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	 
	        /**
	         * getting All products from url
	         * */
	        protected String doInBackground(String... args) {
	        	
	        	
	        	
	            
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();	            
	            params.add(new BasicNameValuePair("key","trnsl.1.1.20140702T094113Z.7fbb61faf25d5f51.7d045dabcc22d9847503f7d563d4df82933ec899"));
	            params.add(new BasicNameValuePair("lang",lang_to_translate));
	            /*
	            ALBANIAN
				ARABIC
				ARMENIAN
				AZERBAIJANI
				BELARUSIAN
				BOSNIAN
				BULGARIAN
				CATALAN
				CROATIAN
				CZECH
				DANISH
				DUTCH
				ESTONIAN
				FINNISH
				FRENCH
				GEORGIAN
				GERMAN
				GREEK
				HEBREW
				HUNGARIAN
				ICELANDIC
				INDONESIAN
				ITALIAN
				LATIVIAN
				LITHUANIAN
				MACEDONIAN
				MALAY
				MALTESE
				NORWEGIAN
				POLISH
				PORTUGUESE
				ROMANIAN
				RUSSIAN
				SERBIAN
				SLOVAK
				SLOVENIAN
				SPANISH
				SWEDISH
				TURKISH
				UKRAINIAN
				VIETNAMESE
	            */
	            params.add(new BasicNameValuePair("text",text.getText().toString()));
	            
	            // getting JSON string from URL
	            JSONObject json = jParser.makeHttpRequest(json_url, "GET", params);
	            
	            
	 
	            // Check your log cat for JSON reponse
	            Log.d("Response: ", json.toString());
	 
	           try{
	        	   
	        	   //translated_text_arr = json.getJSONArray("text");
	        	   //JSONObject c = translated_text_arr.getJSONObject(0);
	        	   
	        	   translated = json.getString("text");
	        	   Log.d("trnas: ", translated);
	        	   
	           }catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            pDialog.dismiss();
	            runOnUiThread(new Runnable() {
	                public void run() {
	                   
	                	translatedText.setText(translated);
                        Toast.makeText(LanguageTranslate.this, "Saying: " + translated, Toast.LENGTH_LONG).show();
                        
	                }
	            });
	 
	        }
	 
	    }
	
}
