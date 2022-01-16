# pubyun

内网穿透工具

> 配合 [Pubyun](https://www.pubyun.com/) 免费实现内网穿透，不受网速限制

## 如何使用
### 修改配置
需要在application.yml中添加个人公云域名及用户名密码，如下范例：
```xml
pubyun:
    domain: "xxx.f3322.net" // 此处为公云域名
    auth-token: "xxx:xxx" // 此处为 用户名:密码（可在域名中设置独立root更新密码）
```
### 打包
```sh
mvn clean package
```

### 运行
将 pubyun.jar 与 pubyun.sh 放入同级目录，运行：
```sh
sh pubyun.sh
```
