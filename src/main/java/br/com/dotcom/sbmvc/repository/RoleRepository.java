package br.com.dotcom.sbmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dotcom.sbmvc.model.Role;
import br.com.dotcom.sbmvc.model.Usuario;

public interface RoleRepository extends JpaRepository<Role, String> { 
	
}
