package com.sorteo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.sorteo.inte_service.sorteoInte_service;
import com.sorteo.interfaces.premioInterface;
import com.sorteo.interfaces.sorteoInterfaces;
import com.sorteo.models.Persona;
import com.sorteo.models.Premio;



@Service
public class sorteoService implements sorteoInte_service {
	
	@Autowired
	private sorteoInterfaces data;

	@Autowired
	premioInterface PremioInterface;


	@Override
	public List<Persona> Listar() {
		
		return (List<Persona>)data.findAll();
	}
	
	@Override
	public List<Persona> ganadorevacio() {
		String vari = "S";
		return (List<Persona>)data.findByfirstGano(vari);
	}
	
	@Override
	public List<Persona> ganadores() throws Exception {
		List<Persona> ganadores = (List<Persona>) data.findAll();
		List<Premio> premios = (List<Premio>) PremioInterface.findAll();
		
		int n_perso= ganadores.size();
		int n_pre =premios.size();
		int suma_premios=0;
		int caso= 0;
		
		for(int x=0;x<premios.size();x++) {
			Premio pre_gano = premios.get(x);
			 suma_premios =  suma_premios + pre_gano.getCantidad();
			 if (suma_premios >= n_perso  ) {
					suma_premios = n_perso;
				}
			 if (suma_premios == 0 && n_perso==0  ) {
				throw new Exception("No hay condiciones para el sorteo  ");
				
				}
		}
		
		while(suma_premios > caso) {
			int r1 = (int)(Math.random()*n_pre);
			int r2 = (int)(Math.random()*n_perso);
			
			Persona gano = ganadores.get(r2);
			Premio pre_gano = premios.get(r1);
			
			int disponible = pre_gano.getCantidad();
			
			String verifica = gano.getGano();
			String descri = pre_gano.getDescripcion();
			pre_gano.getCantidad();
			
			if(disponible==0 || verifica =="S" ) {
				
			}else {
				int di = disponible -1;
				gano.setGano("S");
				gano.setPremio(descri);
				pre_gano.setCantidad(di);
				
				updatePersona(gano);
				actualizapremio(pre_gano);
				suma_premios --;
				  if(suma_premios == 0) {
					  throw new Exception("Felicidades a los ganadores ");
				  }
			}	
		}
		
		String vari ="S";
		return(List<Persona>)data.findByfirstGano(vari);
	}
	
	
	private boolean virificausuario(Persona P) throws Exception {
		  List<Persona> participantList = new ArrayList<Persona>();
		  participantList = (List<Persona>) data.findAll();
		  int verifica = P.getN_documento();
		  for(Persona per : participantList)
		  {
			  int contra= per.getN_documento();
			  if(verifica == contra) {
				  throw new Exception("USUARIO YA EXISTE ...");
			  }
			  if(verifica == 0) {
				  throw new Exception("EL CERO NO ES VALIDO");
			  }
		  }
		return true;
	}

	@Override
	public Persona createPersona(Persona P) throws Exception {
		if(virificausuario(P)) {
			String gano =P.getGano();
			if(gano== null||gano=="") {
				P.setGano("N");
				P.setPremio("N");
			}
			P =data.save(P);
		}
		return P;
	}

	@Override
	public Optional<Persona> ListarId(long id) {
		
		return data.findById(id);
	}

	@Override
	public Persona getPersonaById(Long id) throws Exception {
		
		return data.findById(id).orElseThrow(() -> new Exception("La perona No se encuentra..."));
		
	}
	
	@Override
	public Premio getPremioById(Long id) throws Exception {
		
		return PremioInterface.findById(id).orElseThrow(() -> new Exception("El premio No se encuentra..."));
		
	}


	@Override
	public Persona updatePersona(Persona P) throws Exception {
		
		Persona toPersona = getPersonaById(P.getId());
		mapUser(P, toPersona);
		return data.save(toPersona);
	}
	
	@Override
	public Premio actualizapremio(Premio Premi) throws Exception {
		Premio toPremio = getPremioById(Premi.getId());
		mapPremio(Premi,toPremio);
		return PremioInterface.save(toPremio) ;
	}

	protected void mapUser(Persona from,Persona to) {
		
		to.setTipo_documento (from.getTipo_documento());
		to.setN_documento(from.getN_documento());
		to.setNombres(from.getNombres());
		to.setApellidos(from.getApellidos());
		to.setFecha_nacimiento(from.getFecha_nacimiento());
		to.setGano(from.getGano());
		to.setPremio(from.getPremio());
		
	}
	
protected void mapPremio(Premio from,Premio to) {
		
		to.setCantidad(from.getCantidad()); 
		to.setDescripcion(from.getDescripcion());
	}

	@Override
	public void eliminarpersona(Long id) throws Exception {
		Persona per = data.findById(id) .orElseThrow(() -> new Exception("No existe el usuario -"+this.getClass().getName()));
		
		data.delete(per);
		
	}

	

	

	



	
	

}
