package br.com.renato.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
