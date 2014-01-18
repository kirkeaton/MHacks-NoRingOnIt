package com.example.mhacks_noringonit;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

// Testing the commenting

public class MainActivity extends Activity {

	@SuppressLint("InlinedApi")
	public void addEvent(View view) {
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
		startActivity(intent);
	}

	@SuppressLint("NewApi")
	public List<CalEvent> getEvents(View view) {
		List<CalEvent> events = null;
				
		int year, month, date;

		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		year = Calendar.getInstance().get(Calendar.YEAR);
		month = Calendar.getInstance().get(Calendar.MONTH);
		date = Calendar.getInstance().get(Calendar.DATE);

		begin.set(year, month, date, 0, 0);
		end.set(year, month, date, 23, 59);

		String[] projection = new String[] { Events._ID, Events.TITLE,
				Events.DTSTART, Events.DTEND, Events.DURATION };


		
		Cursor cursor = Instances.query(getContentResolver(), projection,
				begin.getTimeInMillis(), end.getTimeInMillis());
		
		cursor.moveToFirst();

		do {
			CalEvent event = new CalEvent();
			event.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			event.setStartTime(cursor.getColumnIndex("dtstart"));


			if (TextUtils.isEmpty(cursor.getString(cursor.getColumnIndex("duration")))) {
				event.setEndTime(cursor.getColumnIndex("dtend"));
				event.setIsDuration(false);
			} else {
				event.setEndTime(cursor.getColumnIndex("duration"));
				event.setIsDuration(true);
			}
			events.add(event);
		} while (cursor.moveToNext());
		return events;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/* Creates audio manager object */ 
		AudioManager audioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
	}
	
		/* Turns Ringer to normal mode */ 
	protected void setRingerOn(AudioManager myAudioManager)
	{
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);	
	}
	
	/* Turns ringer to silent mode */ 
	protected void setRingerOff(AudioManager myAudioManager)
	{
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	
	}
}
