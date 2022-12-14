package com.superaluno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superaluno.entities.AssuntoEntity;
import com.superaluno.entities.CadernoEntity;
import com.superaluno.entities.UsuarioEntity;
import com.superaluno.entities.dtos.AssuntoEntityDTO;
import com.superaluno.entities.dtos.CadernoEntityDTO;
import com.superaluno.repositories.CadernoRepository;

@Service
public class CadernoService {
	
	@Autowired
	private CadernoRepository cadernoRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AssuntoService assuntoService;
	
	public List<CadernoEntity> findAllCadernoByUsuario(Long idUsuario) {
		UsuarioEntity usuario = this.usuarioService.findUsuarioByIdUsuario(idUsuario);
		return this.cadernoRepository.findAllCadernoByUsuario(usuario);
	}

	public CadernoEntity findCadernoByIdAssunto(Long idAssunto) {
		AssuntoEntity assunto = this.assuntoService.findAssuntoByIdAssunto(idAssunto);
		return this.cadernoRepository.findCadernoByAssunto(assunto);
	}

	public CadernoEntity updateCaderno(CadernoEntityDTO cadernoDTO) {
		CadernoEntity caderno = this.findCadernoByIdCaderno(cadernoDTO.getIdCaderno());
		caderno.setConteudo(cadernoDTO.getConteudo());
		return this.cadernoRepository.save(caderno);
	}

	public CadernoEntity findCadernoByIdCaderno(Long idCaderno) {
		return this.cadernoRepository.findCadenoByIdCaderno(idCaderno);
	}

	public CadernoEntity createCaderno(CadernoEntityDTO cadernoDTO) {
		CadernoEntity caderno = new CadernoEntity(cadernoDTO);
		caderno.setPublicado(false);
		AssuntoEntity assunto = this.assuntoService.findAssuntoByNomeAssunto(cadernoDTO.getAssunto().getNomeAssunto());
		if(assunto != null) {			
			caderno.setAssunto(this.assuntoService.findAssuntoByNomeAssunto(cadernoDTO.getAssunto().getNomeAssunto()));
		} else {
			this.assuntoService.createAssunto(new AssuntoEntity(cadernoDTO.getAssunto()));
		}
		
		caderno.setUsuario(this.usuarioService.findUsuarioByIdUsuario(cadernoDTO.getUsuario().getIdUsuario()));
		return this.cadernoRepository.save(caderno);
	}

}
