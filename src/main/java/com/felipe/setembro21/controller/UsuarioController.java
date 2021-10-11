package com.felipe.setembro21.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.setembro21.model.Usuario;
import com.felipe.setembro21.repository.UsuarioRepository;

@RestController
@Cacheable
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/nome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String user(@PathVariable String name) {

		Usuario usuario = new Usuario();
		usuario.setNome(name);
		usuarioRepository.save(usuario);
		return "usuário: " + usuario.getNome();
	}

	@GetMapping(value = "listarusuarios")
	@ResponseBody
	public ResponseEntity<Iterable<Usuario>> listaUsuario() {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<Iterable<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping(value = "buscaruserid")
	@ResponseBody // no postman form-data key iduser e value 1 para usuario de id=1
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser) {
		Usuario usuario = usuarioRepository.findById(iduser).get();
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {
		List<Usuario> usuarios = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "findUserByLogin")
	@ResponseBody
	public ResponseEntity<Usuario> findUserByLogin(@RequestParam(name = "email") String email) {
		Usuario usuario = usuarioRepository.findUserByLogin(email.trim().toLowerCase());
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}	

	@PostMapping(value = "salvar") // pega o JSON do corpo {"nome": "Post no Postman", "email":
									// "email@postman.com", "dtNascimento": null}
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setPassword(senhacriptografada);
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@PutMapping(value = "atualizar") // pega o JSON do corpo {"nome": "Post no Postman", "email":
										// "email@postman.com", "dtNascimento": null}
	@ResponseBody // se não colocar o campo id no json vai criar outro usuario
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		Usuario userDB = usuarioRepository.findUserByLogin(usuario.getEmail());
		if (!userDB.getPassword().equals(usuario.getPassword())) {
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getPassword());
			usuario.setPassword(senhacriptografada);
		}
		Usuario user = usuarioRepository.save(usuario);//saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@DeleteMapping("deletar")
	@ResponseBody
	public ResponseEntity<String> remover(@RequestParam Long id) {
		Optional<Usuario> user = usuarioRepository.findById(id);//getById(id);
		String nomeUser = user.get().getNome();//getNome();
		System.out.println("nomeUser: " + nomeUser);
		usuarioRepository.deleteById(id);
		return new ResponseEntity<String>("Usuário " + nomeUser + " removido com sucesso!", HttpStatus.OK);
	}

}
