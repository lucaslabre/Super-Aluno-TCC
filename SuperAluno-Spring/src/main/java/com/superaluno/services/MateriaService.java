package com.superaluno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superaluno.entities.MateriaEntity;
import com.superaluno.entities.UsuarioEntity;
import com.superaluno.repositories.MateriaRepository;
import com.superaluno.services.exceptions.ObjectNotFoundException;

@Service
public class MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	public MateriaEntity findById(Long idMateria) {
		Optional<MateriaEntity> materia = this.materiaRepository.findById(idMateria);
		return materia.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+idMateria+", Tipo: "+UsuarioEntity.class.getName()));
	}

}