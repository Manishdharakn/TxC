package com.xonlabs.txc.pojo;

import java.sql.Timestamp;

public class TwitterHandle
{
   private String handle;
   private String user;
   private Timestamp entry_time;

   public String getHandle()
   {
      return handle;
   }

   public void setHandle(String handle)
   {
      this.handle = handle;
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
