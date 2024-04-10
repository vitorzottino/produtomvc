package br.com.fiap.produtomvc.services;

import br.com.fiap.produtomvc.models.Produto;
import br.com.fiap.produtomvc.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Transactional(readOnly = true)
    public List<Produto> findAll() {
        return repository.findAll();
    }

    public void insert(Produto produto) {
        repository.save(produto);
    }

    public Produto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Recurso invalido " + id));
    }

    public Produto update(Long id, Produto entity) {
        try {
            Produto produto = repository.getReferenceById(id);
            copyToProduto(entity, produto);
            return repository.save(produto);


        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Recurso nao encontrado");

        }
    }

    private void copyToProduto(Produto entity, Produto produto) {
        produto.setNome(entity.getNome());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso invalido " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha na integridade referencial" + id);

        }
    }
}
