package br.com.pedro.agropopshop.client.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pedro.agropopshop.model.Cliente;
import br.com.pedro.agropopshop.model.Dependente;
import br.com.pedro.agropopshop.repositories.ClienteRepository;
import br.com.pedro.agropopshop.repositories.DependenteRepository;

@Controller
@RequestMapping
public class ClientDependenteController {
		
		@Autowired
		DependenteRepository dependenteRepo;
		
		@Autowired
		ClienteRepository clienteRepo;
		
	
		/*
		//Transformar informações do formulário em novo objeto
		@GetMapping("/adm/adicionarDependente/{idCliente}")
		public ModelAndView formAddDependente(@PathVariable("idCliente") long idCliente) {
			Cliente clienteEl = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idCliente));
			ModelAndView mav = new ModelAndView("adm/adicionarDependente");
		 	mav.addObject(clienteEl);
		 	mav.addObject(new Dependente());
			return mav;
		}
		
		
		//Adicionar Novo Dependente
		@PostMapping("/adm/adicionarDependente/{idCliente}")
		public ModelAndView addDependente(@PathVariable("idCliente") long idCliente, Dependente dependente){
			Cliente clienteEl = clienteRepo.findById(idCliente)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idCliente));
			List<Dependente> dependentes = new ArrayList<Dependente>();
			dependentes = clienteEl.getOsDependentes();
			dependentes.add(dependente);
			clienteEl.setOsDependentes(dependentes);
			dependente.setCliente(clienteEl);
			clienteRepo.save(clienteEl);
			return new ModelAndView("redirect:/adm/listarDependentes/"+idCliente);
		}
		*/
	
	
		//Listar dependentes do cliente
		@GetMapping("/client/infoCliente/{idCliente}")
		public ModelAndView listarCliente(@PathVariable("idCliente") long idCliente) {
			Cliente listaClienteEl = clienteRepo.findById(idCliente)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idCliente));
			List<Dependente> lista = listaClienteEl.getOsDependentes();
			ModelAndView mav = new ModelAndView("client/infoCliente");
			mav.addObject("dependentes",lista);
			mav.addObject(listaClienteEl);
			return mav;
			
		}
		
		/*
		//Deletar dependente
		@GetMapping("/adm/removerDependente/{idDep}")
		public ModelAndView deleteDependente(@PathVariable("idDep") long idDep) {
			Dependente delDependente = dependenteRepo.findById(idDep)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idDep));
			long idOrigem = delDependente.getCliente().getId();
			dependenteRepo.delete(delDependente);
			return new ModelAndView("redirect:/adm/listarDependentes/"+idOrigem);
		}
		
		//Editar dependente
		@GetMapping("/adm/editarDependente/{idDep}")
		public ModelAndView formEditDependente(@PathVariable("idDep") long idDep ) {
			Dependente editEldep = dependenteRepo.findById(idDep)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idDep));
			ModelAndView model = new ModelAndView("adm/editarDependente");
			model.addObject(editEldep);
			return model;
		}
		
		//Salvar dependente editado no Bando de dados
		@PostMapping("/adm/editarDependente/{idDep}")
		public ModelAndView editDependente(@PathVariable("idDep") long idDep , Dependente dependente) {	
			Dependente editEldep = dependenteRepo.findById(idDep)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idDep));
			Cliente cliente = clienteRepo.findById(editEldep.getCliente().getId())
					.orElseThrow(() -> new IllegalArgumentException("ID inválido"));
			dependente.setCliente(cliente);
			dependente.setId(editEldep.getId());
			this.dependenteRepo.save(dependente);
			return new ModelAndView("redirect:/adm/listarDependentes/" +cliente.getId());
		}
		*/
	
}
