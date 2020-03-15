package com.zacharychao.measurement.principle;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {
	public static void main(String[] args) {
		Map<String,String> map = new ConcurrentHashMap<>(); 
//		Map<String,Object> map = new HashMap<>();
		for(int i = 0 ; i < 100 ; i ++) {
			final int j = i;
			new Thread(
					() -> {
						map.put(UUID.randomUUID().toString(), j+"");
					},String.valueOf(i)) .start();
			
			
		}
	}
}
