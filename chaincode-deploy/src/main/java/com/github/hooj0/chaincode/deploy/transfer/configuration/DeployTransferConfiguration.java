package com.github.hooj0.chaincode.deploy.transfer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.hooj0.chaincode.configuration.ChaincodeConfiguration;

import io.github.hooj0.springdata.fabric.chaincode.repository.config.EnableChaincodeRepositories;

/**
 * transfer chaincode config 
 * 
 * @author hoojo
 * @createDate 2018年8月9日 下午2:12:44
 * @file DeployTransferConfiguration.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.github.hooj0.chaincode.deploy.transfer")
@EnableChaincodeRepositories(basePackages = "com.github.hooj0.chaincode.deploy.transfer.repository", considerNestedRepositories = true)
public class DeployTransferConfiguration extends ChaincodeConfiguration {
	
}
