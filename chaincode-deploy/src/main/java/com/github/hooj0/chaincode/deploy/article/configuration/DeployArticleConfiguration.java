package com.github.hooj0.chaincode.deploy.article.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.hooj0.chaincode.configuration.ChaincodeConfiguration;

import io.github.hooj0.springdata.fabric.chaincode.repository.config.EnableChaincodeRepositories;

/**
 * deploy article configuration
 * @author hoojo
 * @createDate 2018年8月13日 上午11:10:04
 * @file DeployArticleConfiguration.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.github.hooj0.chaincode.deploy.article")
@EnableChaincodeRepositories(basePackages = "com.github.hooj0.chaincode.deploy.article.repository", considerNestedRepositories = true)
public class DeployArticleConfiguration extends ChaincodeConfiguration {

}
