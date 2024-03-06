package br.com.fiap.produtomvc.controllers;

import br.com.fiap.produtomvc.model.ProdutoModel;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String insertProduto(@Valid  ProdutoModel produto, BindingResult result,
                                RedirectAttributes attributes) {

        if(result.hasErrors()){

            attributes.addFlashAttribute("mensagem", "nao foi possivel salvar");
            return "redirect:/produtos/novo";

        }
        repository.save(produto);
        attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");


        return "redirect:/produtos/novo";
    }

    @GetMapping("/listar")
    public String listarProdutos(Model model){
        model.addAttribute("produtos", repository.findAll());
        return "/produto/listar-produtos";
    }

}
