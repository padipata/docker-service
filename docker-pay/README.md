# 支付

### 描述

支付相关接口，用于docker服务化

##### 完成情况

* [x] 微信公众号支付
* [x] 微信H5支付
* [ ] 支付宝H5支付
* [ ] 银联支付

端口号：10002（需要找root进行服务登记）

启动后访问接口文档: http://localhost:10002/swagger-ui.html

### 变量说明

- WXAPPID：微信公众号appid
- WXSECRET：微信公众号秘钥
- WXMCHID：微信公众号商户号
- WXPAYSIGNKEY：微信公众号支付秘钥（建议生成两次）

### 运行

- 拉取镜像

`docker pull padipata/docker-pay`

- 运行镜像

`docker run -p 10002:8080 -e WXAPPID=xxx -e WXSECRET=xxx -e WXMCHID=xxx -e WXPAYSIGNKEY=xxx padipata/docker-pay:latest`