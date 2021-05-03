package br.com.pedro.cadpessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import br.com.pedro.cadpessoas.model.Pessoa;
import br.com.pedro.cadpessoas.repositories.PessoaRepository;


@Controller
@RequestMapping("/")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepo;
	
	@GetMapping
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/listarPessoas")
	public ModelAndView listarPessoas() {
		List<Pessoa> lista = pessoaRepo.findAll();
		ModelAndView mav = new ModelAndView("listarPessoas");
		mav.addObject("pessoas",lista);
		return mav;
	}
	
	//Trnasformar informções do form em novo objeto Pessoa
	@GetMapping("/adicionarPessoa")
	public ModelAndView formAddPessoa(){
		ModelAndView mav = new ModelAndView("adicionarPessoa");
	 	mav.addObject(new Pessoa());
		return mav;
	}
	
	//Adicionar pessoa
	@PostMapping("/adicionarPessoa")
	public String AddPessoa(Pessoa p) {
		this.pessoaRepo.save(p);
		return "redirect:/public/adicionadaComSucesso.html";
	}
	
	//Deletar Pessoa
	@GetMapping("/remover/{id}")
	public ModelAndView deletePessoa(@PathVariable("id") long id) {
		Pessoa delPessoa = pessoaRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
		pessoaRepo.delete(delPessoa);
		return new ModelAndView("redirect:/listarPessoas");
	}
	
	//Editar pessoa
	@GetMapping("/editar/{id}")
	public ModelAndView formEditPessoa(@PathVariable("id") long id) {
		Pessoa editEl = pessoaRepo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
		ModelAndView model = new ModelAndView("editarPessoa");
		model.addObject(editEl);
		return model;
	}
	
	//Salvar pessoa editada no Bando de dados
	@PostMapping("/editar/{id}")
	public ModelAndView editPessoa(@PathVariable("id") long id, Pessoa pessoa) {
		this.pessoaRepo.save(pessoa);
		return new ModelAndView("redirect:/listarPessoas");
	}
}
