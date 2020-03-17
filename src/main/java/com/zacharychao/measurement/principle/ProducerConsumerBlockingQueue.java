package com.zacharychao.measurement.principle;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ProducerConsumerBlockingQueue {
	public static void main(String[] args) {
		MyResource resource = new MyResource(new ArrayBlockingQueue<String>(10));
		new Thread(() -> {
			resource.produce();
		},"AAAA").start();
		
		new Thread(() -> {
			resource.consume();
		},"BBBB").start();
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		resource.stop();
	}
}
class MyResource{
	private volatile boolean FLAG = true;
	private AtomicInteger number = new AtomicInteger();
	private BlockingQueue<String> queue = null;
	public MyResource(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void produce() {
		String num = null; 
		while(FLAG) {
			num = number.incrementAndGet() + "";
			try {
				queue.offer(num, 2, TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + " produce ******** " + num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("FLAG******** = " + FLAG);
	}
	
	public void consume() {
		String element = null;
		while(FLAG) {
			try {
				element = this.queue.poll(2, TimeUnit.SECONDS);
				
				if(element == null || element.equals("")) {
					System.out.println("$****** out");
					FLAG = false;
					System.out.println("$$$$$$ out");
					return ;
				}
				
				System.out.println(Thread.currentThread().getName() + "######consume###### " + element);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void stop() {
		this.FLAG = false;
	}
}