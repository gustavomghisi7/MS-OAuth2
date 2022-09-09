package com.aulas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aulas.entidade.User;
import com.aulas.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	BCryptPasswordEncoder passwordEndcoder;
	
	public User salvar(User user) {
		String senha = user.getSenha();
		user.setSenha(passwordEndcoder.encode(senha));
		return repo.save(user);
	}
	
	public List<User> consulta() {
		return repo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Usuário não existe");
		}
		
		return user;
	}
}
