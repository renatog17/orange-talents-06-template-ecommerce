package br.com.renato.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Caracteristica;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Long>{
}
