package com.zacharychao.measurement.principle;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void increment() throws InterruptedException {
		try {
			lock.lock();
			while(number != 0) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "*****before " + number);
			number ++;
			System.out.println(Thread.currentThread().getName() + "*****" + number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
	
	public void decrement() throws InterruptedException {
		try {
			lock.lock();
			while(number == 0) {
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "-----before " + number);
			number --;
			System.out.println(Thread.currentThread().getName() + "-----" + number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}
public class ProducerTraditionDemo {
	public static void main(String[] args) {
		ShareData data = new ShareData();
		new Thread(() ->  {
			for(int i = 0 ; i < 5 ; i++) {
				try {
					data.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"AAA").start();
		new Thread(() ->  {
			for(int i = 0 ; i < 5 ; i++) {
				try {
					data.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"BBB").start();
		
		new Thread(() ->  {
			for(int i = 0 ; i < 5 ; i++) {
				try {
					data.increment();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"CCC").start();
		new Thread(() ->  {
			for(int i = 0 ; i < 5 ; i++) {
				try {
					data.decrement();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"DDD").start();
	}
}
