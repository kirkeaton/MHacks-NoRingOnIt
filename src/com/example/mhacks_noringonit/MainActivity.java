package com.example.mhacks_noringonit;
import static java.util.concurrent.TimeUnit.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.text.TextUtils;
import android.view.View;

public class MainActivity extends Activity {

	private AudioManager audioManager;
	
	public void 		addEvent(View view) {
		Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setType("vnd.android.cursor.item/event");
		startActivity(intent);
	}

	@SuppressLint("NewApi")
	public List<CalEvent> getEvents(View view) {
		List<CalEvent> events = new ArrayList<CalEvent>();
				
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

		Cursor cursor = 
				Instances.query(getContentResolver(), projection, begin.getTimeInMillis(), end.getTimeInMillis());
				
		cursor.moveToFirst();
		if(cursor.getCount() > 0)
		{
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
		}
		
		
		
		setRingerOn(audioManager);
		
		return events;		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
	}
	
		/* Turns Ringer to normal mode */ 
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); 
	protected void setRingerOn(final AudioManager myAudioManager)
	{
		  final Runnable ringer = new Runnable (){
		    public void run() {  myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);}	

		  };
		  
		  final ScheduledFuture<?> ringerHandle = scheduler.scheduleAtFixedRate(ringer,10,10,SECONDS);
		  scheduler.schedule (new Runnable() {
			  public void run() { ringerHandle.cancel(true); }
		  }, 60 * 60, SECONDS);
	}
	
	
	/* Turns ringer to silent mode */ 
	protected void setRingerOff(AudioManager myAudioManager)
	{
	      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}
}
