package com.sorteo.inte_service;

import java.util.List;
import java.util.Optional;
import com.sorteo.models.*;

public interface sorteoInte_service {
	
	public Persona createPersona(Persona P) throws Exception;
	
	public List<Persona>Listar();
	
	public Optional<Persona>ListarId(long id);
	
	public Persona getPersonaById(Long id) throws Exception;

	public Persona updatePersona(Persona P) throws Exception;
	
	public void eliminarpersona(Long id) throws Exception;
	
	public List<Persona>ganadores() throws Exception;

	public Premio actualizapremio(Premio Premi) throws Exception;

	public Premio getPremioById(Long id) throws Exception;

	public List<Persona> ganadorevacio();
	


}
