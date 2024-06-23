/**
 * 
 */
package com.epson.enovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 */
@Controller
//@RestController
public class HelloController {

	/**
	 * 
	 */
	public HelloController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data" ,"aa");
		model.addAttribute("host" ,"http://localhost:8080");
		return "hello";
	}
	
}
