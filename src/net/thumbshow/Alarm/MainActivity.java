package net.thumbshow.Alarm;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener(){
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent("android.alarm.alarm.action");
				PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this,  
				 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
				Calendar calendar = Calendar.getInstance();  
				calendar.setTimeInMillis(System.currentTimeMillis());  
				calendar.add(Calendar.SECOND, 5);
				AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);  
				am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
				am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*1000, sender);
			}
			
		});
	}
}
