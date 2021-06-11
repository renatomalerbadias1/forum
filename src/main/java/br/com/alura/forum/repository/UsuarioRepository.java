package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.alura.forum.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findByNome(String nome);

}
