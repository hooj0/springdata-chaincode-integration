package com.github.hooj0.chaincode.transfer.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.hooj0.chaincode.configuration.ChaincodeConfiguration;
import com.github.hooj0.chaincode.transaction.transfer.manager.TransferManager;
import com.github.hooj0.chaincode.transaction.transfer.repository.TransferRepository;

import io.github.hooj0.springdata.fabric.chaincode.repository.support.creator.ProposalBuilder;
import io.github.hooj0.springdata.fabric.chaincode.repository.support.creator.ProposalBuilder.QueryProposal;

/**
 * TransferManagerTest
 * @author hoojo
 * @createDate 2018年8月9日 下午6:09:34
 * @file TransferManagerTest.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ChaincodeConfiguration.class })
public class TransferManagerTest {

	@Autowired
	private TransferManager manager;
	
	@Autowired
	@Qualifier("transferRepository")
	private TransferRepository repository;
	
	@Test
	public void testTransaction() throws Exception {
		QueryProposal proposal = ProposalBuilder.query();
		proposal.clientUser("user");
		
		System.out.println(manager.query("a"));

		System.out.println(manager.transfer("a", "b", 100));
	}
}
