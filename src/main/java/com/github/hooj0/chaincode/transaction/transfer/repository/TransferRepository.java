package com.github.hooj0.chaincode.transaction.transfer.repository;

import org.hyperledger.fabric.sdk.TransactionRequest.Type;

import com.github.hooj0.chaincode.transaction.transfer.entity.TransferAccount;

import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Chaincode;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Invoke;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Query;
import io.github.hooj0.springdata.fabric.chaincode.repository.ChaincodeRepository;

/**
 * transfer transaction repo
 * @author hoojo
 * @createDate 2018年8月9日 下午3:57:47
 * @file TransferRepository.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Chaincode(channel = "mychannel", org = "peerOrg1", name = "example_cc_go", type = Type.GO_LANG, version = "v0.1", path = "github.com/example_cc")
public interface TransferRepository extends ChaincodeRepository<TransferAccount> {

	@Query(clientUser = "user1")
	int query(String account);
	
	@Invoke(clientUser = "user1")
	ResultSet move(String from, String to, int amount);
}
