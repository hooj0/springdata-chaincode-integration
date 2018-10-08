package com.github.hooj0.chaincode.transaction.article.repository;

import java.util.List;

import org.hyperledger.fabric.sdk.TransactionRequest.Type;

import com.github.hooj0.chaincode.transaction.article.entity.ArticleEntity;

import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Chaincode;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Invoke;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Query;
import io.github.hooj0.springdata.fabric.chaincode.annotations.repository.Serialization;
import io.github.hooj0.springdata.fabric.chaincode.enums.SerializationMode;
import io.github.hooj0.springdata.fabric.chaincode.repository.ChaincodeRepository;

/**
 * article transaction repository
 * @author hoojo
 * @createDate 2018年8月13日 上午9:29:29
 * @file ArticleRepository.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Chaincode(channel = "articlechannel", org = "peerOrg2", name = "article", type = Type.GO_LANG, version = "v0.1", path = "masget.com/article_cc")
public interface ArticleTransactionRepository extends ChaincodeRepository<ArticleEntity> {

	@Invoke(func = "create")
	@Serialization
	ArticleEntity write(ArticleEntity article);
	
	@Query
	ArticleEntity queryById(String id);

	@Query(clientUser = "user1")
	@Serialization(value = SerializationMode.DESERIALIZE)
	List<ArticleEntity> queryByTitle(String title);
}
