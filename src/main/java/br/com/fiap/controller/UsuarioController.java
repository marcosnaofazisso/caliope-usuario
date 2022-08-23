package br.com.fiap.controller;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.model.Usuario;
import br.com.fiap.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@PostMapping()
	public ResponseEntity<Usuario> insert(@RequestBody  Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(usuario));
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		return ResponseEntity.of(service.findById(id));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		Optional<Usuario> findById = service.findById(id);
		if (findById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@RequestBody  Usuario newUsuario,@PathVariable Long id) {
		Optional<Usuario> findById = service.findById(id);
		if (findById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Usuario usuario = findById.get();
		BeanUtils.copyProperties(newUsuario, usuario);
		usuario.setId(id);
		service.insert(usuario);
		return ResponseEntity.ok(usuario);

	}

}
