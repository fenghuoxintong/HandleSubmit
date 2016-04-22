package com.bjym.mobiledata.redis.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.bjym.mobiledata.redis.interfaces.RedisInterface;
import com.bjym.mobiledata.utils.LoggerUtil;

public class RedisImpl implements RedisInterface {
	private JedisPools pool = JedisPools.getInstance();

	@Override
	public boolean isShutdown() {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				String ping = jedis.ping();
				if (ping.equals("PONG")) {
					return true;
				}
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法检测redis状态");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean Shutdown() {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				jedis.shutdown();
				return true;
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法shutdown");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public int setKey(String key, byte[] value) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				String result = jedis.set(key.getBytes("GBK"), value);
				if (result.equals("OK")) {
					return 1;
				}
			}
			LoggerUtil.info("通用连接池内未取到连接,无法设置key的值");
			return 0;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return 0;
	}

	@Override
	public byte[] getByteFromList(String listname) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				byte[] b = jedis.rpop(listname.getBytes("GBK"));
				if (b != null) {
					byte[] arrayOfByte1 = b;
					return arrayOfByte1;
				}
				return null;
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法取得队列数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return null;
	}

	@Override
	public List<byte[]> getByteFromList(String listname, int count) {
		Jedis jedis = this.pool.getJedis();
		try {
			List<byte[]> list = new ArrayList();
			if (jedis != null) {
				for (int i = 0; i < count; i++) {
					byte[] b = jedis.rpop(listname.getBytes("GBK"));
					if (b != null) {
						list.add(b);
					}
				}
				if ((list != null) && (list.size() != 0)) {
					List<byte[]> localList1 = list;
					return localList1;
				}
				return null;
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法取得队列数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return null;
	}

	@Override
	public List<byte[]> getByteFromLists(String[] listname, int count) {
		Jedis jedis = this.pool.getJedis();
		try {
			List<byte[]> list = new ArrayList();
			byte[][] listnames = new byte[listname.length][];
			for (int x = 0; x < listname.length; x++) {
				byte[] b = listname[x].getBytes("GBK");
				listnames[x] = b;
			}
			if (jedis != null) {
				for (int i = 0; i < count; i++) {
					List<byte[]> b = jedis.brpop(1, listnames);
					if (b != null) {
						list.add((byte[]) b.get(1));
					}
				}
				if ((list != null) && (list.size() != 0)) {
					List<byte[]> localList1 = list;
					return localList1;
				}
				return null;
			}
			LoggerUtil.info("读取连接池内未取到连接,无法取得队列数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return null;
	}

	@Override
	public int addByteToList(String listname, byte[] cmpp) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				jedis.lpush(listname.getBytes("GBK"), new byte[][] { cmpp });
				return 1;
			}
			LoggerUtil.info("写入redis连接池内未取到连接,无法加入数据队列");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return 0;
	}

	@Override
	public int addByteToList(String listname, List<byte[]> cmpp) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Pipeline p = jedis.pipelined();
				for (int i = 0; i < cmpp.size(); i++) {
					p.lpush(listname.getBytes("GBK"), (byte[]) cmpp.get(i));
				}
				p.sync();

				return 1;
			}
			LoggerUtil.info("写入redis连接池内未取到连接,无法加入队列数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return 0;
	}

	@Override
	public int addSet(String listname, String member) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				long result = jedis.sadd(listname, new String[] { member })
						.longValue();
				return (int) result;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法加入set数据");
			return 0;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public int addSet(String listname, byte[] member) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				long result = jedis.sadd(listname.getBytes("GBK"),
						new byte[][] { member }).longValue();
				int i = (int) result;
				return i;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法加入set数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return 0;
	}

	@Override
	public int addSet(String listname, String[] menbers) {
		Jedis jedis = this.pool.getJedis();
		try {
			Response<Long> result = null;
			if (jedis != null) {
				Pipeline p = jedis.pipelined();
				for (int i = 0; i < menbers.length; i++) {
					result = p.sadd(listname, menbers[i]);
				}
				p.sync();

				return ((Long) result.get()).intValue();
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法加入set数据");
			return 0;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean sisMember(String listname, String member) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Boolean result = jedis.sismember(listname, member);
				return result.booleanValue();
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法检测set数据");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean sisMember(String listname, byte[] member) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Boolean result = jedis.sismember(listname.getBytes("GBK"),
						member);
				boolean bool = result.booleanValue();
				return bool;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法检测set数据");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			this.pool.close(jedis);
		}
		return false;
	}

	@Override
	public boolean delSet(String listname, String menber) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Long result = jedis.srem(listname, new String[] { menber });
				if (result.longValue() > 0L) {
					return true;
				}
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法检测set数据");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public int exprie(String key, int second) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Long result = jedis.expire(key, second);
				return result.intValue();
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法设置有效期");
			return 0;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public int addHash(String listname, String field, String values) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Long result = jedis.hset(listname, field, values);
				return result.intValue();
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法加入hash");
			return 0;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public int addHash(String listname, Map<String, String> map) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				String result = jedis.hmset(listname, map);
				if (result.equals("OK")) {
					return 1;
				}
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法加入hash");
			return 0;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public String getHash(String listname, String field) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				String result = jedis.hget(listname, field);
				return result;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法获得field数据");
			return null;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean delHash(String listname, String field) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Long result = jedis.hdel(listname, new String[] { field });
				if (result.longValue() > 0L) {
					return true;
				}
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法检测set数据");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public long getListLen(String listname) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				long result = jedis.llen(listname).longValue();
				return result;
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法获得list长度");
			return 0L;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public long getSetLen(String setname) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				long result = jedis.scard(setname).longValue();
				return result;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法检获得set长度");
			return 0L;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public long getHashLen(String hashname) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				long result = jedis.hlen(hashname).longValue();
				return result;
			}
			LoggerUtil.info("逻辑redis连接池内未取到连接,无法获得hash长度");
			return 0L;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean existKey(byte[] key) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				boolean result = jedis.exists(key).booleanValue();
				return result;
			}
			LoggerUtil.info("连接池内未取到连接,无法检测key是否存在");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public boolean delKey(String key) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Long result = jedis.del(new String[] { key });
				if (result.longValue() > 0L) {
					return true;
				}
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法删除set数据");
			return false;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public Set<String> getAllHashKeys(String hashname) {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Set<String> result = jedis.hkeys(hashname);
				return result;
			}
			LoggerUtil.info("读写连接池内未取到连接,无法检测set数据");
			return null;
		} finally {
			this.pool.close(jedis);
		}
	}

	@Override
	public Set<String> getAllKeys() {
		Jedis jedis = this.pool.getJedis();
		try {
			if (jedis != null) {
				Set<String> result = jedis.keys("*");
				return result;
			}
			LoggerUtil.info("读取redis连接池内未取到连接,无法获得数据");
			return null;
		} finally {
			this.pool.close(jedis);
		}
	}

	private static String[] HexChar = { "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return String.valueOf(HexChar[d1]) + String.valueOf(HexChar[d2]);
	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result = String.valueOf(result)
					+ String.valueOf(byteToHexString(b[i]));
		}
		return result;
	}

}
