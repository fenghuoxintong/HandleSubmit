package com.bjym.mobiledata.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.bjym.mobiledata.bean.MonitorBean;
import com.bjym.mobiledata.control.HandleSubmitServlet;
import com.bjym.mobiledata.redis.impl.RedisImpl;
import com.bjym.mobiledata.redis.interfaces.RedisInterface;
import com.bjym.mobiledata.utils.LoggerUtil;
import com.bjym.mobiledata.utils.SerializeUtils;

public class OnTime {
	private RedisInterface redisInterface = new RedisImpl();
	private String nodecode;
	
	public OnTime(String nodecode,int second) {
		this.nodecode=nodecode;
		Timer timer = new Timer();
		timer.schedule(new RefreshAccessTokenTask(), 0, second*1000);
	}
	
	private class RefreshAccessTokenTask extends TimerTask {
		@Override
		public void run() {
			
			List<byte[]> taskList2 = new ArrayList<byte[]>();
			Integer allcount=HandleSubmitServlet.allcout;
			Integer success=HandleSubmitServlet.success;
			Integer fail=HandleSubmitServlet.fail;
			LoggerUtil.info("总数："+allcount+"成功： "+success+"失败："+fail);
			MonitorBean monitor=new MonitorBean("DHHttpHdSubmit001",allcount,success,fail,0, 0, 0, 0, 0);
			taskList2.add(SerializeUtils.serialize(monitor));
			try{
				redisInterface.addByteToList("monitorbean", taskList2);
			}catch (Exception e) {
				// TODO: handle exception
				LoggerUtil.info("添加队列异常");
			}
			
			//清零
			HandleSubmitServlet.allcout=0;
			HandleSubmitServlet.success=0;
			HandleSubmitServlet.fail=0;
			//ThreadLocalSend.setIsSend(true);
			
		}
	}
}
