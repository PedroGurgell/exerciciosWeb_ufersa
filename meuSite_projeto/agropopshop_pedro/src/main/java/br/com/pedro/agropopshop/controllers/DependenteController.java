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
			
		
			@GetMapping("/adm/adicionarDependente/{id}")
			public ModelAndView formAddDependente(@PathVariable("id") long id) {
				ModelAndView mav = new ModelAndView("adm/adicionarDependente");
			 	mav.addObject(new Dependente());
				return mav;
			}
		
			@PostMapping("/adm/adicionarDependente/{id}")
			public ModelAndView addDependente(@PathVariable("id") long id, Dependente dependente){
				List<Dependente> dependentes = new ArrayList<Dependente>();
				dependentes.add(dependente);
				Cliente clienteEl = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				clienteEl.setOsDependentes(dependentes);
				return new ModelAndView("redirect:/adm/listarDependentes/"+id);
			}
	
			//Listar dependentes
			@GetMapping("/adm/listarDependentes/{id}")
			public ModelAndView listarCliente(@PathVariable("id") long id) {
				Cliente listaClienteEl = clienteRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				List<Dependente> lista = listaClienteEl.getOsDependentes();
				ModelAndView mav = new ModelAndView("adm/listarDependentes");
				mav.addObject("dependentes",lista);
				return mav;
			}
	
			//Deletar dependente
			@GetMapping("/adm/removerDependente/{id}")
			public ModelAndView deleteDependente(@PathVariable("id") long id) {
				Dependente delDependente = dependenteRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				dependenteRepo.delete(delDependente);
				return new ModelAndView("redirect:/adm/listarDependentes");
			}
			
			//Editar dependente
			@GetMapping("/adm/editarDependente/{id}")
			public ModelAndView formEditDependente(@PathVariable("id") long id) {
				Dependente editEl = dependenteRepo.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
				ModelAndView model = new ModelAndView("adm/editarDependente");
				model.addObject(editEl);
				return model;
			}
			
			//Salvar dependente editado no Bando de dados
			@PostMapping("/adm/editarDependente/{id}")
			public ModelAndView editDependente(@PathVariable("id") long id, Dependente dependente) {
				this.dependenteRepo.save(dependente);
				return new ModelAndView("redirect:/adm/listarDependentes");
			}
			
}
