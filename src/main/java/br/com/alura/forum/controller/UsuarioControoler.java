package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.UsuarioDto;
import br.com.alura.forum.controller.form.AtualizacaoUsuarioForm;
import br.com.alura.forum.controller.form.UsuarioForm;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioControoler {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	/**
	 * 
	 * Lista usuarios
	 *
	 */
	
	@GetMapping
	public List<UsuarioDto> lista(String nome) {
		
		if (nome == null) {
			List<Usuario> usuario = usuarioRepository.findAll();
			return UsuarioDto.converter(usuario);
		} else {
			List<Usuario> usuario = usuarioRepository.findByNome(nome);
			return UsuarioDto.converter(usuario);
		}
	}
	
	/**
	 * 
	 * Cadastro de usuario
	 */
	
	@PostMapping
	public ResponseEntity<UsuarioDto> cadstrar(@RequestBody @Valid UsuarioForm form , UriComponentsBuilder uriBuilder) {
		
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		
		
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	/**
	 * 
	 * detalhe do usuario
	 */

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {		
		Optional<Usuario > usuario = usuarioRepository.findById(id);		
		if (usuario == null) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));		
		}	
		return ResponseEntity.notFound().build();
	}
	
	/*
	 * Atualizando usuario
	 */
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id,@Valid @RequestBody  AtualizacaoUsuarioForm form ){
		
		Usuario usuario = form.atualizar(id,usuarioRepository);
		return ResponseEntity.ok(new UsuarioDto(usuario));
		
		
	}
	
	/*
	 * Remover usuario
	 */
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?>  remover(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	
}
