package com.GUI;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DialogBoxActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dialogbox);
		
		/** handle buttons events */
		handle_button_toast();
		handle_button_alert();
		handle_button_yesno();
		handle_progess_bar();
	}

	/** functions to add Event Handler foreach Button */
	private void handle_progess_bar() {
		
		 Button progressbutton  = (Button)this.findViewById(R.id.btn_progress);
		 progressbutton.setOnClickListener( new OnClickListener() {

			public void onClick(View arg0) {
				
				ProgressDialog pdialog = new ProgressDialog(DialogBoxActivity.this);
				pdialog.setCancelable(true);
				pdialog.setMessage("loading...");
				pdialog.show();
			}
		   });
		
	}

	private void handle_button_yesno() {
		
		Button yesno  = (Button)this.findViewById(R.id.btn_yesno);
	    yesno.setOnClickListener( new OnClickListener() {
		
			public void onClick(View arg0) {
			
				AlertDialog.Builder alertdialog = new AlertDialog.Builder(DialogBoxActivity.this);
				alertdialog.setMessage("this is a Yes/No Dialog");
				alertdialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						
						Toast.makeText(DialogBoxActivity.this, "You have Clicked Yes!", Toast.LENGTH_LONG).show();
					}
				});
				alertdialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						
						Toast.makeText(DialogBoxActivity.this, "You have Clicked No!", Toast.LENGTH_LONG).show();
					}
				});
				
				alertdialog.show();
			}
		   });
		
	}

	private void handle_button_alert() {
		
		Button alert  = (Button)this.findViewById(R.id.btn_alert);
	    alert.setOnClickListener( new OnClickListener() {
			
			public void onClick(View arg0) {
			
				AlertDialog.Builder alertdialog = new AlertDialog.Builder(DialogBoxActivity.this);
				alertdialog.setMessage("this is an alert Dialog");
				alertdialog.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
					
					
					public void onClick(DialogInterface arg0, int arg1) {
						
						Toast.makeText(DialogBoxActivity.this, "You have Clicked Ok!", Toast.LENGTH_LONG).show();
					}
				});
				
				alertdialog.show();
			}
		   });
	}

	private void handle_button_toast() {
	  
	  Button toast  = (Button)this.findViewById(R.id.btn_toast);
      toast.setOnClickListener( new OnClickListener() {
		
		public void onClick(View arg0) {
			
			Toast.makeText(DialogBoxActivity.this, "toast test example", Toast.LENGTH_LONG).show();
		}
	   });
		
	}
	
}
