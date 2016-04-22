package com.bjym.mobiledata.Time;

public class ThreadLocalSend {
	
	private static ThreadLocal<Boolean> IsSend=new ThreadLocal<Boolean>();
	public static boolean getIsSend()
	{
		return IsSend.get();
	}
	
	
	
	public static void setIsSend(Boolean b)
	{
		IsSend.set(b);
	}
	public static void InitIsSend()
	{
		IsSend.set(false);
	}
	
	
}
