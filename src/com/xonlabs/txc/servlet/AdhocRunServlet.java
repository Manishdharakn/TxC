package com.xonlabs.txc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xonlabs.txc.vader.VaderService;

public class AdhocRunServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      try
      {
         String sentence = req.getParameter("sentence");
         if (sentence.trim().length() < 50)
         {
            resp.sendRedirect("adhoc_run.jsp?msg=The input sentence must be a minimum 50 characters.");
         }
         else
         {
            VaderService vs = new VaderService();
            req.setAttribute("result", vs.analyzeText(sentence));
            req.getRequestDispatcher("adhoc_run.jsp").forward(req, resp);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
      }
   }

}
