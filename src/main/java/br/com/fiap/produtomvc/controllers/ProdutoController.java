package br.com.fiap.produtomvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {


    @GetMapping
    public String adicionarProduto() {

        return "produto/novo-produto";
    }

    @PostMapping
    public String printarProduto(Model model){

        System.out.println(model.getAttribute("nome"));
        System.out.println(model.addAttribute("nome"))  ;


        return "redirect:/produtos";
    }

}
