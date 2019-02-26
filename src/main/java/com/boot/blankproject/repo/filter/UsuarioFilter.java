package com.boot.blankproject.repo.filter;

import java.util.List;

import com.boot.blankproject.model.Grupo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFilter {

	private String nome;
	
	private String email;
	
	private List<Grupo> grupos;
}
