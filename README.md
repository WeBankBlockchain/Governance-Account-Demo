# Governance-Account-Demo

## 使用步骤

### 克隆代码
```
git clone https://github.com/WeBankBlockchain/Governance-Account-Demo.git
```

### 拷贝链证书

```
cd Gov-Acct-Demo
```

将SDK证书拷贝到项目的config目录下(这里假设SDK证书位于~/fisco/nodes/127.0.0.1/sdk目录)：
```
mkdir -p config && cp -r ~/fisco/nodes/127.0.0.1/sdk/* config
```

### 修改配置文件
配置文件位于 src/main/resources/application.properties 目录下

```
## 节点地址和channel端口
system.nodeStr=127.0.0.1:20200
## 群组ID
system.groupId=1

## 密码类型 0-非国密，1-国密
system.encryptType=0

## 配置的客户端私钥，如不配置，则随机生成一个
#system.hexPrivateKey=33b07356be6d05a930a104d20f482e36e55040e2f8d1af6169419e5e231629ac

## 是否默认开启创建治理合约
system.defaultGovernanceEnabled=true
```

### 执行构建和测试
```
./gradlew build
```

后续，也可单独执行测试案例。
```
./gradlew test
```

### DIY测试代码

导入IDE中，支持导入IDEA或Eclipse中。



修改src/test/java目录下的测试代码。


