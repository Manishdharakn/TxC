package com.xonlabs.txc.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xonlabs.txc.dao.TwitterKeywordDAO;
import com.xonlabs.txc.daoimpl.TwitterKeywordDAOImpl;
import com.xonlabs.txc.pojo.TwitterKeyword;
import com.xonlabs.txc.pojo.User;

public class TwitterKeywordServlet extends HttpServlet
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
         TwitterKeywordDAO tDao = new TwitterKeywordDAOImpl();
         User user = (User) req.getSession().getAttribute("user");
         String requestType = req.getParameter("requestType");
         if (requestType.equals("create"))
         {
            TwitterKeyword th = new TwitterKeyword();
            th.setUser(user.getEmail());
            th.setKeyword(req.getParameter("keyword"));
            th.setEntry_time(new Timestamp(System.currentTimeMillis()));

            tDao.create(th);
            resp.sendRedirect("twitter_keyword_add.jsp?msg=Twitter keyword added successfully");

         }
         else if (requestType.equals("get"))
         {
            req.setAttribute("keywords", tDao.getTwitterKeywordsByUser(user.getEmail()));
            req.getRequestDispatcher("twitter_keyword_get.jsp").forward(req, resp);
         }
         else if (requestType.equals("delete"))
         {
            tDao.delete(req.getParameter("keyword"), user.getEmail());
            resp.sendRedirect("twitterkeyword?requestType=get&msg=Twitter Keyword " + req.getParameter("keyword") + " removed successfully");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
      }
   }

}
