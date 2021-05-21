package com.google.servlet;

import android.content.Context;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public UserServlet(){
		super();
	}

	public UserServlet(Context context) {
		super(context);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
