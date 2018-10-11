package com.github.hooj0.chaincode.deploy.transfer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.hooj0.chaincode.deploy.transfer.configuration.DeployTransferConfiguration;
import com.github.hooj0.chaincode.deploy.transfer.manager.TransferManager;

import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;

/**
 * transfer chaincode deploy application
 * @author hoojo
 * @createDate 2018年8月9日 下午2:22:19
 * @file App.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
public class App {

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DeployTransferConfiguration.class);
		
		try {
			runTransferExample(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		context.close();
	}
	
	public static void runTransferExample(AnnotationConfigApplicationContext context) throws Exception {
		TransferManager service = context.getBean(TransferManager.class);
		
		// install chaincode
		service.install();
		
		// instantiate chaincode
		ResultSet rs = service.instantiate(1000, 800);
		System.out.println(rs);
		
		// upgrade chaincode
		rs = service.upgrade();
		System.out.println(rs);
		
		System.err.println("done!");
	}
}
