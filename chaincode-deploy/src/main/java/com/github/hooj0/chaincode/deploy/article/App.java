package com.github.hooj0.chaincode.deploy.article;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.hooj0.chaincode.deploy.article.configuration.DeployArticleConfiguration;
import com.github.hooj0.chaincode.deploy.article.manager.ArticleManager;

import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;

/**
 * install chaincode article
 * @author hoojo
 * @createDate 2018年8月13日 上午11:11:11
 * @file App.java
 * @project chaincode-deploy-service
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class App {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DeployArticleConfiguration.class);
		try {
			
			ArticleManager manager = context.getBean(ArticleManager.class);
			
			ResultSet result = manager.setup();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		context.close();
	}
}
