package com.min.zblog.facility.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

/**
 * #心跳、进程运行状态更新
 * @author zhouzm
 *
 */
public class HeartbeatHandler implements SmartLifecycle {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private boolean runningFlag = false;
	
	@Override
	public void start() {
		logger.info("启动进程");
		runningFlag = true;
	}

	@Override
	public void stop() {
		logger.info("关闭进程");
		runningFlag = false;
	}

	@Override
	public boolean isRunning() {
		return runningFlag;
	}

	@Override
	public int getPhase() {
		//在最后一个初始化，第一个关闭
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		logger.info("关闭进程");
		callback.run();
		runningFlag = false;
	}
	
	public void heartbeat()
	{
		MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
		MemoryUsage memUsage = mbean.getHeapMemoryUsage();
		String memUsageStr = MessageFormat.format("{0,number,0.00}% ({1}MB)", (1.0 - (double)memUsage.getUsed() / memUsage.getMax()) * 100, (memUsage.getMax() - memUsage.getUsed()) >> 20);
		logger.info("心跳数据，剩余内存："+memUsageStr);
	}

}
