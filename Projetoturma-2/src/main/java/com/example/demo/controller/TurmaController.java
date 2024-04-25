package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entites.Turma;
import com.example.demo.service.TurmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/turma")
public class TurmaController {
	private final TurmaService turmaService;

	@Autowired
	public TurmaController(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscaTurmaControlId(@PathVariable Long id){
		Turma turma = turmaService.buscaTurmaId(id);
		if(turma !=null) {
			return ResponseEntity.ok(turma);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	public ResponseEntity<List<Turma>> buscaTodosTurmasControl(){
		List<Turma> Turma = turmaService.buscaTodosTurmas();
		return ResponseEntity.ok(Turma);
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Turma>> buscarTurmasPorNome(@PathVariable String nome) {
		List<Turma> turmas = turmaService.buscarTurmasPorNome(nome);
		return ResponseEntity.ok(turmas);
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Turma>> buscarTurmasPorDescricao(@PathVariable String descricao) {
		List<Turma> turmas = turmaService.buscarTurmasPorDescricao(descricao);
		return ResponseEntity.ok(turmas);
	}
	@GetMapping("/nome/{nome}/descricao/{descricao}")
	public ResponseEntity<List<Turma>> buscarTurmasPorNomeEDescricao(@PathVariable String nome, @PathVariable String descricao) {
		List<Turma> turmas = turmaService.buscarTurmasPorNomeEDescricao(nome, descricao);
		return ResponseEntity.ok(turmas);
	}
	@PostMapping
	public ResponseEntity<Turma> salvaTurmaCpntrol(@RequestBody @Valid Turma turma) {
		Turma salvaTurma = turmaService.salvaTurma(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaTurma);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Turma> alteraTurmaControl(@PathVariable Long id, @RequestBody @Valid Turma turma){
		Turma alteraTurma = turmaService.alterarTurma(id, turma);
		if(alteraTurma !=null) {
			return ResponseEntity.ok(turma);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Turma> apagaTurmaControl(@PathVariable Long id){
		boolean apagar = turmaService.apagarTurma(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}