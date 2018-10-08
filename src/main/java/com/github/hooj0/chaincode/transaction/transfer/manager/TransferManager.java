package com.github.hooj0.chaincode.transaction.transfer.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hooj0.chaincode.transaction.transfer.repository.TransferRepository;

/**
 * transfer service impl
 * @author hoojo
 * @createDate 2018年8月9日 下午2:57:59
 * @file TransferManager.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Service
public class TransferManager {

	@Autowired
	private TransferRepository repo;
	
	public String transfer(String from, String to, int amount) {
		return repo.move(from, to, amount).getResult();
	}

	public int query(String account) {
		return repo.query(account);
	}
}
