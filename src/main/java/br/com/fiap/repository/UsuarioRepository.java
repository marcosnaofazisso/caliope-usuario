package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
