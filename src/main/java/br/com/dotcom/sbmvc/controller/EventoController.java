package br.com.dotcom.sbmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dotcom.sbmvc.model.Convidado;
import br.com.dotcom.sbmvc.model.Evento;
import br.com.dotcom.sbmvc.repository.ConvidadoRepository;
import br.com.dotcom.sbmvc.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;	
	
	private List<Evento> eventos;
	private Evento evento;
	
	private List<Convidado> convidados;
	private Convidado convidado;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/listarEventos";
	}
	
	// *********** INSERT EVENTO ****************
	@GetMapping("/cadastrarEvento")
	public ModelAndView cadastrarEvento() {
		System.out.println("GET cadastrarEvento");
		ModelAndView mv = new ModelAndView("evento/formEvento");
		evento = new Evento();
		evento.setId(0);
		mv.addObject("evento",evento);
		return mv;
	}
	@PostMapping("/cadastrarEvento")
	public String cadastrarEvento(Evento evento) {
		System.out.println("POST cadastrarEvento");
    er.save(evento);
    return "redirect:/listarEventos";
	}
	
	
  // *********** UPDATE EVENTO ****************
	@GetMapping("/editarEvento/{id}")
	public ModelAndView editarEvento(@PathVariable("id") long id) {
		System.out.println("GET editarEvento");
		evento = er.findById(id).get();
		ModelAndView mv = new ModelAndView("evento/formEvento");
		mv.addObject("evento",evento);
		return mv;
	}
	
	@PostMapping("/editarEvento")
	public String editarEvento(Evento evento) {
		System.out.println("POST editarEvento :"+evento.getId());
    er.save(evento);
    return "redirect:/listarEventos";
	}

  // *********** DELETE EVENTO ****************
	@GetMapping("/removerEvento/{id}")
	public String removerEvento(@PathVariable("id") long id) {
		System.out.println("GET removerEvento");
    er.deleteById(id);
    return "redirect:/listarEventos";
	}
	@GetMapping("/listarEventos")
	public ModelAndView listaEventos() {
		System.out.println("GET listaEvento");
		eventos = er.findAll();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("eventos",eventos);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		System.out.println("GET detalhesEvento");
		evento = er.findById(id).get();
		convidados = cr.findByEvento(evento);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento",evento);
		mv.addObject("convidados",convidados);
		return mv;
	}
	
	@PostMapping("/{id}")
	public String cadastrarConvidado(@PathVariable("id") long id, @Valid Convidado convidado, BindingResult br, RedirectAttributes ra) {
		System.out.println("Gravando Convidado para o evento:"+id);
		if (br.hasErrors()) {
			ra.addFlashAttribute("mensagem","Verifique os campos !");
			return "redirect:/{id}";
		}
		evento = er.findById(id).get();
		System.out.println("Gravando Convidado para o evento:"+evento.getNome());
		convidado.setEvento(evento);
    cr.save(convidado);
    ra.addFlashAttribute("mensagem","Convidado adicionado com sucesso !");
		return "redirect:/{id}";
	}

	@PostMapping("/removeConvidado/{id}")
	public String removeConvidado(@PathVariable("id") long id, Convidado convidado) {
		System.out.println("Preparando para remover Convidado :"+convidado.getId());
		evento = er.findById(id).get();
		System.out.println("Convidado do evento :"+evento.getId());
    cr.deleteById(convidado.getId());
		return "redirect:/{id}";
	}
	
}
