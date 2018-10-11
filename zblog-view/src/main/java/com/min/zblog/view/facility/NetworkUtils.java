package com.min.zblog.view.facility;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: NetworkUtils
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author zhouzm
 * @date 2018年10月11日
 * @version 1.0
 */
public class NetworkUtils {
	private static Logger logger = LoggerFactory.getLogger(NetworkUtils.class);

	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
		String ip = request.getHeader("X-Forwarded-For");
		if (logger.isDebugEnabled()) {
			logger.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
				if (logger.isDebugEnabled()) {
					logger.debug("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
				if (logger.isDebugEnabled()) {
					logger.debug("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
				if (logger.isDebugEnabled()) {
					logger.debug("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
				if (logger.isDebugEnabled()) {
					logger.debug("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
				}
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				if (logger.isDebugEnabled()) {
					logger.debug("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
				}
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");
			for (int index = 0; index < ips.length; index++) {
				String strIp = (String) ips[index];
				if (!("unknown".equalsIgnoreCase(strIp))) {
					ip = strIp;
					break;
				}
			}
		}
		return ip;
	}
	
	/**
	 * 获取浏览器版本信息
	 * @param request
	 * @return
	 */
    public static String getBrowserVersion(HttpServletRequest request) {
		String agent = request.getHeader("user-agent");
		if(agent == null || agent.equals("")){
		   return "";
		 }
		
         if(agent.indexOf("msie 7") > 0){
             return "IE7";
         }else if(agent.indexOf("msie 8") > 0){
             return "IE8";
         }else if(agent.indexOf("msie 9") > 0){
             return "IE9";
         }else if(agent.indexOf("msie 10") > 0){
             return "IE10";
         }else if(agent.indexOf("msie") > 0){
             return "IE";
         }else if(agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0){
             return "IE11";
         }else if(agent.indexOf("opera") > 0){
             return "Opera";
         }else if(agent.indexOf("chrome") > 0){
             return "Chrome";
         }else if(agent.indexOf("firefox") > 0){
             return "Firefox";
         }else if(agent.indexOf("ucbrowser") > 0){
             return "UCBrowser";
         }else if(agent.indexOf("applewebkit") > 0){
             return "Safari";
         }else if(agent.indexOf("webkit") > 0){
             return "webkit";
         }else{
             return "Others";
         }
    }
}
