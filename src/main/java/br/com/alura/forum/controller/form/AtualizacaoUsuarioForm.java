package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

public class AtualizacaoUsuarioForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String email;
	@NotNull @NotEmpty @Length(min = 5)
	private String senha;
	
	
	
	
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
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		
		return usuario;
	}
	
	

}
