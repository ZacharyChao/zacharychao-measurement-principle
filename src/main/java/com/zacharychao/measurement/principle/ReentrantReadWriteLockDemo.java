package com.zacharychao.measurement.principle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
class MyCache{
	private volatile Map<String , Object> map = new HashMap<>();
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	public void put(String key,String value) {
		lock.writeLock().lock();
		try {
			Thread thread = Thread.currentThread();
			System.out.println(thread.getName() + " 正在写入***** " + key);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(key, value);
			System.out.println(thread.getName() + " 写入完成***** " + key);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		
	}
	
	public void get(String key) {
		
		try {
			lock.readLock().lock();
			Thread thread = Thread.currentThread();
			System.out.println(thread.getName() + " 正在读取##### " + key);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String value = (String)map.get(key);
			System.out.println(thread.getName() + " 读取完成##### " + key + " = " + value);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.readLock().unlock();
		}
	}
	public Map<String, Object> getMap(){
		return map;
	}
}
public class ReentrantReadWriteLockDemo {
	
	
	public static void main(String[] args) {
		
		MyCache myCache = new MyCache();
		
		for(int i = 0 ; i < 10 ; i ++) {
			final int index = i;
			new Thread(() -> {
				myCache.put(index + "", index + "");
			},"" + i).start();
		}
		
		for(int i = 0 ; i < 10 ; i ++) {
			final int index = i;
			new Thread(() -> {
				myCache.get(index + "");
			},"" + i).start();
		}
	}
}
