package com.github.hooj0.chaincode.deploy.article.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.hooj0.chaincode.deploy.article.repository.ArticleRepository;

import io.github.hooj0.fabric.sdk.commons.AbstractObject;
import io.github.hooj0.fabric.sdk.commons.core.ChaincodeDeployOperations;
import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;

/**
 * article manager
 * @author hoojo
 * @createDate 2018年8月13日 上午11:01:21
 * @file ArticleManager.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Component
public class ArticleManager extends AbstractObject {

	@Autowired
	private ArticleRepository repository;
	
	public ArticleManager() {
		super(ArticleManager.class);
	}
	
	public ResultSet setup() throws Exception {
		ChaincodeDeployOperations operations = repository.getChaincodeDeployOperations();

		ResultSet result = null;
		if (!operations.checkInstallChaincode(repository.getCriteria().getChaincodeID())) {
			result = repository.install();
			logger.info("安装 article Chaincode： {}", result);
		}
		
		if (!operations.checkInstantiatedChaincode(repository.getCriteria().getChaincodeID())) {
			result = repository.instantiate();
			logger.info("实例化 article Chaincode： {}", result);
		}
		
		return result;
	}
}
