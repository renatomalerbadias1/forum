package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Usuario;

public class UsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5)
	private String email;
	@NotNull @NotEmpty @Length(min = 5)
	private String senha;
	



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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Usuario converter() {
		
		return new Usuario(nome,email,senha);
	}
	
	
	

}
