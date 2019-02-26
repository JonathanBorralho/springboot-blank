package com.boot.blankproject.enums;

import com.boot.blankproject.repo.UsuarioRepo;

public enum StatusUsuario {
	
	ATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepo usuarioRepo) {
			usuarioRepo.findByCodigoIn(codigos).forEach(u -> u.setAtivo(true));
		}
	},
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, UsuarioRepo usuarioRepo) {
			usuarioRepo.findByCodigoIn(codigos).forEach(u -> u.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] codigos, UsuarioRepo usuarioRepo);
}
