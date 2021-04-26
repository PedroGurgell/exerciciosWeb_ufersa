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
@RequestMapping("/")
public class ClienteController {

		@Autowired
		ClienteRepository clienteRepo;
		
		@GetMapping
		public String index() {
			return "index.html";
		}
		
		@GetMapping("/listarPessoas")
		public ModelAndView listarPessoas() {
			List<Cliente> lista = clienteRepo.findAll();
			ModelAndView mav = new ModelAndView("listarPessoas");
			mav.addObject("pessoas",lista);
			return mav;
		}
		
		//Trnasformar informções do form em novo objeto Pessoa
		@GetMapping("/adicionarPessoa")
		public ModelAndView formAddPessoa(){
			ModelAndView mav = new ModelAndView("adicionarPessoa");
		 	mav.addObject(new Cliente());
			return mav;
		}
		
		//Adicionar pessoa
		@PostMapping("/adicionarPessoa")
		public String AddCliente(Cliente p) {
			this.clienteRepo.save(p);
			return "redirect:/public/adicionadaComSucesso.html";
		}
		
		//Deletar Pessoa
		@GetMapping("/remover/{id}")
		public ModelAndView deleteCliente(@PathVariable("id") long id) {
			Cliente delCliente = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			clienteRepo.delete(delCliente);
			return new ModelAndView("redirect:/listarPessoas");
		}
		
		//Editar pessoa
		@GetMapping("/editar/{id}")
		public ModelAndView formEditcliente(@PathVariable("id") long id) {
			Cliente editEl = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			ModelAndView model = new ModelAndView("editarPessoa");
			model.addObject(editEl);
			return model;
		}
		
		//Salvar pessoa editada no Bando de dados
		@PostMapping("/editar/{id}")
		public ModelAndView editCliente(@PathVariable("id") long id, Cliente cliente) {
			this.clienteRepo.save(cliente);
			return new ModelAndView("redirect:/listarPessoas");
		}
}

