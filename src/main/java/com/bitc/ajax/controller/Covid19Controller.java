package com.bitc.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Covid19Controller {

	@RequestMapping(value="/data/covid19sidoList", method=RequestMethod.GET)
	public String covid19ListPage() throws Exception {
		return "/data/covid19sidoList";
	}
	
}






































