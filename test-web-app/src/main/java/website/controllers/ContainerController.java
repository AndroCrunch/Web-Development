package website.controllers;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import website.model.Container;
import website.repository.ContainersRepository;

@Controller
public class ContainerController {
	
	@Autowired
	private ContainersRepository repository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("containers", repository.findAll());
		return "index";
		
	}
	
	@GetMapping("/add")
	public String add(Container container, Model model) {
		return "add";
	}
	
	@PostMapping("/add")
	public String add(@Valid Container container, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add";
		}
		
		repository.save(container);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Optional<Container> c = repository.findById(id);
		if(c.isPresent()) {
			Container cont = c.get();
			repository.delete(cont);
		}
		
		return "redirect:/";
	}
}
