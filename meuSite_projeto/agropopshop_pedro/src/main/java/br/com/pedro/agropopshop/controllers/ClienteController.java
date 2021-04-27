package br.com.pedro.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pedro.agropopshop.model.Cliente;
import br.com.pedro.agropopshop.repositories.ClienteRepository;

@Controller
@RequestMapping
public class ClienteController {

		@Autowired
		ClienteRepository clienteRepo;
		
		/* 
		 Interação com Cliente >>
		 */
		
		//Trnasformar informções do form em novo objeto Cliente(Pelo Cliente)
		@GetMapping("/client/adicionarCliente")
		public ModelAndView formAdicionarCliente(){
			ModelAndView mav = new ModelAndView("/client/adicionarCliente");
			mav.addObject(new Cliente());
			return mav;
				}
				
		//Adicionar Cliente
		@PostMapping("/client/adicionarCliente")
		public String AdicionarCliente(Cliente p) {
			this.clienteRepo.save(p);
			return "redirect:/client";
				}
		
		/* 
		 Interação com ADM >>
		 */
		
		//Trnasformar informções do form em novo objeto Cliente
		@GetMapping("/adm/listarClientes")
		public ModelAndView listarClientes() {
			List<Cliente> lista = clienteRepo.findAll();
			ModelAndView mav = new ModelAndView("/adm/listarClientes");
			mav.addObject("clientes",lista);
			return mav;
		}

		//Trnasformar informções do form em novo objeto Cliente
		@GetMapping("/adm/adicionarCliente")
		public ModelAndView formAddCliente(){
			ModelAndView mav = new ModelAndView("/adm/adicionarCliente");
		 	mav.addObject(new Cliente());
			return mav;
		}
		
		//Adicionar Cliente
		@PostMapping("/adm/adicionarCliente")
		public String AddCliente(Cliente p) {
			this.clienteRepo.save(p);
			return "redirect:/adm/listarClientes";
		}
		
		//Deletar Cliente
		@GetMapping("/adm/remover/{id}")
		public ModelAndView deleteCliente(@PathVariable("id") long id) {
			Cliente delCliente = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			clienteRepo.delete(delCliente);
			return new ModelAndView("redirect:/adm/listarClientes");
		}
		
		//Editar Cliente
		@GetMapping("/adm/editar/{id}")
		public ModelAndView formEditcliente(@PathVariable("id") long id) {
			Cliente editEl = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			ModelAndView model = new ModelAndView("/adm/editarCliente");
			model.addObject(editEl);
			return model;
		}
		
		//Salvar cliente editado no Bando de dados
		@PostMapping("/adm/editar/{id}")
		public ModelAndView editCliente(@PathVariable("id") long id, Cliente cliente) {
			this.clienteRepo.save(cliente);
			return new ModelAndView("redirect:/adm/listarClientes");
		}
}

