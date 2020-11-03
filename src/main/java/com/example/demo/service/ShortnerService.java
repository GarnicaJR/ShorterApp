package com.example.demo.service;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ShortnerEntity;
import com.example.demo.utility.Bijective;


@Service
public class ShortnerService {
	
	@Autowired
	private Bijective bijective;
	
	private final static CopyOnWriteArrayList<ShortnerEntity> database = new CopyOnWriteArrayList<ShortnerEntity>();
	private final static AtomicInteger seed = new AtomicInteger(5000);
	
	
	public String generateEncoded(String url) {
		
		
		ShortnerEntity shortner = new ShortnerEntity();
		
		shortner.setId(seed.getAndIncrement());
		String encoded = bijective.encode(shortner.getId());
		shortner.setEncode(encoded);
		shortner.setUrl(url);
		
		database.add(shortner);
		return encoded;
	}
	
	public Optional<ShortnerEntity> getURLFromEncoded(String encode) {
		
		int decode = bijective.decode(encode);
		Optional<ShortnerEntity>  obj = database.stream().filter( e -> e.getId() == decode).findFirst();
		return obj;
	}

}
