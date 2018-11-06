package br.com.dotcom.sbmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dotcom.sbmvc.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
