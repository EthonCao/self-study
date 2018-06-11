package com.test.redis.demo;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisUtilTest {
	JedisPool pool;
	Jedis jedis;

	@Before
	public void setUp() {
		pool = new JedisPool(new JedisPoolConfig(), "localhost");
		jedis = pool.getResource();
		// jedis.auth("password");
	}

	@Test
	public void testGet() {
		System.out.println(" --- get lu --- " + jedis.get("lu"));
	}

	/**
	 * Redis�洢�������ַ��� CRUD
	 */
	//@Test
	public void testAdd() {
		for (int i = 0; i < 1000; i++) {
			jedis.set("name" + i, "caocao" + i);// ��key-->name�з�����value-->minxr
			System.out.println("Added Key-Value is: " + jedis.get("name" + i));// ִ�н����minxr
		}
		System.out.println("Added Done");
	}
	
	//@Test
	public void testMSet() {
		//mset�൱�� jedis.set("name","minxr"); jedis.set("jarorwar","������");
		jedis.mset("firstName", "Jask", "secondName", "Rose");
		System.out.println(jedis.mget("firstName", "secondName"));
	}
	
	//@Test
	public void testDel() {
		// ɾ��key��Ӧ�ļ�¼
		jedis.del("name");
		System.out.println(jedis.get("name"));// ִ�н����null
	}
	
	//@Test
	public void testReSet() {
		//ֱ�Ӹ���ԭ��������
		jedis.set("name", "hello world");
		System.out.println(jedis.get("name"));
		jedis.append("name", "caocaocoacao");
		System.out.println(jedis.get("name"));
	}

	//@Test
	public void testAppend() {
		jedis.set("name", "caocao");// ��key-->name�з�����value-->minxr
		System.out.println("Append Key-Value is: " + jedis.get("name"));// ִ�н����minxr
	} 
	
	/**
	 * jedis����Map
	 */
	//@Test
	public void testMap() {
		Map<String, String> user = new HashMap<String, String>();
		user.put("name", "minxr");
		user.put("pwd", "password");
		jedis.hmset("user", user);
		// ȡ��user�е�name��ִ�н��:[minxr]-->ע������һ�����͵�List
		// ��һ�������Ǵ���redis��map�����key����������Ƿ���map�еĶ����key�������key���Ը�������ǿɱ����
		List<String> rsmap = jedis.hmget("user", "name");
		System.out.println("rsmap ---: " + rsmap);
		// ɾ��map�е�ĳ����ֵ
		// jedis.hdel("user","pwd");
		System.out.println(" User+PWD: " + jedis.hmget("user", "pwd")); // ��Ϊɾ���ˣ����Է��ص���null
		System.out.println("Number of key=user: " + jedis.hlen("user")); // ����keyΪuser�ļ��д�ŵ�ֵ�ĸ���1
		System.out.println("If exist key=user: " + jedis.exists("user"));// �Ƿ����keyΪuser�ļ�¼ ����true
		System.out.println("All Keys:" + jedis.hkeys("user"));// ����map�����е�����key [pwd, name]
		System.out.println("All Values: " + jedis.hvals("user"));// ����map�����е�����value [minxr,
												// password]
		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + jedis.hmget("user", key));
		}
	}

	/**
	 * jedis����List
	 */
	//@Test
	public void testList() {
		// ��ʼǰ�����Ƴ����е�����
		jedis.del("java framework");
		System.out.println(jedis.lrange("java framework", 0, -1));
		// ����key java framework�д����������
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// ��ȡ����������jedis.lrange�ǰ���Χȡ����
		// ��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������
		System.out.println(jedis.lrange("java framework", 0, -1));
	}

	/**
	 * jedis����Set
	 */
	//@Test
	public void testSet() {
		// ���
		jedis.sadd("sname", "minxr");
		jedis.sadd("sname", "jarorwar");
		jedis.sadd("sname", "������");
		jedis.sadd("sanme", "noname");
		// �Ƴ�noname
		jedis.srem("sname", "noname");
		System.out.println(jedis.smembers("sname"));// ��ȡ���м����value
		System.out.println(jedis.sismember("sname", "minxr"));// �ж� minxr
																// �Ƿ���sname���ϵ�Ԫ��
		System.out.println(jedis.srandmember("sname"));
		System.out.println(jedis.scard("sname"));// ���ؼ��ϵ�Ԫ�ظ���
	}

	@Test
	public void test() throws InterruptedException {
		// keys�д���Ŀ�����ͨ���
		System.out.println(jedis.keys("*")); // ���ص�ǰ�������е�key [sose, sanme, name,
												// jarorwar, foo, sname, java
												// framework, user, braand]
		Set<String> keysArray = jedis.keys("*");
		System.out.println(keysArray.size());
		//jedis.flushAll(); //ɾ����������
		Set<String> keysArray1 = jedis.keys("*");
		System.out.println(keysArray1.size());
		System.out.println(jedis.keys("*name"));// ���ص�sname [sname, name]
		System.out.println(jedis.del("sanmdde"));// ɾ��keyΪsanmdde�Ķ��� ɾ���ɹ�����1
													// ɾ��ʧ�ܣ����߲����ڣ����� 0
		System.out.println(jedis.ttl("sname"));// ���ظ���key����Чʱ�䣬�����-1���ʾ��Զ��Ч
		jedis.setex("timekey", 10, "min");// ͨ���˷���������ָ��key�Ĵ���Чʱ�䣩 ʱ��Ϊ��
		Thread.sleep(5000);// ˯��5���ʣ��ʱ�佫Ϊ<=5
		System.out.println(jedis.ttl("timekey")); // ������Ϊ5
		jedis.setex("timekey", 1, "min"); // ��Ϊ1�������ٿ�ʣ��ʱ�����1��
		System.out.println(jedis.ttl("timekey")); // ������Ϊ1
		System.out.println(jedis.exists("key"));// ���key�Ƿ����
												// System.out.println(jedis.rename("timekey","time"));
		System.out.println(jedis.get("timekey"));// ��Ϊ�Ƴ�������Ϊnull
		System.out.println(jedis.get("time")); // ��Ϊ��timekey ������Ϊtime ���Կ���ȡ��ֵ
												// min
		// jedis ����
		// ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ��������ģ�
		jedis.del("a");// ��������ݣ��ټ������ݽ��в���
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
		System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //�����������
		System.out.println(jedis.lrange("a", 0, -1));
	}
}