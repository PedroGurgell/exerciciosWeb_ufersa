package br.com.pedro.agropopshop.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name="clientes")
	public class Cliente implements Serializable{

		private static final long serialVersionUID = -7498256744927465623L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		
		private String nome;
		private String email;
		private String endereco;
		private String cep;
		private String genero;
		
	    @Column(nullable = false)
	    @DateTimeFormat(pattern = "dd/MM/yyyy")
	    private LocalDate dataAtual = LocalDate.now();
		
		
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getGenero() {
			return genero;
		}
		public String getEndereco() {
			return endereco;
		}
		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}
		public String getCep() {
			return cep;
		}
		public void setCep(String cep) {
			this.cep = cep;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		public LocalDate getDataAtual() {
			return dataAtual;
		}
		public void setDataAtual(LocalDate dataAtual) {
			this.dataAtual = dataAtual;
		}

		
		
	}

