package com.github.hooj0.chaincode.deploy.article.repository;

import org.hyperledger.fabric.sdk.TransactionRequest.Type;

import com.github.hooj0.chaincode.deploy.article.entity.ArticleEntity;

import io.github.hooj0.fabric.sdk.commons.core.execution.result.ResultSet;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Chaincode;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Install;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Instantiate;
import io.github.hooj0.springdata.fabric.chaincode.repository.DeployChaincodeRepository;

/**
 * article repository
 * @author hoojo
 * @createDate 2018年8月13日 上午10:41:48
 * @file ArticleRepository.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Chaincode(channel = "articlechannel", org = "peerOrg2", name = "article", type = Type.GO_LANG, version = "v0.1", path = "hoojo.com/article_cc")
public interface ArticleRepository extends DeployChaincodeRepository<ArticleEntity> {

	@Install(chaincodeLocation = "chaincode/go/article/v0.1")
	ResultSet install();
	
	@Instantiate(endorsementPolicyFile = "chaincode-endorsement-policy.yaml", func = "init")
	ResultSet instantiate();
}
