package com.xonlabs.txc.vader;

import javax.servlet.http.HttpSession;

public class TwitterHandleVaderThread implements Runnable
{
   HttpSession session;
   String email;
   Thread t;

   public TwitterHandleVaderThread(String email, HttpSession session)
   {
      this.email = email;
      this.session = session;
      t = new Thread(this);
      t.start();
   }

   @Override
   public void run()
   {
      try
      {
         session.setAttribute("inprogress", "yes");
         VaderService service = new VaderService();
         session.setAttribute("twitterhandleresults", service.analyzeTwitterHandles(email));
         session.removeAttribute("inprogress");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
