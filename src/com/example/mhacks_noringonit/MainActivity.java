package com.example.mhacks_noringonit;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

// Testing the commenting

public class MainActivity extends Activity {

	private static final String DEBUG_TAG = "MyActivity";
	public static final String[] INSTANCE_PROJECT = new String[] {  
        Instances.EVENT_ID, // 0  
        Instances.BEGIN, // 1  
        Instances.TITLE, // 2  
	};
	
	private static final int PROJECTION_ID_INDEX = 0;
	private static final int PROJECTION_BEGIN_INDEX = 1;
	private static final int PROJECTION_TITLE_INDEX = 2;
	
	@SuppressLint("InlinedApi")
	public void addEvent(Activity ac)
	{
		Log.d("third", "third");
		Intent intent = new Intent(Intent.ACTION_INSERT);  
        intent.setType("vnd.android.cursor.item/event");  
        intent.putExtra(Events.TITLE, "tester Android");  
        intent.putExtra(Events.EVENT_LOCATION, "Home suit home");  
        intent.putExtra(Events.DESCRIPTION, "Download Examples");  
        
        Log.d("fourth", "fourth");
   
        GregorianCalendar calDate = new GregorianCalendar(2014, 0, 19);  
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,  
                calDate.getTimeInMillis());  
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,  
                calDate.getTimeInMillis());  
   
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);  
   
        intent.putExtra(Events.RRULE,  
                "FREQ=WEEKLY;COUNT=11;WKST=SU;BYDAY=TU,TH");  
   
        intent.putExtra(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);  
        intent.putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);  
   
        Log.d("fifth", "fifth");
        ac.startActivity(intent);  
	}
	
	@SuppressLint("NewApi")
	public void getEvents(Activity ac)
	{
		int year, month, date;
		
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
		date = Calendar.getInstance().get(Calendar.DATE);
		
		begin.set(year, month, date, 0, 0);
		end.set(year, month, date, 23, 59);
		
		String[] projection = new String[] {
				Events._ID,
				Events.TITLE,
				Events.DTSTART,
				Events.DTEND,
				Events.DURATION};		

		Cursor cursor = 
				Instances.query(getContentResolver(), projection, begin.getTimeInMillis(), end.getTimeInMillis());
		
		cursor.moveToFirst();

		do {
			Log.d(cursor.getString(cursor.getColumnIndex("title")),  cursor.getString(cursor.getColumnIndex("title")));
			Log.d(cursor.getString(cursor.getColumnIndex("dtstart")),cursor.getString(cursor.getColumnIndex("dtstart")));

			if(TextUtils.isEmpty(cursor.getString(cursor.getColumnIndex("duration"))))
			{
				Log.d(cursor.getString(cursor.getColumnIndex("dtend")),cursor.getString(cursor.getColumnIndex("dtend")));
			} else
			{
				Log.d(cursor.getString(cursor.getColumnIndex("duration")),cursor.getString(cursor.getColumnIndex("duration")));
			}
		} while (cursor.moveToNext());
	}	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }    
}
