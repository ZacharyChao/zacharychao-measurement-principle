package com.zacharychao.measurement.principle;
class Person{
	public synchronized void sendMSG() {
		System.out.println(Thread.currentThread().getName() + "-----sendMSG------");
		sendEMail();
		sendSomeThing();
	}
	
	public synchronized void sendEMail() {
		System.out.println(Thread.currentThread().getName() + "*****sendEMail*****");
	}
	
	public synchronized void sendSomeThing() {
		System.out.println(Thread.currentThread().getName() + "&&&&sendSomeThing&&&&&");
	}
}
public class SynchronizedDemo {
	public static void main(String[] args) {
		Person person = new Person();
		for(int i = 0 ; i < 10 ; i ++) {
			final int index = i ;
			new Thread(() -> {
				person.sendMSG();
				
			},"" + index) .start();
		}
	}
}
