package br.com.pedro.agropopshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.pedro.agropopshop.model.Produto;
import br.com.pedro.agropopshop.repositories.ProdutoRepository;

@Controller
@RequestMapping
public class ProdutoController {

		@Autowired
		ProdutoRepository produtoRepo;
		
	
		@GetMapping("/adm/listarProdutos")
		public ModelAndView listarProdutos() {
			List<Produto> listaProdutos = produtoRepo.findAll();
			ModelAndView mav = new ModelAndView("adm/listarProdutos");
			mav.addObject("produtos",listaProdutos);
			return mav;
		}
		/*
		@PostMapping("/procurarProduto")
		public ModelAndView procurarP(@RequestParam("nomeProduto") String nomeProduto){
			ModelAndView modelAndView = new ModelAndView("adm/listarProdutos");
			modelAndView.addObject("produtos", produtoRepo.findProdutoByName(nomeProduto));
			return modelAndView;
		}
		*/
		
		@PostMapping("/adm/procurarProduto")
		public ModelAndView ProcurarProduto(@RequestParam("nomeProduto") String nomeProduto){
			ModelAndView modelAndView = new ModelAndView("adm/listarProdutos");
			modelAndView.addObject("produtos", produtoRepo.findByNomeContains(nomeProduto));
			return modelAndView;
		}
		
		//Trnasformar informções do form em novo objeto Pessoa
		@GetMapping("/adm/adicionarProduto")
		public ModelAndView formAddProduto(){
			ModelAndView mav = new ModelAndView("adm/adicionarProduto");
		 	mav.addObject(new Produto());
			return mav;
		}
		
		//Adicionar Produto
		@PostMapping("/adm/adicionarProduto")
		public String AddProduto(Produto p) {
			this.produtoRepo.save(p);
			return "redirect:/adm/listarProdutos";
		}
		
		//Deletar Produto
		@GetMapping("/adm/removerProduto/{id}")
		public ModelAndView deleteProduto(@PathVariable("id") long id) {
			Produto delProduto = produtoRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			produtoRepo.delete(delProduto);
			return new ModelAndView("redirect:/adm/listarProdutos");
		}
		
		//Editar produto
		@GetMapping("/adm/editarProduto/{id}")
		public ModelAndView formEditProduto(@PathVariable("id") long id) {
			Produto editElProduto = produtoRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido" + id));
			ModelAndView model = new ModelAndView("adm/editarProduto");
			model.addObject(editElProduto);
			return model;
		}
		
		//Salvar produto editado no Bando de dados
		@PostMapping("/adm/editarProduto/{id}")
		public ModelAndView editProduto(@PathVariable("id") long id, Produto produto) {
			this.produtoRepo.save(produto);
			return new ModelAndView("redirect:/adm/listarProdutos");
		}
}

