package br.com.dotcom.sbmvc.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.dotcom.sbmvc.model.Role;
import br.com.dotcom.sbmvc.model.Usuario;
import br.com.dotcom.sbmvc.repository.RoleRepository;
import br.com.dotcom.sbmvc.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	@Autowired
	private RoleRepository rr;
	
	private Usuario usuario;
	private List<Role> roles;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		System.out.println("Procurando Usuario: "+login);
		usuario = ur.findById(login).get();
		if(usuario == null){
			System.out.println("Usuario não encontrado!");
			throw new UsernameNotFoundException("Usuario não encontrado !!!");
		}
		if (usuario.getRole() == null || usuario.getRole().size()==0) {
			System.out.println("Não foram concedidas permições para este usuário !");
			throw new UsernameNotFoundException("Não foram concedidas permições para este usuário !!!");
		}else{
			System.out.println("Qtd Roles: "+usuario.getRole().size());
			roles = usuario.getRole();
			for (int i=0; i<usuario.getRole().size(); i++) {
				Role role = roles.get(i);
				System.out.println("Role:["+i+"] ->"+role.getNomeRole());
			}
		}
		User user = new User(usuario.getLogin(), usuario.getSenha(), true, true, true, true, usuario.getAuthorities());
		return user;
	}

}
