package net.thumbshow.Alarm;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	static final String action_boot = "android.intent.action.BOOT_COMPLETED";
	static final String action_mounted = "android.intent.action.MEDIA_MOUNTED";
	static final String action_unmounted = "android.intent.action.MEDIA_UNMOUNTED";
	static final String action_eject = "android.intent.action.MEDIA_EJECT";
	static final String action_alarm = "android.alarm.alarm.action";
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub
//		Toast.makeText(arg0, "收到通知", Toast.LENGTH_LONG).show();
		if (arg1.getAction().equals(action_alarm)){  
			NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0,  
	                new Intent(context, MainActivity.class), 0);  
	        // 通过Notification.Builder来创建通知，注意API Level  
	        // API16之后才支持  
			 Notification notify3 = new Notification.Builder(context)  
		                .setSmallIcon(R.drawable.ic_launcher)  
		                .setTicker("XXXX:" + "您有新短消息，请注意查收！")  
		                .setContentTitle("XXXX")  
		                .setContentText("赶快去战斗吧!")  
		                .setContentIntent(pendingIntent3).setNumber(0).build(); 
			 // 需要注意build()是在API  
	         // level16及之后增加的，API11可以使用getNotificatin()来替代  
	        notify3.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。  
	        manager.notify(11789, notify3);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示  
		}else if(arg1.getAction().equals(action_boot)||
				arg1.getAction().equals(action_mounted)||
				arg1.getAction().equals(action_unmounted)||
				arg1.getAction().equals(action_eject)){
			Intent intent = new Intent("android.alarm.alarm.action");
			PendingIntent sender = PendingIntent.getBroadcast(context,  
			 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);  
			Calendar calendar = Calendar.getInstance();  
			calendar.setTimeInMillis(System.currentTimeMillis());  
			calendar.add(Calendar.SECOND, 5);
			AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);  
			am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
			am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*1000, sender);
		}
		
	}

}
