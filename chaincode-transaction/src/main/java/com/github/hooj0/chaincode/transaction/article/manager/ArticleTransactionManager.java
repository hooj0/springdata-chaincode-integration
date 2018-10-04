package com.github.hooj0.chaincode.transaction.article.manager;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hooj0.chaincode.transaction.article.entity.ArticleEntity;
import com.github.hooj0.chaincode.transaction.article.repository.ArticleTransactionRepository;
import com.google.common.base.Strings;

/**
 * Article Service transaction manager
 * @author hoojo
 * @createDate 2018年8月8日 上午11:55:11
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Service
public class ArticleTransactionManager {

	@Autowired
	private ArticleTransactionRepository repository;
	
	public ArticleEntity write(ArticleEntity article) {
		checkNotNull(article, "article 对象不能为空");
		
		return repository.write(article);
	}
	
	public ArticleEntity queryById(ArticleEntity article) {
		checkNotNull(article, "article 对象不能为空");
		checkArgument(!Strings.isNullOrEmpty(article.getId()), "id 不能为空");
		
		return repository.queryById(article.getId());
	}
	
	public List<ArticleEntity> queryByTitle(ArticleEntity article) {
		checkNotNull(article, "article 对象不能为空");
		checkArgument(!Strings.isNullOrEmpty(article.getTitle()), "title 不能为空");
		
		return repository.queryByTitle(article.getTitle());
	}
}
