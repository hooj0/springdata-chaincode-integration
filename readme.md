# `Spring Data Fabric Chaincode Framework Integrate`

`SpringData Fabric Chaincode` 整合使用示例，用于展示在实际开发环境和生产环境中，将Chaincode开发和部署分隔为两个不同的独立子模块，`chaincode-deploy`用于部署链码，`chaincode-transaction`用于做交易的查询和交易数据修改的业务操作。

## 运行环境

- `fabric-sdk-java` v1.1+
- `spring data` 2.1.0+
- `jdk` 8+

**`extra jar`**

- `fabric-sdk-commons` v1.1+
- `spring-data-fabric-chaincode` v1.1+

## `chaincode-deploy` 部署链码

部署链码包含链码的安装、实例化、更新操作，可以将部署操作打包成 docker 镜像，单独在服务器上进行docker容器部署。这样能很好的和docker进行链接，也可以用普通的方式进行更新操作。

## `chaincode-transaction` 业务服务

业务服务主要完成核心的业务逻辑，完成业务的操作。后期会频繁修改和部署更新，它是面向所有开发者的。