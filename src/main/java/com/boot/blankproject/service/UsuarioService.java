package com.boot.blankproject.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.boot.blankproject.enums.StatusUsuario;
import com.boot.blankproject.exception.UsuarioJaCadastradoException;
import com.boot.blankproject.model.Usuario;
import com.boot.blankproject.repo.UsuarioRepo;
import com.boot.blankproject.repo.filter.UsuarioFilter;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Optional<Usuario> retornaUsuarioAtivoPorEmail(String email){
		return usuarioRepo.retornaUsuarioAtivoPorEmail(email);
	}
	
	@Transactional
	public void salvar(@Valid Usuario usuario){
		Optional<Usuario> usuarioExistente = usuarioRepo.findByEmail(usuario.getEmail());	
		if((usuarioExistente.isPresent()) && 
				(usuarioExistente.get().getCodigo().compareTo(usuario.getCodigo()) == 0)){
			throw new UsuarioJaCadastradoException("Usuário já cadastrado.");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarioRepo.save(usuario);
	}
	
	public List<String> retornaPermissoes(Usuario usuario) {
		return usuarioRepo.retornaPermissoes(usuario);
	}

	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usuarioRepo.filtrar(usuarioFilter, pageable);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario status) {
		status.executar(codigos, usuarioRepo);
	}

	public Usuario buscarComGrupos(Long codigo) {
		return usuarioRepo.buscarComGrupos(codigo);
	}

}
