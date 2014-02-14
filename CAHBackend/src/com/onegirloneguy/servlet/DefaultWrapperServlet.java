package com.onegirloneguy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class DefaultWrapperServlet extends HttpServlet
{   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
    	throws ServletException, IOException
    {
    	final RequestDispatcher rd = getServletContext().getNamedDispatcher("default");

    	final HttpServletRequest wrapped = new HttpServletRequestWrapper(req) {
    		public String getServletPath() { return ""; }
    	};

    	rd.forward(wrapped, resp);
    }
}