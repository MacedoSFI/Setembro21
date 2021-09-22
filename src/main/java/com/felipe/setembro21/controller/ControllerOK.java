package com.felipe.setembro21.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerOK {
	
	@RequestMapping(value="/testaOK/{name}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String ok(@PathVariable String name) {
		return "Tudo " + name + " OK!";
	}

}
