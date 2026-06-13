package com.comunicacao.api.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.api.modelos.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
}