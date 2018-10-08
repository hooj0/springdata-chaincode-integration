package com.github.hooj0.chaincode.deploy.transfer.manager;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.hooj0.chaincode.deploy.transfer.repository.TransferRepository;
import com.github.hooj0.chaincode.deploy.transfer.repository.TransferRepository.NewTransferRepository;

import io.github.hooj0.fabric.sdk.commons.AbstractObject;
import io.github.hooj0.fabric.sdk.commons.core.ChaincodeDeployOperations;
import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;

/**
 * 转账交易服务实现
 * @author hoojo
 * @createDate 2018年8月9日 上午11:41:32
 * @file TransferServiceImpl.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Component
public class TransferManager extends AbstractObject {

	public TransferManager() {
		super(TransferManager.class);
	}
	
	@Autowired
	@Qualifier("transferRepository")
	private TransferRepository repository;
	
	@Autowired
	@Qualifier("newTransferRepository")
	private NewTransferRepository newTransferRepo;
	
	public ResultSet install() throws Exception {
		if (repository.getChaincodeDeployOperations().checkInstallChaincode(repository.getCriteria().getChaincodeID())) {
			return null;
		}
		
		return repository.install();
	}
	
	public ResultSet instantiate(int aAmount, int bAmount) throws Exception {
		ChaincodeDeployOperations operations = repository.getChaincodeDeployOperations();
		
		if (operations.checkInstantiatedChaincode(repository.getCriteria().getChaincodeID())) {
			return null;
		}
		if (operations.checkInstantiatedChaincode(newTransferRepo.getCriteria().getChaincodeID())) {
			return null;
		}
		
		return repository.instantiate(aAmount, bAmount);
	}
	
	public ResultSet upgrade() throws Exception {
		
		ChaincodeDeployOperations operations = repository.getChaincodeDeployOperations();
		ChaincodeID cc11_1 = repository.getCriteria().getChaincodeID();
		
		if (!operations.checkChaincode(cc11_1, repository.getOrganization())) {
			logger.warn(cc11_1 + " chaincode not install or instantiate!");
			return null;
		} else {
			logger.info(cc11_1 + " chaincode already install & instantiate!");
		}
		
		ChaincodeID cc11_2 = newTransferRepo.getCriteria().getChaincodeID();
		
		if (!operations.checkInstallChaincode(cc11_2)) {
			repository.installNewVersion();
		} else {
			logger.info(cc11_2 + " chaincode already install!");
		}
		
		if (operations.checkInstantiatedChaincode(cc11_2)) {
			logger.warn(cc11_2 + " chaincode already instantiate!");
			return null;
		} 
		
		ResultSet rs =  newTransferRepo.upgrade();
		if (!operations.checkChaincode(cc11_2, repository.getOrganization())) {
			logger.warn(cc11_2 + " chaincode already instantiate!");
			return null;
		}
		
		return rs;
	}
}
