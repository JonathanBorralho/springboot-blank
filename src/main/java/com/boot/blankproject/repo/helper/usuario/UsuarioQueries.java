package com.boot.blankproject.repo.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.boot.blankproject.model.Usuario;
import com.boot.blankproject.repo.filter.UsuarioFilter;

public interface UsuarioQueries {
	
public Optional<Usuario> retornaUsuarioAtivoPorEmail(String email);
	
	public List<String> retornaPermissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
	
	public Usuario buscarComGrupos(Long codigo);

}
