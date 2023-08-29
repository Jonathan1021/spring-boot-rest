package com.example.springboot.api.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.api.entities.Client;
import com.example.springboot.api.exceptions.ResponseEntityErrorException;
import com.example.springboot.api.payloads.ApiResponse;
import com.example.springboot.api.services.IClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	private static final Logger logger = LogManager.getLogger(ClientController.class);

	@Autowired
	public IClientService clientService;
	
	@ExceptionHandler(ResponseEntityErrorException.class)
	public ResponseEntity<ApiResponse> handleExceptions(ResponseEntityErrorException exception) {
		return exception.getApiResponse();
	}

	@GetMapping
	public List<Client> list() {
		return clientService.findAll();
	}

	@GetMapping("/{id}")
	public Client findById(@PathVariable Long id) {
		return clientService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Client> save(@Valid @RequestBody Client client) {
		return clientService.save(client);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/{id}")
	public Client update(@PathVariable Long id, @RequestBody Client client) {
		return clientService.update(id, client);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		clientService.deleteById(id); 
	}
}
