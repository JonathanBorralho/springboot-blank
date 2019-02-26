package com.boot.blankproject.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.blankproject.model.Usuario;
import com.boot.blankproject.repo.helper.usuario.UsuarioQueries;

public interface UsuarioRepo extends JpaRepository<Usuario, Long>, UsuarioQueries {
	
	Optional<Usuario> findByEmail(String email);

	List<Usuario> findByCodigoIn(Long[] codigos);

}
