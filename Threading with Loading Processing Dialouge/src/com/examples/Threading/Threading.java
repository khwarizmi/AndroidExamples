package com.examples.Threading;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Threading extends Activity {
    
	Handler myHandler;
	ProgressDialog myProgress;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    
        Button btnThread = (Button) findViewById(R.id.TestThreads);
        btnThread.setOnClickListener(new OnClickListener() {
			
		
			public void onClick(View v) {
				
				myProgress = ProgressDialog.show(Threading.this, 
						"Linux Magazine Demo", "Counting down ...", true,false);

				myHandler = new Handler(new Callback() {
					
					
					public boolean handleMessage(Message msg) {
						switch (msg.what) {
                        case 0:
                            // update progress bar
                            myProgress.setMessage("" + (String) msg.obj);
                            break;
                        case 1:
                       	 myProgress.cancel();
                            finish();
                            break;
                        case 2:
                       	 myProgress.cancel();
                        	break;
                    }
						return false;
					}
				});
				
				Thread workthread = new Thread(new SidelineThread());
                workthread.start();
			}
		});
        
        
    }

class SidelineThread implements Runnable
{

	public void run() {
		
		long i =0;
		Message msg;
		
		while(i < 10000000)
		{
			
			if(i%100000 == 0)
			{
				msg = new Message();
				msg.what = 0;
				msg.obj = "Processed " + i + " so far.";
				myHandler.sendMessage(msg);
			}
			i++;
			
		}
		msg = new Message();
		msg.what = 1;
		myHandler.sendMessage(msg);

	}
	
}

}
