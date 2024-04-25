package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entites.Turma;
import com.example.demo.repository.TurmaRepository;

@Service
public class TurmaService {
	private final TurmaRepository turmaRepository;
	
	@Autowired
	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
	public List<Turma> buscaTodosTurmas(){
		return turmaRepository.findAll();
	}
	public List<Turma> buscarTurmasPorNome(String nome) {
		return turmaRepository.findByNome(nome);
	}
	public List<Turma> buscarTurmasPorDescricao(String descricao) {
		return turmaRepository.findByDescricao(descricao);
	}
	public List<Turma> buscarTurmasPorNomeEDescricao(String nome, String descricao) {
		return turmaRepository.findByNomeAndDescricao(nome, descricao);
	}
	public Turma buscaTurmaId(Long id) {
		Optional <Turma> Turma = turmaRepository.findById(id);
		return Turma.orElse(null);
	}
	public Turma salvaTurma (Turma Turma) {
		return turmaRepository.save(Turma);
	}
	public Turma alterarTurma(Long id, Turma alterarT) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			alterarT.setId(id);
			return turmaRepository.save(alterarT);
		}
		return null;
	}
	public boolean apagarTurma(Long id) {
		Optional <Turma> existeTurma = turmaRepository.findById(id);
		if (existeTurma.isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		}
		return false;
	}

}