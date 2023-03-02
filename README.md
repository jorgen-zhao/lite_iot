# lite_iot

# 项目介绍

A distributed project with microservice, building a lite IoT project to review and organize my knowledge, avoid forgeting and comfusing.

#  物联网

## 协议

1. MQTT（Message Queuing Telemetry Transport）：一种轻量级的、基于发布/订阅模式的通信协议，广泛用于传感器网络、远程监控和移动设备等场景。
2. CoAP（Constrained Application Protocol）：一种针对低功耗设备的轻量级应用层协议，基于 RESTful 风格，具有低延迟、低带宽、低能耗等特点，广泛应用于物联网领域。
3. HTTP（Hypertext Transfer Protocol）：一种广泛应用于互联网上的传输协议，也可以用于物联网通信，但其较大的数据包和较复杂的协议设计可能不适合所有 IoT 场景。
4. ZigBee：一种基于 IEEE 802.15.4 标准的低功耗无线通信协议，广泛用于家庭自动化、工业控制、智能建筑等领域。
5. LoRaWAN：一种基于 LoRa 技术的长距离低功耗无线通信协议，适用于 IoT 设备之间的低速长距离通信。
6. NB-IoT（Narrowband Internet of Things）：一种基于 3GPP 标准的窄带物联网技术，支持 IoT 设备间的窄带连接，覆盖范围广，连接稳定。
7. Sigfox：一种全球性的低功耗无线广域网（LPWAN）技术，适用于传输小量数据的场景，具有低成本、低功耗、高覆盖等特点。

## 通信技术

1. LoRa：LoRa是一种长距离、低功耗的无线通信技术，可用于连接远距离的物联网设备。
2. NB-IoT：NB-IoT是一种低功耗、低成本的蜂窝通信技术，专门用于连接低速数据传输的物联网设备。
3. Sigfox：Sigfox是一种低速、低功耗的无线通信技术，适用于连接各种不同类型的物联网设备。
4. ZigBee：ZigBee是一种短距离、低功耗的无线通信技术，适用于连接智能家居、智能电网等物联网设备。
5. Wi-Fi：Wi-Fi是一种无线局域网技术，可以用于连接各种不同类型的物联网设备。
6. Bluetooth：Bluetooth是一种短距离、低功耗的无线通信技术，适用于连接智能家居、智能健身设备等物联网设备。
7. 5G：5G是一种新一代移动通信技术，可以提供更快的数据传输速度、更低的延迟和更高的可靠性，适用于连接各种不同类型的物联网设备。



# 安全

## JWT

### 简介

JSON Web Token (JWT)是一个开放标准(RFC 7519)，它定义了一种紧凑且自包含的方式，以JSON对象的形式在各方之间安全地传输信息。可以验证和信任该信息，因为它是数字签名的。客户端只需要使用凭据对服务器进行一次身份验证。在此期间，服务器验证凭据并向客户端返回一个JSON Web令牌(JWT)。对于以后的所有请求，客户机都可以使用这个JSON Web令牌(JWT)向服务器进行身份验证，因此不需要发送用户名和密码等凭据。

### 如何工作

![jwt-workflow](/images/jwt-workflow.png)



在第一个请求期间，客户端发送一个带有用户名和密码的POST请求。

身份验证成功后，服务器生成JWT并将此JWT发送给客户机。这个JWT可以包含数据的有效负载。在所有后续请求中，客户端在头中发送这个JWT令牌。

服务器使用此令牌对用户进行身份验证。因此，在每个身份验证请求期间，我们不需要客户机向服务器发送用户名和密码，而只需要服务器向客户机发送一次JWT。

JWT有效负载可以包含用户ID这样的内容，这样当客户端再次发送JWT时，您可以确保它是由您发出的，并且可以看到它是向谁发出的。

### JWT结构

JWT有以下格式 **-header.payload.signature**

![JWT详细结构](/images/JWT详细结构.png)

## 协议解析



## 实时展示(客户端获取服务端数据的方法)



## 存储



## 流处理



## 规则引擎

