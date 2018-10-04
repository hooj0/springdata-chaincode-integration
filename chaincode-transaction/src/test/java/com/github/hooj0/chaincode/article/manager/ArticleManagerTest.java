package com.github.hooj0.chaincode.article.manager;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.hooj0.chaincode.configuration.ChaincodeConfiguration;
import com.github.hooj0.chaincode.transaction.article.entity.ArticleEntity;
import com.github.hooj0.chaincode.transaction.article.manager.ArticleTransactionManager;

/**
 * ArticleManagerTest
 * @author hoojo
 * @createDate 2018年8月13日 下午3:00:45
 * @file ArticleManagerTest.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ChaincodeConfiguration.class })
public class ArticleManagerTest {

	@Autowired
	private ArticleTransactionManager manager;
	
	@Test
	public void testArticleTransaction() throws Exception {
		
		ArticleEntity article = new ArticleEntity();
		
		article.setId("1");
		article.setTitle("today");
		article.setContent("Today is a good day");
		article.setCreateDate(new Date());
		article.setCreator("joe");
		
		System.out.println(manager.write(article));
		System.out.println(manager.queryById(article));
		System.out.println(manager.queryByTitle(article));
	}
}
