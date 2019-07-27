package com.xonlabs.txc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xonlabs.txc.vader.VScore;
import com.xonlabs.txc.vader.VaderService;

public class AsAServiceServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		try
		{
			String text = req.getParameter("text");
			if (text == null || text.trim().length() == 0)
			{
				pw.println("Invalid Text");
			} else
			{
				VaderService vs = new VaderService();
				Map<String, VScore> result = vs.analyzeText(text);
				Gson gson = new Gson();
				String json = gson.toJson(result);
				pw.println(json);
			}

		} catch (Exception e)
		{
			pw.print("Error: " + e.getMessage());
		}
		pw.close();
	}

}
