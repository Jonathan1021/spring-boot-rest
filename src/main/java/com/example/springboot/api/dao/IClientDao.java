package com.example.springboot.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.api.entities.Client;

public interface IClientDao extends CrudRepository<Client, Long>{

}
