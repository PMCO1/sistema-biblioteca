package com.ejemplo.sistema_biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.sistema_biblioteca.model.Autor;
import com.ejemplo.sistema_biblioteca.repository.AutorRepository;


@Controller
@RequestMapping("/autores")
public class AutorController {
		@Autowired
		private AutorRepository repositorioA;
		
		@GetMapping
		public String ver(Model modelo) {
			modelo.addAttribute("autores", repositorioA.findAll());
			return "lista_autores";
		}
		
		@GetMapping("/new_autor")
		public String mostrarForm(Model modelo) {
			modelo.addAttribute("autor", new Autor());
			return "form_autores";
		}
		
		@PostMapping("/save")
		public String save(@ModelAttribute Autor autor, RedirectAttributes flash) {
		    try {
		        repositorioA.save(autor);
		        flash.addFlashAttribute("exito", "Autor guardado correctamente");
		    } catch (Exception e) {
		        flash.addFlashAttribute("error", "Error al guardar el autor: " + e.getMessage());
		    }
		    return "redirect:/autores";
		}
		
		@GetMapping("/edite/{id}")
		public String edite(@PathVariable Long id, Model model) {
			Autor autor = repositorioA.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de autor invalido: "+id));
			model.addAttribute("autor", autor);
			return "form_autores";
		}
		
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable Long id, RedirectAttributes flash) {
		    try {
		        repositorioA.deleteById(id);
		        flash.addFlashAttribute("exito", "Autor eliminado correctamente");
		    } catch (Exception e) {
		        flash.addFlashAttribute("error", "Error al eliminar el autor");
		    }
		    return "redirect:/autores";
		}
}
