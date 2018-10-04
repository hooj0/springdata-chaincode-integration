package com.github.hooj0.chaincode.transaction.article.entity;

import java.util.Date;

import io.github.hooj0.springdata.fabric.chaincode.annotations.Entity;
import io.github.hooj0.springdata.fabric.chaincode.annotations.Field;
import io.github.hooj0.springdata.fabric.chaincode.annotations.Transient;
import io.github.hooj0.springdata.fabric.chaincode.domain.AbstractEntity;

/**
 * block chain article entity
 * @author hoojo
 * @createDate 2018年8月8日 上午11:48:23
 * @file ArticleEntity.java
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Entity
public class ArticleEntity extends AbstractEntity {

	private String id;
	
	/** 创建者 */
	@Transient
	private String creator;
	/** 标题 */
	private String title;
	/** 内容 */
	private String content;
	/** 创建时间 */
	@Field(transientAlias = "date")
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
