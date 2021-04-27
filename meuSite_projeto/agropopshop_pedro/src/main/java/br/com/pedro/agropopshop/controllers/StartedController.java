package br.com.pedro.agropopshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class StartedController {
	
	@GetMapping
	public String index(){
		return "index.html";
	}
	
	@GetMapping("/client")
	public String indexCliente(){
		return "client/index.html";
	}
			
	@GetMapping("/adm")
	public String indexAdm() {
		return "adm/index.html";
	}
			
}
