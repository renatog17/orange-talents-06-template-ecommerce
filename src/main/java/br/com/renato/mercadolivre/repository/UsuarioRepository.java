package br.com.renato.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.renato.mercadolivre.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
