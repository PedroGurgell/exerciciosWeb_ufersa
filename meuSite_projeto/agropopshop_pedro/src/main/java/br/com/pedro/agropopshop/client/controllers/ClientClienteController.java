package br.com.pedro.agropopshop.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pedro.agropopshop.model.Cliente;
import br.com.pedro.agropopshop.repositories.ClienteRepository;

@Controller
@RequestMapping
public class ClientClienteController {

		@Autowired
		ClienteRepository clienteRepo;
		
		/* 
		 AddCliente >>
		 */
		//Trnasformar informções do form em novo objeto Cliente
		@GetMapping("/client/listarClientes")
		public ModelAndView listarClientes() {
			List<Cliente> lista = clienteRepo.findAll();
			ModelAndView mav = new ModelAndView("client/listarClientes");
			mav.addObject("clientes",lista);
			return mav;
		}
		
		//Trnasformar informções do form em novo objeto Cliente(Pelo Cliente)
		@GetMapping("/client/adicionarCliente")
		public ModelAndView formAdicionarCliente(){
			ModelAndView mav = new ModelAndView("client/adicionarCliente");
			mav.addObject(new Cliente());
			return mav;
				}
				
		//Adicionar Cliente
		@PostMapping("/client/adicionarCliente")
		public String AdicionarCliente(Cliente p) {
			this.clienteRepo.save(p);
			return "redirect:/client";
				}
		
		
}
