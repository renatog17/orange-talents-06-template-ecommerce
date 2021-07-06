package br.com.renato.mercadolivre.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Optional<Categoria> findByNome(String nome);
}
