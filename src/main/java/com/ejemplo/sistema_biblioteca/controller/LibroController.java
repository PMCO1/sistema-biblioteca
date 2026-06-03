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
import com.ejemplo.sistema_biblioteca.model.Libro;
import com.ejemplo.sistema_biblioteca.repository.AutorRepository;
import com.ejemplo.sistema_biblioteca.repository.LibroRepository;

@Controller
@RequestMapping("/libros")
public class LibroController {
	@Autowired
	private LibroRepository repositorioL;
	@Autowired
	private AutorRepository repositorioA;
	
	@GetMapping
	public String ver(Model modelo) {
		modelo.addAttribute("libros", repositorioL.findAll());
		return "lista_libros";
	}
	
	@GetMapping("/new_libro")
	public String mostrarForm(Model modelo) {
		modelo.addAttribute("libro", new Libro());
		modelo.addAttribute("autores", repositorioA.findAll());
		return "form_libros";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Libro libro, RedirectAttributes flash) {
		try {
			repositorioL.save(libro);
			flash.addFlashAttribute("exito", "Libro guardado correctamente");
		} catch (Exception e) {
			flash.addFlashAttribute("error", "Error al guardar el libro: " + e.getMessage());
		}
		return "redirect:/libros";
	}
	
	@GetMapping("/edite/{id}")
	public String edite(@PathVariable Long id, Model model) {
		Libro libro = repositorioL.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de libro invalido: "+id));
		model.addAttribute("libro", libro);
		model.addAttribute("autores", repositorioA.findAll());
		return "form_libros";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		try {
		repositorioL.deleteById(id);
		flash.addFlashAttribute("exito", "Libro eliminnado correctamente");
		} catch (Exception e) {
			flash.addFlashAttribute("error", "Error al eliminar el libro");
		}
		return "redirect:/libros";
	}
}
