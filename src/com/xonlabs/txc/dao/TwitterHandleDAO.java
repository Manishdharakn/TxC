package com.xonlabs.txc.dao;

import java.util.List;

import com.xonlabs.txc.pojo.TwitterHandle;

public interface TwitterHandleDAO
{

   public void create(TwitterHandle th) throws Exception;

   public List<TwitterHandle> getTwitterHandlesByUser(String user) throws Exception;

   public void delete(String handle, String user) throws Exception;

}
