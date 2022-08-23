package br.com.fiap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	public Usuario insert(Usuario usuario) {
		Usuario saveUsuario = repository.save(usuario);
		return saveUsuario;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
