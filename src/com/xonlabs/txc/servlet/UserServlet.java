package com.xonlabs.txc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xonlabs.txc.dao.UserDAO;
import com.xonlabs.txc.daoimpl.UserDAOImpl;
import com.xonlabs.txc.pojo.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      UserDAO dao = new UserDAOImpl();

      try
      {
         String request_type = req.getParameter("request_type");
         if (request_type.equals("adminlogin"))
         {
            String adminid = req.getParameter("adminid");
            String password = req.getParameter("adminpassword");
            if (dao.adminlogin(adminid, password))
            {
               req.getSession().setAttribute("admin", adminid);
               resp.sendRedirect("adminwelcome.jsp?msg=Logged in as " + adminid + " (ADMIN)");
            }
            else
            {
               resp.sendRedirect("adminlogin.jsp?msg=Invalid Admin Credentials");
            }
         }
         else if (request_type.equals("adminlogout"))
         {
            req.getSession().invalidate();
            resp.sendRedirect("adminlogin.jsp?msg=Logout successful");
         }
         else if (request_type.equals("register"))
         {
            User user = new User();
            String addr = req.getParameter("addr");
            user.setAddr(addr);
         if (addr.length() < 20) {
			  resp.sendRedirect("register.jsp?msg=Error! Minimum address length is 20 characters.");
            String email = req.getParameter("email");
            user.setEmail(email);
            String fname = req.getParameter("fname");
            user.setFname(fname);
            String lname = req.getParameter("lname");
            user.setLname(lname);
            String gender = req.getParameter("gender");
            user.setGender(gender);
            String mobile = req.getParameter("mobile");
            user.setMobile(mobile);
          Pattern p = Pattern.compile("(\\+91\\-)[6-9][0-9]{9}");
					Matcher m = p.matcher(mobile);
					if (!(m.find() && m.group().equals(mobile))) {
						resp.sendRedirect("register.jsp?msg=Error!Mobile Number Not in Required Format");
					} else {
             user.setMobile(mobile);
            String password = req.getParameter("password");
            Pattern q = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})");
						Matcher n = q.matcher(password);
						if (!(n.find() && n.group().equals(password))) {
							resp.sendRedirect(
									"register.jsp?msg=Error!Password should contain one uppercase, one lowercase, one digit, one special character, and minimum 8 characters");
						} else {
            user.setPassword(password);
            String role = req.getParameter("role");
            if (role == null || role.trim().length() == 0)
               role = "USER";
            user.setRole(role);

            if ((addr == null || addr.trim().length() == 0) || (email == null || email.trim().length() == 0)
                     || (fname == null || fname.trim().length() == 0)
                     || (lname == null || lname.trim().length() == 0)
                     || (mobile == null || mobile.trim().length() == 0)
                     || (role == null || role.trim().length() == 0)
                     || (gender == null || gender.trim().length() == 0)
                     || (password == null || password.trim().length() == 0))
            {
               resp.sendRedirect(
                        "register.jsp?msg=Error! All the fields are mandatory. Please provide the details.");
            }
            else
            {

               dao.register(user);
               resp.sendRedirect("register.jsp?msg=Registration Successful");
            }
         }
         else if (request_type.equals("login"))
         {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = dao.getUserDetails(email, password);
            if (email == null || email.trim().length() == 0 || password == null || password.trim().length() == 0)
            {
               resp.sendRedirect("login.jsp?msg=Error! All the fields are mandatory. Please provide the details");
            }
            else if (user != null)
            {
               req.getSession().setAttribute("user", user);
               resp.sendRedirect("welcome.jsp?msg=Successfully logged in as " + user.getFname() + " "
                        + user.getLname() + " (" + user.getRole() + ") ");

            }
            else
            {
               resp.sendRedirect("login.jsp?msg=Invalid Credentials");
            }
         }

         else if (request_type.equals("updateprofile"))
         {
            User user = new User();

            String addr = req.getParameter("addr");
            String email = req.getParameter("email");
            String fname = req.getParameter("fname");
            String lname = req.getParameter("lname");
            String gender = req.getParameter("gender");
            String mobile = req.getParameter("mobile");
            String role = req.getParameter("role");
            if (role == null || role.trim().length() == 0)
               role = "USER";
            user.setAddr(addr);
            user.setEmail(email);
            user.setFname(fname);
            user.setLname(lname);
            user.setGender(gender);
            user.setMobile(mobile);
            user.setRole(role);

            if ((addr == null || addr.trim().length() == 0) || (email == null || email.trim().length() == 0)
                     || (fname == null || fname.trim().length() == 0)
                     || (lname == null || lname.trim().length() == 0)
                     || (mobile == null || mobile.trim().length() == 0)
                     || (role == null || role.trim().length() == 0)
                     || (gender == null || gender.trim().length() == 0))
            {
               resp.sendRedirect(
                        "updateprofile.jsp?msg=Error! All the fields are mandatory. Please provide the details");

            }
            else
            {

               dao.updateProfile(user);
               req.getSession().removeAttribute("user");
               req.getSession().setAttribute("user", user);
               resp.sendRedirect("updateprofile.jsp?msg=Profile Updated Successfully");
            }

         }
         else if (request_type.equals("changepassword"))
         {
            String oldpassword = req.getParameter("oldpassword");
            String newpassword = req.getParameter("newpassword");

            if (oldpassword == null || oldpassword.trim().length() == 0 || newpassword == null
                     || newpassword.trim().length() == 0)
            {
               resp.sendRedirect(
                        "changepassword.jsp?msg=Error! All the fields are mandatory. Please provide the details");
            }
            else
            {

               boolean result = dao.changePassword(((User) req.getSession().getAttribute("user")).getEmail(),
                        oldpassword, newpassword);

               if (result)
               {
                  resp.sendRedirect("changepassword.jsp?msg=Successfully Updated Your Password");
               }
               else
               {
                  resp.sendRedirect("changepassword.jsp?msg=Your Current Password is Wrong");

               }
            }
         }
         else if (request_type.equals("deleteprofile"))
         {
            dao.deleteProfile(((User) req.getSession().getAttribute("user")).getEmail());
            req.getSession().invalidate();
            resp.sendRedirect("login.jsp?msg=Profile Deleted Successfully");
         }

         else if (request_type.equals("logout"))
         {
            req.getSession().removeAttribute("dir");
            req.getSession().removeAttribute("sync");
            req.getSession().invalidate();
            resp.sendRedirect("login.jsp?msg=Successfully Logged Out");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         resp.sendRedirect("error.jsp?msg=OOPS! Something went wrong");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      doGet(req, resp);
   }

}