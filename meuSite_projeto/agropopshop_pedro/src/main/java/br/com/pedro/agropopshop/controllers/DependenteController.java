package br.com.pedro.agropopshop.controllers;

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
public class DependenteController {
	
	@Autowired
	DependenteRepository dependenteRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
			
			/*
			 Editar/apagar dependentes por cliente >>
			*/
	
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
		
			//Listar dependentes do cliente
			@GetMapping("/adm/listarDependentes/{idCliente}")
			public ModelAndView listarCliente(@PathVariable("idCliente") long idCliente) {
				Cliente listaClienteEl = clienteRepo.findById(idCliente)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + idCliente));
				List<Dependente> lista = listaClienteEl.getOsDependentes();
				ModelAndView mav = new ModelAndView("adm/listarDependentes");
				mav.addObject("dependentes",lista);
				return mav;
				
			}
			
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
	
			
			/*
			 Editar/apagar dependentes totais >>
			*/
			
			//Listar todos os dependentes 
			@GetMapping("/adm/listarDependentesTotal")
			public ModelAndView listarClientes() {
				List<Dependente> lista = dependenteRepo.findAll();
				ModelAndView mav = new ModelAndView("adm/listarDependentesTotal");
				mav.addObject("dependentes",lista);
				return mav;
			}
			//Deletar dependente da lista geral
			@GetMapping("/adm/removerDependenteTotal/{id}")
			public ModelAndView delDependentes(@PathVariable("id") long id) {
				Dependente delDependente = dependenteRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				dependenteRepo.delete(delDependente);
				return new ModelAndView("redirect:/adm/listarDependentesTotal");
			}
			
			//Editar dependente da lista geral
			@GetMapping("/adm/editarDependenteTotal/{id}")
			public ModelAndView formEditDependentes(@PathVariable("id") long id ) {
				Dependente editEldep = dependenteRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				ModelAndView model = new ModelAndView("adm/editarDependenteTotal");
				model.addObject(editEldep);
				return model;
			}
			
			//Salvar dependente editado no Bando de dados
			@PostMapping("/adm/editarDependenteTotal/{id}")
			public ModelAndView editDependentes(@PathVariable("id") long id , Dependente dependente) {	
				Dependente editEldep = dependenteRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				Cliente cliente = clienteRepo.findById(editEldep.getCliente().getId())
						.orElseThrow(() -> new IllegalArgumentException("ID inválido"));
				dependente.setCliente(cliente);
				dependente.setId(editEldep.getId());
				this.dependenteRepo.save(dependente);
				return new ModelAndView("redirect:/adm/listarDependentesTotal");
			}


}
