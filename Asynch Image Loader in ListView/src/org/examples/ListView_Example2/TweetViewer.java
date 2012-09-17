package org.examples.ListView_Example2;

import java.util.ArrayList;

import Model.*;
import android.app.ListActivity;
import android.os.Bundle;


public class TweetViewer extends ListActivity {
	
	ArrayList<ImageAndText> myData;
	ImageAndTextListAdapter myAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* set listview xml layout file */
        setContentView(R.layout.main);
        
        /* define data items */
        myData = new ArrayList<ImageAndText>();
        for(int i = 0; i < 20; i++)
        	myData.add(new ImageAndText("https://si0.twimg.com/profile_images/1305987339/avatar173376_1.gif_reasonably_small.jpeg","Testing Item" + i));
       
        /* set data adapter for the listview*/
       myAdapter = new  ImageAndTextListAdapter(this, this.myData);
       setListAdapter(myAdapter);
       
       
       myAdapter.notifyDataSetChanged();
           
    }
}