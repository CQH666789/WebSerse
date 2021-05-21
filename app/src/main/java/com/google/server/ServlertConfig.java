package com.google.server;

import android.app.Service;
import android.content.Context;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.servlet.AppServlet;
import com.google.servlet.CategoryServlet;
import com.google.servlet.DetailServlet;
import com.google.servlet.DownloadServlet;
import com.google.servlet.GameServlet;
import com.google.servlet.HomeServlet;
import com.google.servlet.HotServlet;
import com.google.servlet.ImageServlet;
import com.google.servlet.RecommendServlet;
import com.google.servlet.SubjectServlet;
import com.google.servlet.UserServlet;

public class ServlertConfig {
	public static void config(ServletContextHandler handler, Service service) {
		handler.addServlet(new ServletHolder(new CategoryServlet(service)), "/category");
		handler.addServlet(new ServletHolder(new ImageServlet(service)), "/image");
		handler.addServlet(new ServletHolder(new RecommendServlet(service)), "/recommend");
		handler.addServlet(new ServletHolder(new SubjectServlet(service)), "/subject");
		handler.addServlet(new ServletHolder(new DetailServlet(service)), "/detail");
		handler.addServlet(new ServletHolder(new HomeServlet(service)), "/home");
		handler.addServlet(new ServletHolder(new AppServlet(service)), "/app");
		handler.addServlet(new ServletHolder(new GameServlet(service)), "/game");
		handler.addServlet(new ServletHolder(new DownloadServlet(service)), "/download");
		handler.addServlet(new ServletHolder(new UserServlet(service)), "/user");
		handler.addServlet(new ServletHolder(new HotServlet(service)), "/hot");
	}
}
