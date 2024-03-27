package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.models.Categoria;
import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.CategoriaRepository;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @ModelAttribute("categorias")
    public List<Categoria> categorias() {
        return categoriaRepository.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("produto", new Produto());

        return "produto/novo-produto";
    }

    @PostMapping()
    @Transactional
    public String insert(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "produto/novo-produto";
        }
        repository.save(produto);
        attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");

        return "redirect:/produtos/form";
    }

    @GetMapping()
    @Transactional(readOnly = true)
    public String findAll(Model model) {
        model.addAttribute("produtos", repository.findAll());

        return "/produto/listar-produtos";
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public String findById(@PathVariable("id") Long id, Model model) {
        Produto produto = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inválido - id: " + id));
        model.addAttribute("produto", produto);

        return "/produto/editar-produto";
    }

    @PutMapping("/{id}")
    @Transactional
    public String update(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            produto.setId(id);
            return "/produto/editar-produto";
        }
        repository.save(produto);

        return "redirect:/produtos";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Produto inválido - id: " + id);
        }

        return "redirect:/produtos";
    }
}