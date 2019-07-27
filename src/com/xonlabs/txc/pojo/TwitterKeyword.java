package com.xonlabs.txc.pojo;

import java.sql.Timestamp;

public class TwitterKeyword
{
   private String keyword;
   private String user;
   private Timestamp entry_time;

   public String getKeyword()
   {
      return keyword;
   }

   public void setKeyword(String keyword)
   {
      this.keyword = keyword;
   }

   public String getUser()
   {
      return user;
   }

   public void setUser(String user)
   {
      this.user = user;
   }

   public Timestamp getEntry_time()
   {
      return entry_time;
   }

   public void setEntry_time(Timestamp entry_time)
   {
      this.entry_time = entry_time;
   }

}
