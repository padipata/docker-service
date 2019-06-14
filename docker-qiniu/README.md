# 七牛云

### 描述

七里云相关接口，用于docker服务化

端口号：10001（需要找root进行服务登记）

启动后访问接口文档: http://localhost:10001/swagger-ui.html

### 变量说明

- ACCESSKEY：七牛云秘钥（例子：CCywlxZUROwBCBNJQWUdPeQncmBDjqI1Nwg3UXSe）
- SECRETKEY：七牛云秘钥（例子：CCywlxZUROwBCBNJQWUdPeQncmBDjqI1Nwg3UXSw）
- BUCKET：七牛云空间（例子：cbs-oss）
- BASEPATH：图片前缀（后面需要加/，例子：http://cbsqiniu.yipage.cn/）

### 运行

- 拉取镜像

`docker pull padipata/docker-qiniu`

- 运行镜像

`docker run -p 10001:8080 -e ACCESSKEY=xxx -e SECRETKEY=xxx -e BUCKET=xxx -e BASEPATH=xxx padipata/docker-qiniu:latest`

- 例子

`docker run -p 10001:8080 -e ACCESSKEY=CCywlxZUROwBCBNJQWUdPeQncmBDjqI1Nwg3UXSe -e SECRETKEY=CCywlxZUROwBCBNJQWUdPeQncmBDjqI1Nwg3UXSw -e BUCKET=cbs-oss -e BASEPATH=http://cbsqiniu.yipage.cn/ padipata/docker-qiniu:latest`
