package br.com.renato.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{
}
