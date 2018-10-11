package com.github.hooj0.chaincode.deploy.transfer.repository;

import org.hyperledger.fabric.sdk.TransactionRequest.Type;
import org.springframework.stereotype.Repository;

import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Chaincode;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Channel;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Install;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Instantiate;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Upgrade;
import io.github.hooj0.springdata.fabric.chaincode.repository.DeployChaincodeRepository;

/**
 * 转账 repo
 * @author hoojo
 * @createDate 2018年8月9日 上午11:28:31
 * @file TransferRepository.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Channel(name = "mychannel", org = "peerOrg1")
@Chaincode(name = "example_cc_go", type = Type.GO_LANG, version = "v0.1", path = "github.com/example_cc")
@Repository("transferRepository")
public interface TransferRepository extends DeployChaincodeRepository<Object> {

	@Install(chaincodeLocation = "chaincode/go/transfer/v0.1")
	ResultSet install();
	
	@Instantiate(endorsementPolicyFile = "chaincode-endorsement-policy.yaml", func = "init", args = { "a", "?0", "b", "?1" })
	ResultSet instantiate(int aAmount, int bAmount);
	
	@Install(clientUser = "admin", chaincodeLocation = "chaincode/go/transfer/v0.2", version = "v0.2")
	ResultSet installNewVersion();
	
	@Chaincode(channel = "mychannel", org = "peerOrg1", name = "example_cc_go", version = "v0.2")
	@Repository("newTransferRepository")
	public interface NewTransferRepository extends TransferRepository {

		@Upgrade(clientUser = "admin", endorsementPolicyFile = "chaincode-endorsement-policy.yaml", func = "init")
		ResultSet upgrade();
	}
}
