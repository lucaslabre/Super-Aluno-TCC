package com.superaluno.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superaluno.entities.CadernoEntity;
import com.superaluno.entities.dtos.CadernoEntityDTO;
import com.superaluno.services.CadernoService;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "cadernos")
public class CadernoController {
	
	@Autowired
	private CadernoService cadernoService;
	
	@GetMapping("/usuario/{idUsuario}")
	public List<CadernoEntityDTO> findAllCadernoByUsuario(@PathVariable Long idUsuario) {
		List<CadernoEntity> cadernos = this.cadernoService.findAllCadernoByUsuario(idUsuario);
		List<CadernoEntityDTO> cadernosDTO = new ArrayList<>();
		for(CadernoEntity caderno : cadernos) {
			cadernosDTO.add(new CadernoEntityDTO(caderno));
		}
		return cadernosDTO;
	}
	
	@GetMapping("/assunto/{idAssunto}")
	public ResponseEntity<CadernoEntityDTO> findCadernoByIdAssunto(@PathVariable Long idAssunto) {
		CadernoEntity caderno = this.cadernoService.findCadernoByIdAssunto(idAssunto);
		return ResponseEntity.ok().body(new CadernoEntityDTO(caderno));
	}
	
	@GetMapping("/{idCaderno}")
	public ResponseEntity<CadernoEntityDTO> findCadernoByIdCaderno(@PathVariable Long idCaderno) {
		CadernoEntity caderno = this.cadernoService.findCadernoByIdCaderno(idCaderno);
		return ResponseEntity.ok().body(new CadernoEntityDTO(caderno));
	}
	
	@PostMapping()
	public CadernoEntityDTO createCaderno(@RequestBody CadernoEntityDTO cadernoDTO) {
		CadernoEntity caderno = this.cadernoService.createCaderno(cadernoDTO);
		cadernoDTO = new CadernoEntityDTO(caderno); 
		return cadernoDTO;
	}
	
	@PutMapping()
	public CadernoEntityDTO updateCaderno(@RequestBody CadernoEntityDTO cadernoDTO) {
		CadernoEntity caderno = this.cadernoService.updateCaderno(cadernoDTO);
		return new CadernoEntityDTO(caderno);
	}

}
