package com.GUI;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends ListActivity {
	
	/** we want to display these items in the list */
	String[] names = {" Test App"};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        /** our array adapter which is dealt as connection between data source
            and the list View component */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1,names);
        
        /** last step is to set the list adapter as our adapter */
        setListAdapter(adapter);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		/* create an Intent to start */
		Intent i = new Intent(ListViewActivity.this , DialogBoxActivity.class);
		
		startActivityForResult(i, 0x1337);
	}
    
}