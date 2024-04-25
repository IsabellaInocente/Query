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

import com.example.demo.entites.Aluno;
import com.example.demo.service.AlunoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	private final AlunoService alunoService;
	

	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> buscaAlunoControlId(@PathVariable Long id){
	 Aluno aluno = alunoService.buscaAlunoId(id);
	 if(aluno !=null) {
		 return ResponseEntity.ok(aluno);
	 }
	 else {
		 return ResponseEntity.notFound().build();
	 }
	}
	@GetMapping
	public ResponseEntity<List<Aluno>> buscaTodosAlunosControl(){
		List<Aluno> Aluno = alunoService.buscaTodosAlunos();
		return ResponseEntity.ok(Aluno);
	}
	@GetMapping("/cidade/{cidade}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorCidade(@PathVariable String cidade) {
		List<Aluno> alunos = alunoService.buscarAlunosPorCidade(cidade);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/renda/{renda}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorRenda(@PathVariable Double renda) {
		List<Aluno> alunos = alunoService.buscarAlunosPorRenda(renda);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/ra/{ra}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorRa(@PathVariable String ra) {
		List<Aluno> alunos = alunoService.buscarAlunosPorRa(ra);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/cidade/{cidade}/renda/{renda}")
	public ResponseEntity<List<Aluno>> buscarAlunosPorCidadeERenda(@PathVariable String cidade, @PathVariable Double renda) {
		List<Aluno> alunos = alunoService.buscarAlunosPorCidadeERenda(cidade, renda);
		return ResponseEntity.ok(alunos);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Aluno> findAlunosPorNome(@PathVariable String nome){
		return alunoService.findByNome(nome);
	}
	@GetMapping("/nome-completo/{nomecompleto}")
	public List<Aluno> findAlunosPorNomeCompletoLike(@PathVariable String nomeCompleto){
		return alunoService.findByNomeCompletoLike(nomeCompleto);
	}
	
	
	@PostMapping
	public ResponseEntity<Aluno> salvaAlunoCpntrol(@RequestBody @Valid Aluno aluno) {
		Aluno salvaAluno = alunoService.salvaAluno(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaAluno);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alteraAlunoControl(@PathVariable Long id, @RequestBody @Valid Aluno aluno){
		Aluno alteraAluno = alunoService.alterarAluno(id, aluno);
		if(alteraAluno !=null) {
			return ResponseEntity.ok(aluno);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Aluno> apagaAlunoControl(@PathVariable Long id){
		boolean apagar = alunoService.apagarAluno(id);
		if (apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

}