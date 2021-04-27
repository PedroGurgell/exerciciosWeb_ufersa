package br.com.pedro.agropopshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name="produtos")
	public class Produto implements Serializable{

		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		
		private String nome;
		private String marca;
		private Double altura;
		private Double largura;
		private Double profundidade;
		private Double peso;
		private Double preco;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public Double getAltura() {
			return altura;
		}
		public void setAltura(Double altura) {
			this.altura = altura;
		}
		public Double getLargura() {
			return largura;
		}
		public void setLargura(Double largura) {
			this.largura = largura;
		}
		public Double getProfundidade() {
			return profundidade;
		}
		public void setProfundidade(Double profundidade) {
			this.profundidade = profundidade;
		}
		public Double getPeso() {
			return peso;
		}
		public void setPeso(Double peso) {
			this.peso = peso;
		}
		public Double getPreco() {
			return preco;
		}
		public void setPreco(Double preco) {
			this.preco = preco;
		}
		

		
	}

