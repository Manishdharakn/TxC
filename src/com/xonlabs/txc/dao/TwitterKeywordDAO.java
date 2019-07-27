package com.xonlabs.txc.dao;

import java.util.List;

import com.xonlabs.txc.pojo.TwitterKeyword;

public interface TwitterKeywordDAO
{
   public void create(TwitterKeyword tk) throws Exception;

   public List<TwitterKeyword> getTwitterKeywordsByUser(String user) throws Exception;

   public void delete(String keyword, String user) throws Exception;

}
