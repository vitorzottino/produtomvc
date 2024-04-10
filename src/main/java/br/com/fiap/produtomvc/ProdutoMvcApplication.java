package br.com.fiap.produtomvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.fiap.produtomvc"})
public class ProdutoMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoMvcApplication.class, args);

	}
}
