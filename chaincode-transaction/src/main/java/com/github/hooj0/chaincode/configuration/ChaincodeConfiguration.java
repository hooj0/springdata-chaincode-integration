package com.github.hooj0.chaincode.configuration;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.hooj0.fabric.sdk.commons.config.DefaultFabricConfiguration;
import io.github.hooj0.fabric.sdk.commons.store.FabricKeyValueStore;
import io.github.hooj0.fabric.sdk.commons.store.support.FileSystemKeyValueStore;
import io.github.hooj0.springdata.fabric.chaincode.config.AbstractChaincodeConfiguration;
import io.github.hooj0.springdata.fabric.chaincode.core.convert.MappingChaincodeConverter;
import io.github.hooj0.springdata.fabric.chaincode.core.support.ChaincodeTemplate;
import io.github.hooj0.springdata.fabric.chaincode.repository.config.EnableChaincodeRepositories;

/**
 * based chaincode config support 
 * @author hoojo
 * @createDate 2018年8月9日 下午2:15:57
 * @blog http://hoojo.cnblogs.com
 * @email hoojo_@126.com
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.github.hooj0.chaincode.transaction.**")
@EnableChaincodeRepositories(basePackages = "com.github.hooj0.chaincode.transaction.**.repository", considerNestedRepositories = true)
public abstract class ChaincodeConfiguration extends AbstractChaincodeConfiguration {

	private final static Logger log = LoggerFactory.getLogger(ChaincodeConfiguration.class);

	private static File storeFile;
	
	static {
		
		String fileLocation = StringUtils.defaultIfBlank(System.getenv("HYPERLEDGER_FABRIC_SDK_KV_STORE_CONFIG"), "src/main/resources/fabric-kv-store.properties");
		
		storeFile = new File(fileLocation);;
		if (!storeFile.exists()) {
			storeFile = new File("/var/fabric/config/fabric-kv-store.properties");
		} 
		
		if (!storeFile.exists()) {
			try {
				log.info("create key value store file： {}", storeFile);
				storeFile.createNewFile();
			} catch (IOException e) {
				log.error("创建文件 {} 异常", storeFile.getAbsolutePath(), e);
			}
		} else {
			log.info("find key value store file location： {}", storeFile);
		}
	}
	
	static {
		//FabricConfigurationPropertyKey.COMMONS_PREFIX = "com.blockchain.fabric.";
	}
	
	@Autowired
	private MappingChaincodeConverter mappingConverter;

	@Bean
	public ChaincodeTemplate chaincodeTemplate() throws ClassNotFoundException {
		log.info("create chaincode configuration \"ChaincodeTemplate\" instance");
		
		FabricKeyValueStore store = new FileSystemKeyValueStore(storeFile);
		
		return new ChaincodeTemplate(mappingConverter, DefaultFabricConfiguration.INSTANCE.getPropertiesConfiguration(), store);
	}
}
