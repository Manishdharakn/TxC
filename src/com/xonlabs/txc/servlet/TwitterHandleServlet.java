package com.xonlabs.txc.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xonlabs.txc.dao.TwitterHandleDAO;
import com.xonlabs.txc.daoimpl.TwitterHandleDAOImpl;
import com.xonlabs.txc.pojo.TwitterHandle;
import com.xonlabs.txc.pojo.User;

public class TwitterHandleServlet extends HttpServlet
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
         TwitterHandleDAO tDao = new TwitterHandleDAOImpl();
         User user = (User) req.getSession().getAttribute("user");
         String requestType = req.getParameter("requestType");
         if (requestType.equals("create"))
         {
            TwitterHandle th = new TwitterHandle();
            th.setUser(user.getEmail());
            th.setHandle(req.getParameter("handle"));
            th.setEntry_time(new Timestamp(System.currentTimeMillis()));

            tDao.create(th);
            resp.sendRedirect("twitter_handle_add.jsp?msg=Twitter handle added successfully");

         }
         else if (requestType.equals("get"))
         {
            req.setAttribute("handles", tDao.getTwitterHandlesByUser(user.getEmail()));
            req.getRequestDispatcher("twitter_handle_get.jsp").forward(req, resp);
         }
         else if (requestType.equals("delete"))
         {
            tDao.delete(req.getParameter("handle"), user.getEmail());
            resp.sendRedirect("twitterhandle?requestType=get&msg=Twitter handle " + req.getParameter("handle") + " removed successfully");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=Error: " + e.getMessage());
      }
   }

}
