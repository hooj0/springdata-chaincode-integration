#!/bin/bash

function package() {
	# clean
	rm -rfv chaincode-deploy-service/target/jib-image.tar
	rm -rfv bin/chaincode-deploy-service/target/jib-image.tar
	
	log purple "===> mvn clean install -DskipTests=true compile jib:buildTar"
	mvn clean install -DskipTests=true compile jib:buildTar
	
}

function upload() {
	log purple "===> scp chaincode-deploy-service/target/jib-image.tar root@192.168.33.73:/tmp/jib-image.tar"
	scp -v chaincode-deploy-service/target/jib-image.tar root@192.168.33.73:/tmp/jib-image.tar
	
}

function install() {
	# clean
	log sky_blue "clean docker image & contrain"
	
	log purple "===> docker rm --force fabric-chaincode-deploy"
	count=`docker ps --filter 'name=fabric-chaincode-deploy' | wc -l`
  	if ((count > 0)); then
		docker rm --force fabric-chaincode-deploy
  	fi

	log purple "===> docker image rm chaincode/fabric-chaincode-deploy:0.2"
	docker image rm chaincode/fabric-chaincode-deploy:0.2
	
	log purple "===> docker load --input jib-image.tar"
	docker load --input /tmp/jib-image.tar
}

function up() {
	# run
	#log purple "===> docker run .... fabric-chaincode-deploy"
	
	#HYPERLEDGER_FABRIC_SDK_COMMONS_CONFIG="produce/fabric-chaincode.properties"
	#HYPERLEDGER_FABRIC_SDK_KV_STORE_CONFIG="produce/fabric-kv-store.properties"
	#HYPERLEDGER_FABRIC_SDK_COMMONS_NETWORK_HOST=localhost
	#docker run -e HYPERLEDGER_FABRIC_SDK_COMMONS_NETWORK_HOST=localhost -it --rm --name fabric-chaincode-deploy chaincode/fabric-chaincode-deploy:0.1
	
	# start
	log purple "===> docker-compose -f docker-compose.yaml -f docker-compose-net.yaml up"
	docker-compose -f docker-compose.yaml -f docker-compose-net.yaml up
}

function start() {	
	# start
	log purple "===> docker-compose -f docker-compose.yaml -f docker-compose-net.yaml start"
	docker-compose -f docker-compose.yaml -f docker-compose-net.yaml start
}

function stop() {	
	# start
	log purple "===> docker-compose -f docker-compose.yaml -f docker-compose-net.yaml stop"
	docker-compose -f docker-compose.yaml -f docker-compose-net.yaml stop
}

function log() {
	# 字颜色：30—–37
	# 字背景颜色范围：40—–47
	case $1 in
		"red")
			echo -e "\033[31m$2\033[0m" # 红色字
		;; 
		"yellow")
			echo -e "\033[33m$2\033[0m" # 黄色字
		;; 
		"green")
			echo -e "\033[32m$2\033[0m" # 绿色字
		;; 
		"blue")
			echo -e "\033[34m$2\033[0m" # 蓝色字
		;; 
		"purple")
			echo -e "\033[35m$2\033[0m" # 紫色字
		;; 
		"sky_blue")
			echo -e "\033[36m$2\033[0m" # 天蓝字
		;; 
		"white")
			echo -e "\033[37m$2\033[0m" # 白色字
		;; 
		"_black")
			echo -e "\033[40;37m $2 \033[0m" # 黑底白字
		;; 
		"_red")
			echo -e "\033[41;30m $2 \033[0m" # 红底黑字
		;; 
		*)
			echo "$2"
		;;
	esac
}

for opt in "$@"; do

	case "$opt" in
	    package)
	        package
	    ;;
	    upload)
	        upload
		;;
	    publish)
	        package
	        log blue "package Done!"
	        
	        upload
	        log blue "upload Done!"
		;;
	    install)
	        install
		;;
	    start)
	        start
		;;
		stop)
	        stop
		;;
		reup)
	        docker-compose -f docker-compose.yaml -f docker-compose-net.yaml down
	        log blue "down Done!"
	        
	        up
	        log blue "up Done!"
		;;
	    restart)
	        stop
	        log blue "stop Done!"
	        
	        start
	        log blue "start Done!"
		;;
		restore)
	        install
	        log blue "install Done!"
	        
	        up
	        log blue "up Done!"
		;;
	    *)
	        echo $"Usage: $0 {package|upload|publish|install|start|stop|reup|restart|restore}"
	        exit 1
		;;
	esac
done	