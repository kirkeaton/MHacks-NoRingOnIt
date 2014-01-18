package com.example.mhacks_noringonit;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.AudioManager;

public class Toggler extends FullscreenActivity {
	
	      AudioManager myAudioManager=(AudioManager) this.getSystemService(Context.AUDIO_SERVICE);  


	     
       
        
		public void turnRingerOff()
		{
				
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			
		}
		
		public void turnRingerOn()
		{
			myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

		}

}
