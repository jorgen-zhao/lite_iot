# lite_iot

# 项目介绍

A distributed project with microservice, building a lite IoT project to review and organize my knowledge, avoid forgeting and comfusing.

# 项目架构

# 里程碑

- [ ] 搭建`RBAC`框架，实现人员、角色、资源动态配置
- [ ] 搭建hub层，解析设备上报指令
- [ ] 设备分组，根据人员获取组织下的设备信息
- [ ] OTA设计与实现
- [ ] 接入微信服务号，设备上报通过服务号进行推送（支持动态配置&推送）
- [ ] 配置服务器和docker容器以及SpringBoot应用的监控（prometheus+Grafana+auctuator）
- [ ] 引入kafka,架构调整
- [ ] 引入easy-poi,支持Excel批量导入导出
- [ ] 对接海康威视，支持设备上报时，抓拍图片
- [ ] 引入websocket，设备上报时实时推送前端呈现
- [ ] 上报预处理方案（流程引擎？）
- [ ] 日志链路数据追踪 + 注解式打印日志（优雅打印日志以及数据埋点）
- [ ] 接入GrayLog分布式日志收集框架
- [ ] Docker打包，支持Docker + Jenkis部署
- [ ] ElasticSeach引入，迁移上报报文数据

### 🚏代办

- [ ]  基于小熊开发板完成 → 查看开发板 拥有哪些 现成模组可用
- [ ]  如果搞不定，查询可用的树莓派 — 直接在运行Linux系统上进行开发  → 可拓展模组

### ⚓功能

1. 设备定时上报数据
2. server → device：能正常响应且执行
3. 实现OTA功能
![lite_iot功能图示](/images/lite_iot.png)

### 🤔事项

1. 定义协议 → 协议解析 & 组装
    - [ ]  涉及流程：1️⃣心跳/定时打卡  2️⃣指令 3️⃣OTA指令
    - [ ]  异常情况
2. web功能
    1. 设备管理
    2. 在线协议
    3. OTA
    4. 报文查看
    5. 指令调试
3. 拓展功能
    1. 微信模板消息推送
    2. 视频监控
4. 额外功能：小程序接入
