package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.model.ProdutoModel;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @GetMapping("/novo")
    public String adicionarProduto(Model model) {

        model.addAttribute("produto", new ProdutoModel());

        return "produto/novo-produto";
    }

    @PostMapping("/salvar")
    public String insertProduto(ProdutoModel produto) {

        repository.save(produto);

        return "redirect:/produtos/novo";
    }

}
