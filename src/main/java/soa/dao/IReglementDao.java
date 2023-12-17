package soa.dao;


import soa.entities.Reglement;

import java.util.Date;
import java.util.List;


public interface IReglementDao
{
	Reglement save (Reglement r);
	List<Reglement> findAll();
	void delete (Long id);




	}
