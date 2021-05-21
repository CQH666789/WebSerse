package com.google.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.servlet.CategoryServlet;


public class WebService extends Service {

	private Server server;
	private Notification notification;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		startForeground(9999, notification);
		//startForeground(9999, new Notification());
		startServer();
	}

	@Override
	public void onDestroy() {
		stopServer();
		super.onDestroy();
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	public void onCreate() {
		super.onCreate();
		NotificationChannel channel = new NotificationChannel("CHANNEL_ID","CHANNEL_NAME",
				NotificationManager.IMPORTANCE_HIGH);

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.createNotificationChannel(channel);

		notification = new Notification.Builder(getApplicationContext(),"CHANNEL_ID").build();
		startForeground(9999, notification);
	}

	private void startServer() {
		if (server != null) {
			//Toast.makeText(this, "服务器已经开启", Toast.LENGTH_SHORT).show();
			return;
		}
		new Thread(new StartRunnable()).start();
	}

	private void stopServer() {
		if (server != null) {
			new Thread(new StopRunnable()).start();
		}
	}

	class StartRunnable implements Runnable {
		@Override
		public void run() {
			try {
				File JETTY_DIR = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "jetty");
				// Set jetty.home
				System.setProperty("jetty.home", JETTY_DIR.getAbsolutePath());

				// ipv6 workaround for froyo
				System.setProperty("java.net.preferIPv6Addresses", "true");

				server = new Server(8090);
				// server.setHandler(new DefaultHandler());
				ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
				contextHandler.setContextPath("/");
				server.setHandler(contextHandler);
				ServlertConfig.config(contextHandler,WebService.this);

				server.start();
				server.join();
				//Toast.makeText(WebService.this, "服务器启动", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				server = null;
				e.printStackTrace();
				//Toast.makeText(WebService.this, "服务器启动失败", Toast.LENGTH_SHORT).show();
			}
		}
	}

	class StopRunnable implements Runnable {
		@Override
		public void run() {
			try {
				server.stop();
				server = null;
				//Toast.makeText(WebService.this, "服务器关闭", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				e.printStackTrace();
				//Toast.makeText(WebService.this, "服务器关闭失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
