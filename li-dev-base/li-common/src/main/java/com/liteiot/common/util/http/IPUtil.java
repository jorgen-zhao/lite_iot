package com.liteiot.common.util.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

@Slf4j
public class IPUtil {

	private static final String UNKNOWN = "unknown";
	private static final String LOCALHOST = "127.0.0.1";
	private static final String SEPARATOR = ",";
	private static final String X_REAL_IP = "X-Real-IP";// nginx需要配置

//	public static String getIp(ServerHttpRequest request) {
//		String ipAddress = null;
//		try {
//			HttpHeaders headers = request.getHeaders();
//			ipAddress = headers.getFirst(X_REAL_IP);
//			if (!StringUtils.hasLength(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
//				ipAddress = headers.getFirst("x-forwarded-for");
//			}
//			if (!StringUtils.hasLength(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
//				ipAddress = headers.getFirst("Proxy-Client-IP");
//			}
//			if (!StringUtils.hasLength(ipAddress) ||  UNKNOWN.equalsIgnoreCase(ipAddress)) {
//				ipAddress = headers.getFirst("WL-Proxy-Client-IP");
//			}
//			if (!StringUtils.hasLength(ipAddress) ||  UNKNOWN.equalsIgnoreCase(ipAddress)) {
//				InetSocketAddress remoteAddress = request.getRemoteAddress();
//				InetAddress address = remoteAddress.getAddress();
//				ipAddress = address.getHostAddress();
//				if (LOCALHOST.equals(ipAddress)) {
//					InetAddress inet = null;
//					try {
//						inet = InetAddress.getLocalHost();
//					} catch (UnknownHostException e) {
//						e.printStackTrace();
//					}
//					ipAddress = inet.getHostAddress();
//				}
//			}
//			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
//			// "***.***.***.***".length()
//			if (ipAddress != null && ipAddress.length() > 15) {
//				if (ipAddress.indexOf(SEPARATOR) > 0) {
//					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//				}
//			}
//		} catch (Exception e) {
//			log.error("获取ip异常:{}",e.getMessage());
//		}
//		return ipAddress;
//	}

	// 多次反向代理后会有多个ip值 的分割符
	private final static String IP_UTILS_FLAG = ",";
	// 本地 IP
	private final static String LOCALHOST_IP = "0:0:0:0:0:0:0:1";
	private final static String LOCALHOST_IP1 = "127.0.0.1";

	public static String getIP(ServerHttpRequest request){
		// 根据 HttpHeaders 获取 请求 IP地址
		String ip = request.getHeaders().getFirst("X-Forwarded-For");
		if (!StringUtils.hasLength(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("x-forwarded-for");
			if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
				// 多次反向代理后会有多个ip值，第一个ip才是真实ip
				if (ip.contains(IP_UTILS_FLAG)) {
					ip = ip.split(IP_UTILS_FLAG)[0];
				}
			}
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeaders().getFirst("X-Real-IP");
		}
		//兼容k8s集群获取ip
		if (!StringUtils.hasLength(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddress().getAddress().getHostAddress();
			if (LOCALHOST_IP1.equalsIgnoreCase(ip) || LOCALHOST_IP.equalsIgnoreCase(ip)) {
				//根据网卡取本机配置的IP
				InetAddress iNet = null;
				try {
					iNet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("getClientIp error: ", e);
				}
				ip = iNet.getHostAddress();
			}
		}
		return ip;
	}
}
