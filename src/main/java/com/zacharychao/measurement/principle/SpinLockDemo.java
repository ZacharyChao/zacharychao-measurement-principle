package com.zacharychao.measurement.principle;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
	public static void main(String[] args) {
		MyLock lock = new MyLock();
		new Thread(() -> {
			lock.lock();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unLock();
		}, "AAAA").start();

		new Thread(() -> {
			lock.lock();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.unLock();
		}, "BBBB").start();
	}
}

class MyLock {
	private AtomicReference<Thread> lock = new AtomicReference<>();

	public void lock() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " come in lock ********");
		while (!lock.compareAndSet(null, thread)) {
		}
	}

	public void unLock() {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName() + " come out lock ########");
		lock.compareAndSet(thread, null);
	}
}
