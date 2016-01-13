package org.sample.app.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo")
public class DemoController {
	
	@RequestMapping
	public String demo(){
		return "demo/demo";
	}
	
	@RequestMapping("redirect")
	public String redirect(){
		return "redirect:/demo";
	}

}
