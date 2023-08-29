package com.example.springboot.api.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot.api.dao.IClientDao;
import com.example.springboot.api.entities.Client;
import com.example.springboot.api.exceptions.ResourceNotFoundException;
import com.example.springboot.api.services.IClientService;

@Service
public class ClientService implements IClientService {
	
	private static final String CLIENT_SERVICE = "Client";
	
	@Autowired
	public IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional
	public Client update(Long id, Client newClient) {
		Client client = clientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(CLIENT_SERVICE, "id", id));
		client.setName(newClient.getName());
		client.setLastName(newClient.getLastName());
		client.setUpdateAt(new Date());
		return clientDao.save(client);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Client client = clientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(CLIENT_SERVICE, "id", id));
		clientDao.deleteById(client.getId());
	}

	@Override
	public ResponseEntity<Client> save(Client client) {
		client.setCreateAt(new Date());
		return new ResponseEntity<>(clientDao.save(client), HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return clientDao.findById(id).orElseThrow(() -> new ResourceNotFoundException(CLIENT_SERVICE, "id", id));
	}

}
