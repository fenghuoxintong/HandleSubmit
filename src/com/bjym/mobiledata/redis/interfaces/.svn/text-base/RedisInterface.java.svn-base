package com.bjym.mobiledata.redis.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract interface RedisInterface {
	public abstract boolean isShutdown();

	public abstract boolean Shutdown();

	public abstract int setKey(String paramString, byte[] paramArrayOfByte);

	public abstract byte[] getByteFromList(String paramString);

	public abstract List<byte[]> getByteFromList(String paramString,
			int paramInt);

	public abstract List<byte[]> getByteFromLists(String[] paramArrayOfString,
			int paramInt);

	public abstract int addByteToList(String paramString,
			byte[] paramArrayOfByte);

	public abstract int addByteToList(String paramString, List<byte[]> paramList);

	// public abstract int addByteToList(GdMessage[] paramArrayOfGdMessage);

	public abstract int addSet(String paramString1, String paramString2);

	public abstract int addSet(String paramString, byte[] paramArrayOfByte);

	public abstract int addSet(String paramString, String[] paramArrayOfString);

	public abstract boolean sisMember(String paramString1, String paramString2);

	public abstract boolean sisMember(String paramString,
			byte[] paramArrayOfByte);

	public abstract boolean delSet(String paramString1, String paramString2);

	public abstract int exprie(String paramString, int paramInt);

	public abstract int addHash(String paramString1, String paramString2,
			String paramString3);

	public abstract int addHash(String paramString, Map<String, String> paramMap);

	public abstract String getHash(String paramString1, String paramString2);

	public abstract boolean delHash(String paramString1, String paramString2);

	public abstract long getListLen(String paramString);

	public abstract long getSetLen(String paramString);

	public abstract long getHashLen(String paramString);

	public abstract boolean existKey(byte[] paramArrayOfByte);

	public abstract boolean delKey(String paramString);

	public abstract Set<String> getAllHashKeys(String paramString);

	public abstract Set<String> getAllKeys();

}
