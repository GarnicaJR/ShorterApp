package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.ShortnerEntity;
import com.example.demo.service.ShortnerService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	private ShortnerService shortnerService;

	@GetMapping(value = {"","home"})
	public String index(Model model) {
		
		model.addAttribute("shortner", new ShortnerEntity());
		return "index";
	}

	@GetMapping("about")
	public String about() {
		return "about";
	}
	
	@GetMapping("nofound")
	public String noFound() {
		return "nofound";
	}
	
	@PostMapping("generate")
	public String generate(@ModelAttribute ShortnerEntity shortener, Model model,@RequestHeader String host) {
		
		
		String sanitizedURL  = sanitizeUrl(shortener.getUrl());
		String encodedURL = shortnerService.generateEncoded(sanitizedURL);
		
		String fullEncodedURL =  host +"/"+  encodedURL;
		model.addAttribute("encoded", fullEncodedURL);
		return "generate";
	}

	@GetMapping(value = "/{encodedkey}")
	public void redirect(HttpServletResponse httpServletResponse, @PathVariable("encodedkey") String encodedkey,@RequestHeader String host) {

		Optional<ShortnerEntity> urlFromEncoded = shortnerService.getURLFromEncoded(encodedkey);
		
		if(urlFromEncoded.isPresent()) {
			//httpServletResponse.setHeader("Location", "https://www.google.com");
			httpServletResponse.setHeader("Location", "http://"+urlFromEncoded.get().getUrl());
			httpServletResponse.setStatus(302);
		}
		else {			
			httpServletResponse.setHeader("Location",  "http://"+host + "/nofound");
			httpServletResponse.setStatus(302);
		}

	}
	
	private String sanitizeUrl(String url)
	{
	    return url.replace("[^-A-Za-z0-9+&@#/%?=~_|!:,.;\\(\\)]", "");
	}

}
