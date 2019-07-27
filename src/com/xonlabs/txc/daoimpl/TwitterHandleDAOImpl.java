package com.xonlabs.txc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xonlabs.txc.dao.TwitterHandleDAO;
import com.xonlabs.txc.pojo.TwitterHandle;
import com.xonlabs.txc.util.MySQLUtility;

public class TwitterHandleDAOImpl implements TwitterHandleDAO
{

   @Override
   public void create(TwitterHandle th) throws Exception
   {
      Connection con = null;
      try
      {
         con = MySQLUtility.connect();
         PreparedStatement ps = con.prepareStatement("insert into twitterhandles values(?,?,?)");
         ps.setString(1, th.getHandle());
         ps.setString(2, th.getUser());
         ps.setTimestamp(3, th.getEntry_time());
         ps.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
   }

   @Override
   public List<TwitterHandle> getTwitterHandlesByUser(String user) throws Exception
   {
      Connection con = null;
      List<TwitterHandle> result = new ArrayList<>();
      try
      {
         con = MySQLUtility.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from twitterhandles where email='" + user + "' ");
         while (rs.next())
         {
            TwitterHandle th = new TwitterHandle();
            th.setEntry_time(rs.getTimestamp("entry_time"));
            th.setHandle(rs.getString("handle"));
            th.setUser(user);
            result.add(th);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         return null;
      }
      finally
      {
         con.close();
      }
      return result;
   }

   @Override
   public void delete(String handle, String user) throws Exception
   {
      Connection con = null;
      try
      {
         con = MySQLUtility.connect();
         con.createStatement().execute("delete from twitterhandles where handle='" + handle + "' and email='" + user + "' ");
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }

   }

}
