package br.com.renato.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{
}
