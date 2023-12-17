package soa.dao;

import soa.entities.Clients;

import java.util.List;

public interface IClientDao {
    public List<Clients> findAll();
}
