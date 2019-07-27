package com.xonlabs.txc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xonlabs.txc.pojo.User;
import com.xonlabs.txc.vader.TwitterHandleVaderThread;
import com.xonlabs.txc.vader.TwitterKeywordVaderThread;

public class RunTwitterSentimentServlet extends HttpServlet
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
         User user = (User) req.getSession().getAttribute("user");
         String check = (String) req.getSession().getAttribute("inprogress");
         if (check != null && check.equals("yes"))
         {
            resp.sendRedirect("Twitter Sentiment Analysis run has already been initiated and haven't completed yet. Please wait for some more time");
         }
         else
         {
            req.getSession().removeAttribute("twitterhandleresults");
            req.getSession().removeAttribute("twitterkeywordresults");
            new TwitterHandleVaderThread(user.getEmail(), req.getSession());
            new TwitterKeywordVaderThread(user.getEmail(), req.getSession());
            resp.sendRedirect("run.jsp");
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
      }

   }

}
