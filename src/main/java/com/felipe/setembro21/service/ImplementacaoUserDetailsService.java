package com.felipe.setembro21.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.felipe.setembro21.model.Usuario;
import com.felipe.setembro21.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findUserByLogin(email);
		if (usuario == null) {
			throw new UsernameNotFoundException("Não existe usuário cadastrado com este e-mail.");
		}
		return new User(usuario.getEmail(), usuario.getPassword(), usuario.getAuthorities());
	}
	
	
}
