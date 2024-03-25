package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;


    @GetMapping()
    @Transactional(readOnly = true)
    public String findAll(Model model) {
        model.addAttribute("categorias", repository.findAll());
        return "/categoria/listar-categorias";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Categoria inválida - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Categoria inválida - id: " + id);
        }

        return "redirect:/categorias";
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable ("id") Long id, Model model ){

        Categoria categoria = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Categoria inválida - id: " + id)
        );

        model.addAttribute("categoria", categoria);
        return "/categoria/editar-categoria";
    }

    @PutMapping("/{id}")
    @Transactional
    public String update(@PathVariable("id") Long id,
                         @Valid Categoria categoria,
                         BindingResult result){
        if(result.hasErrors()){
            categoria.setId(id);
            return "/categoria/editar-categoria";
        }
        repository.save(categoria);
        return "redirect:/categorias";
    }



}