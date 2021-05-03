package br.com.pedro.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.pedro.agropopshop.model.Dependente;
import br.com.pedro.agropopshop.repositories.DependenteRepository;

@Controller
@RequestMapping
public class DependenteController {
	
	@Autowired
	DependenteRepository dependenteRepo;
	
	
	//Transformar informações do form em novo objeto Cliente
			@GetMapping("/adm/adicionarDependente")
			public ModelAndView formAddDependente(){
				ModelAndView mav = new ModelAndView("adm/adicionarCliente");
			 	mav.addObject(new Dependente());
				return mav;
			}
			
			//Adicionar dependente
			@PostMapping("/adm/adicionarDependente")
			public String AddDependente(Dependente p) {
				this.dependenteRepo.save(p);
				return "redirect:/adm/listarDependentes";
			}
			
			//Listar dependentes
			@GetMapping("/adm/listarDependentes")
			public ModelAndView listarDependentes() {
				List<Dependente> lista = dependenteRepo.findAll();
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
