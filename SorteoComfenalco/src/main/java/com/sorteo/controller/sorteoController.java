package com.sorteo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sorteo.inte_service.*;
import com.sorteo.interfaces.premioInterface;
import com.sorteo.interfaces.tiposInterface;
import com.sorteo.models.*;


@Controller
@RequestMapping
public class sorteoController {


	@Autowired
	private sorteoInte_service service;
	
	@Autowired
	tiposInterface TiposInterface;
	
	@Autowired
	premioInterface PremioInterface;

  
	
	@GetMapping("/")
	public String Listar(Model modelo) {
		
		    List<Persona>personas = service.Listar();
		    List<Persona>ganadores = service.ganadorevacio();
		    List<Premio>premios = (List<Premio>) PremioInterface.findAll();
	        modelo.addAttribute("premios",premios);
	        modelo.addAttribute("ganadores",ganadores);
	        modelo.addAttribute("personas", personas);
			modelo.addAttribute("listTap","active");
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String agregar(Model modelo) {
		modelo.addAttribute("form",new Persona());
		modelo.addAttribute("tipos",TiposInterface.findAll()); 
		return "Form";
	}
	
	@GetMapping("/sorteo")
	public String sorteo(Model modelo) throws Exception {
		
		try {
			
			service.ganadores();
		} catch (Exception e) {
			modelo.addAttribute("listErrorMenssage",e.getMessage());
		}
		
       /** List<Premio>premios = (List<Premio>) PremioInterface.findAll();
        modelo.addAttribute("premios",premios);
		List<Persona>ganadores = service.ganadorevacio();
		modelo.addAttribute("ganadores",ganadores);
		modelo.addAttribute("premios",premios);*/
		modelo.addAttribute("formTab","active");
		return Listar(modelo);
	}
	

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("form")Persona p, BindingResult result, ModelMap modelo) 
	{   
		if(result.hasErrors()) {
			modelo.addAttribute("form", p);
			modelo.addAttribute("tipos",TiposInterface.findAll());
			
			return "Form";
		}
		else {
			try {
				service.createPersona(p);
				modelo.addAttribute("form", new Persona());
				
				modelo.addAttribute("tipos",TiposInterface.findAll());
				 List<Premio>premios = (List<Premio>) PremioInterface.findAll();
			        modelo.addAttribute("premios",premios);
				List<Persona>ganadores = service.ganadorevacio();
				modelo.addAttribute("ganadores",ganadores);
				
			} catch (Exception e) {
				modelo.addAttribute("formErrorMessage",e.getMessage());
				modelo.addAttribute("form", p);	
				 List<Premio>premios = (List<Premio>) PremioInterface.findAll();
			        modelo.addAttribute("premios",premios);
				modelo.addAttribute("premios",PremioInterface.findAll());
				List<Persona>ganadores = service.ganadorevacio();
				modelo.addAttribute("ganadores",ganadores);
				return "Form";
			}
		}
		
        List<Persona>personas = service.Listar();
		modelo.addAttribute("personas", personas);
		return "index";
		}

	@GetMapping("/editar/{id}")
	public String editar(Model modelo,@PathVariable(name = "id") Long id)throws Exception {
		Optional<Persona> presto = service.ListarId(id);
		Persona pre = presto.get();
		if(pre.getId()==null) {
			modelo.addAttribute("form",new Persona());
		}
		modelo.addAttribute("tipos",TiposInterface.findAll());
		 List<Premio>premios = (List<Premio>) PremioInterface.findAll();
	        modelo.addAttribute("premios",premios);
	    modelo.addAttribute("form",pre );
	    modelo.addAttribute("editMode","true");
		return "Form";
		}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("form")Persona p, BindingResult result, ModelMap modelo) {
		
		if(result.hasErrors()) {
			modelo.addAttribute("form", p);
			modelo.addAttribute("editMode","true");
			modelo.addAttribute("tipos",TiposInterface.findAll());
			
			return "Form";
		}
		else {
			try {
				service.updatePersona(p);
				modelo.addAttribute("form", new Persona());
				modelo.addAttribute("tipos",TiposInterface.findAll());
				 List<Premio>premios = (List<Premio>) PremioInterface.findAll();
			        modelo.addAttribute("premios",premios);
				
			} catch (Exception e) {
				modelo.addAttribute("formErrorMessage",e.getMessage());
				modelo.addAttribute("form", p);	
				modelo.addAttribute("tipos",TiposInterface.findAll());
				 List<Premio>premios = (List<Premio>) PremioInterface.findAll();
			        modelo.addAttribute("premios",premios);
				modelo.addAttribute("editMode","true");
				return "Form";
			}
		}
		
        List<Persona>personas = service.Listar();
		modelo.addAttribute("personas", personas);
		return "index";
	}
	
	@GetMapping("/index/cancel")
	public String cancelEditUser(ModelMap modelo) {
		List<Persona>personas = service.Listar();
		modelo.addAttribute("personas", personas);
		return "index";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarPer(Model model, @PathVariable(name="id") Long id) {
			try {
			service.eliminarpersona(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMenssage",e.getMessage());
		}
			List<Persona>personas = service.Listar();
			model.addAttribute("personas", personas);
		return "redirect:/";
	
		
	}
	
}
