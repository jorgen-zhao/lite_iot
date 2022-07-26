# LiteIoT
A distributed project with microservice, building a lite IoT project to review and organize my knowledge, avoid forgeting and comfusing.
## IoT物联网项目

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