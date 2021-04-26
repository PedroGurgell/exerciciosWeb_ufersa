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



public class Cliente {
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	@Entity
	@Table(name="pessoas")
	public class Pessoa implements Serializable{

		private static final long serialVersionUID = -7498256744927465623L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		
		private String nome;
		private String email;
		private String genero;
		
		@Column(nullable=false)
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private LocalDate dataNascimento;
		
		
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
		public LocalDate getDataNascimento() {
			return dataNascimento;
		}
		public void setDataNascimento(LocalDate dataNascimento) {
			this.dataNascimento = dataNascimento;
		}
		public String getGenero() {
			return genero;
		}
		public void setGenero(String genero) {
			this.genero = genero;
		}
		
	}


}
