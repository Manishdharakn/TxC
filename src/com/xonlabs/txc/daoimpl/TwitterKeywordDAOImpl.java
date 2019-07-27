package com.xonlabs.txc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xonlabs.txc.dao.TwitterKeywordDAO;
import com.xonlabs.txc.pojo.TwitterHandle;
import com.xonlabs.txc.pojo.TwitterKeyword;
import com.xonlabs.txc.util.MySQLUtility;

public class TwitterKeywordDAOImpl implements TwitterKeywordDAO
{

   @Override
   public void create(TwitterKeyword tk) throws Exception
   {
      Connection con = null;
      try
      {
         con = MySQLUtility.connect();
         PreparedStatement ps = con.prepareStatement("insert into twitterkeywords values(?,?,?)");
         ps.setString(1, tk.getKeyword());
         ps.setString(2, tk.getUser());
         ps.setTimestamp(3, tk.getEntry_time());
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
   public List<TwitterKeyword> getTwitterKeywordsByUser(String user) throws Exception
   {
      Connection con = null;
      List<TwitterKeyword> result = new ArrayList<>();
      try
      {
         con = MySQLUtility.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from twitterkeywords where email='" + user + "' ");
         while (rs.next())
         {
            TwitterKeyword tk = new TwitterKeyword();
            tk.setEntry_time(rs.getTimestamp("entry_time"));
            tk.setKeyword(rs.getString("keyword"));
            tk.setUser(user);
            result.add(tk);
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
   public void delete(String keyword, String user) throws Exception
   {
      Connection con = null;
      try
      {
         con = MySQLUtility.connect();
         con.createStatement().execute("delete from twitterkeywords where keyword='" + keyword + "' and email='" + user + "' ");
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
