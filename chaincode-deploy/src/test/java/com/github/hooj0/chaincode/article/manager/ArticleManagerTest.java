package com.github.hooj0.chaincode.article.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.hooj0.chaincode.deploy.article.configuration.DeployArticleConfiguration;
import com.github.hooj0.chaincode.deploy.article.manager.ArticleManager;

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
@ContextConfiguration(classes = { DeployArticleConfiguration.class })
public class ArticleManagerTest {

	@Autowired
	private ArticleManager manager;
	
	@Test
	public void testInstall() throws Exception {
		
		System.out.println(manager.setup());
	}
}
