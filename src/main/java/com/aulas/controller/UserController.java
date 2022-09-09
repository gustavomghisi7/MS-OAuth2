package com.aulas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aulas.entidade.User;
import com.aulas.repository.UserRepository;
import com.aulas.service.UserService;

@RestController
@RequestMapping("")
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repo;
	
	@PostMapping("/user")
	public ResponseEntity<Object> salvar(@RequestBody User user) {
		User u = repo.findByEmail(user.getEmail());
		if(u != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuário já cadastrado!!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(service.salvar(user));
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consulta());
	}

}
