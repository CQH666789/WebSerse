package com.google.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;

import android.content.Context;
import android.os.Environment;
import android.os.SystemClock;

public class DownloadServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	public DownloadServlet(){
		super();
	}

	public DownloadServlet(Context context) {
		super(context);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		String name = req.getParameter("name");
		String range = req.getParameter("range");
		String path = mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/" + "WebInfos/" + name;
		File file = new File(path);
		long length = file.length();
		resp.setContentLength((int) length);
		OutputStream out = resp.getOutputStream();
		
		if(range == null ||"".equals(range.trim())){
			FileInputStream stream = new FileInputStream(file);
			int count = -1;
			byte[] buffer = new byte[1024];
			while ((count = stream.read(buffer)) != -1) {
				SystemClock.sleep(20);
				out.write(buffer, 0, count);
				out.flush();
			}
			stream.close();
			out.close();
		}else{
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			raf.seek(Long.valueOf(range));
			int count = -1;
			byte[] buffer = new byte[1024];
			while ((count = raf.read(buffer)) != -1) {
				SystemClock.sleep(10);
				out.write(buffer, 0, count);
				out.flush();
			}
			raf.close();
			out.close();
		}
		
	}
}
