package com.zacharychao.measurement.principle;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(6);
		for(int i = 1 ; i <= 6 ; i ++) {
			new Thread(() -> {
				cdl.countDown();
				System.out.println(Thread.currentThread().getName() );
				
			},CountryEnum.getMSG("" + i).getRetmsg()) .start();
		}
		
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("main");
		
	}
}

 
