package com.sorteo.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sorteo.models.Persona;

@Repository
public interface sorteoInterfaces extends CrudRepository<Persona,Long> {

	@Query("FROM Persona WHERE gano = ?1 ")
	List<Persona> findByfirstGano(String firstGano );
}
