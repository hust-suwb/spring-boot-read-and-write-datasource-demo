# SpringBoot MySQL读写分离示例

这个仓库包含了使用SpringBoot实现MySQL读写分离的示例代码。

## 版本说明

- `tag v0.1`：展示了使用SpringBoot结合JDBC直连数据库实现读写分离的基础版本。
- `tag v0.2`：演示了如何通过SpringBoot结合MyBatis实现读写分离，并支持自动路由读写请求。
- `tag v0.3`：增加了在有数据源异常监控功能的前提下实现从库异常快速恢复的功能。

## 如何使用

克隆仓库后，您可以检出不同的标签来查看每个版本的具体实现。

```bash
git clone https://github.com/hust-suwb/spring-boot-read-and-write-datasource-demo.git
cd spring-boot-read-and-write-datasource-demo
git checkout tags/v0.1 # 或者 v0.2 查看不同版本