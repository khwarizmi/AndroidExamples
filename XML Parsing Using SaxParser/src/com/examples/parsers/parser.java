package com.examples.parsers;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class parser extends Activity {
    /** Called when the activity is first created. */
    
	Button btnXML;
	Button btnJSON;
	TextView tvData;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvData = (TextView) findViewById(R.id.txtData);
        
        btnXML = (Button) findViewById(R.id.btnXML);
        btnXML.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				examineXMLFile();
			}
		});
        
        //Still Json parsing isn't Implemented
        btnJSON = (Button) findViewById(R.id.btnJSON);
        btnJSON.setVisibility(View.GONE);
        btnJSON.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				examineJSONFile();	
			}
		});
        
    }

	protected void examineJSONFile() {
		// TODO Auto-generated method stub
		
	}

	protected void examineXMLFile() {
		
		try{
			InputSource is = new InputSource(getResources().openRawResource(R.raw.xmltwitter));
			// create the factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// create a parser
			SAXParser parser = factory.newSAXParser();
			// create the reader(scanner)
			XMLReader xmlreader = parser.getXMLReader();
			//intantiate our handler
			twitterFeedHandler tfh = new twitterFeedHandler();

		    // assign our handler
		    xmlreader.setContentHandler(tfh);
		    // perform the synchronous parse
		    xmlreader.parse(is);
		    // should be done... let's display our results
		    tvData.setText(tfh.getResults());
		    }
		  catch (Exception e) {
		            tvData.setText(e.getMessage());
		    }
	}
}