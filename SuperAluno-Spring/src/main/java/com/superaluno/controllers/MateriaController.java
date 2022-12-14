package com.superaluno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superaluno.entities.MateriaEntity;
import com.superaluno.entities.dtos.MateriaEntityDTO;
import com.superaluno.services.MateriaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/materias")
public class MateriaController {
	
	@Autowired
	private MateriaService materiaService;
	
	@GetMapping("/{idMateria}")
	public MateriaEntityDTO findMateriaByIdMateria(@PathVariable Long idMateria) {
		MateriaEntity materia = this.materiaService.findMateriaByIdMateria(idMateria);
		MateriaEntityDTO materiaDTO = new MateriaEntityDTO(materia);
		return materiaDTO;
	}
	
	@GetMapping("")
	public List<MateriaEntityDTO> findAllMateriaByUsuario() {
		return null;
	}
	
	@PostMapping("")
	public MateriaEntityDTO createMateria(@RequestBody MateriaEntityDTO materiaDTO) {
		MateriaEntity materia = this.materiaService.createMateria(materiaDTO);
		return new MateriaEntityDTO(materia);
	}

}
