> 将自己所学的项目构建方式全部输出为一个项目，使用现在所用的这一套技术，博众所长

项目技术：`Spring` 、`Springcloud` 、`nacos`（注册、配置中心）、`kafka`（消息引擎）、`es`（非关系型数据库，存储设备报文）、`Redis`（一级缓存）、`caffeine`（二级缓存），`MySQL`（关系型数据库），`graylog`（分布式日志监控），`Docker`（容器打包部署）

`docker-compose` （微服务编排）、`prometheus` （指标监控）、`Grafana` （指标监控展示）

> 关键节点

- [ ] 设备报文流程：设备上报 → hub（Netty接受）→ push kafka → consumer kafka → es
- [ ] 设备分组实现：user — groupIds → Cache in Redis（first level Cache）→ caffeine（second level Cache）→ custom annotation
- [ ] OTA — 指令组装与解析：抽象 & 实现
- [ ] 推送服务：逻辑数据 → kafka → 消费 → 推送记录（借鉴下Austin→消息发送平台）
- [ ] 接口服务：鉴权 → 数据响应



> 实现

现有项目 → 改进使用

- Netty编解码
- 自定义注解，caffeine二级缓存引入使用
- 设备（小熊派是否可用）上报报文 es存储，查询展示
- 推送（借鉴angle的实现方式）→ 数据组装，鉴权流程 → 失败重试