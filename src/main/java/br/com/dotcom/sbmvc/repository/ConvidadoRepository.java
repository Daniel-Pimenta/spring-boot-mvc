package br.com.dotcom.sbmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dotcom.sbmvc.model.Convidado;
import br.com.dotcom.sbmvc.model.Evento;

public interface ConvidadoRepository  extends JpaRepository<Convidado, Long> {
	
	
	List<Convidado> findByEvento(Evento evento);

}
