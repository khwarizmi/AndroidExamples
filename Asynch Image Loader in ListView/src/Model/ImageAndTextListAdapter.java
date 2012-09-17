package Model;


import java.util.ArrayList;

import org.examples.ListView_Example2.R;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAndTextListAdapter extends ArrayAdapter<ImageAndText> {

	AsyncImageLoader myImageLoader;
	ArrayList<ImageAndText> myItems;
	
	public ImageAndTextListAdapter(Context context, ArrayList<ImageAndText> objects) {
		
		super(context, R.layout.row, objects);
		
		myImageLoader = new AsyncImageLoader();
		myItems = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// Inflate the views from XML
        View rowView = convertView;
		 if (rowView == null) {
			 LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 rowView = vi.inflate(R.layout.row, null);
		 }
        
        ImageAndText imageAndText = this.myItems.get(position);
        
        if(imageAndText == null)
        	return rowView;
        
        // Load the image and set it on the ImageView
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        Drawable cachedImage = myImageLoader.loadDrawable(imageAndText.getImageUrl(),
        	new AsyncImageLoader.ImageCallback() {
			 
            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                imageView.setImageDrawable(imageDrawable);
            }
        });
        imageView.setImageDrawable(cachedImage);
        
        // Set the text on the TextView
        TextView topView = (TextView) rowView.findViewById(R.id.toptext);
        if(topView != null)
            topView.setText(imageAndText.getText());

        TextView bottomView = (TextView) rowView.findViewById(R.id.bottomtext);
        if(bottomView != null)
        	bottomView.setText("tweet");
        
        return rowView;
	}

	

	
}
