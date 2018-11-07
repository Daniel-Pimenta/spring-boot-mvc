package br.com.dotcom.sbmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dotcom.sbmvc.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
