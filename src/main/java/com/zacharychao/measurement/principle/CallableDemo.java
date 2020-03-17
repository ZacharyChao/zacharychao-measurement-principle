package com.zacharychao.measurement.principle;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		FutureTask task = new FutureTask(myThread);
		new Thread(task,"AAAA").start();
		
		
		
		try {
			System.out.println((Integer)task.get() + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("**********");
	}
}

class MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return 1000;
	}
	
}
