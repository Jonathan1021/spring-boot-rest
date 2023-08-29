package com.example.springboot.api.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.springboot.api.entities.Client;

public interface IClientService {

	public List<Client> findAll();
	
	public Client findById(Long id);

	public Client update(Long id, Client client);

	public void deleteById(Long Id);

	public ResponseEntity<Client> save(Client client);

}
