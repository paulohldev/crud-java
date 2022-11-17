package br.com.ifpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.ifpe.model.Usuario;
import br.com.ifpe.model.UsuarioDAO;

@Controller
public class InscricaoController {
	
	@Autowired
	private UsuarioDAO dao;
	public Usuario usuarioBD;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/inscricao")
	public String inscricao(Usuario usuario) {
		return "inscricao";
	}
	
	@PostMapping("/salvarUsuario")
	public String salvarUsuario(Usuario usuario, Model model) {
		usuario.setPago("Não");
		dao.save(usuario);
		model.addAttribute("nome", "Muito bem, " + "[ " + usuario.getNome().toUpperCase() + " ]!" );
		return "sucesso";
	}
	
	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("listaUsuario", dao.findAll());
		return "lista";
	}
	
	@GetMapping("/pagamento")
	public String pagamento(Usuario usuario) {
		return "pagamento";
	}
	
	@PostMapping("/confirmarPagamento")
	public String confirmarPagamento(Usuario usuario, Model model) {
		Usuario usuarioObj = dao.findById(usuario.getId()).orElse(null);
		model.addAttribute("id", usuarioObj);
		usuarioObj.setPago("Sim");
		dao.save(usuarioObj);
		return "redirect:/lista";
	}

}
