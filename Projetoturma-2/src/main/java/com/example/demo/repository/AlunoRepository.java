package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entites.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long > {
	List<Aluno> findByCidade(String cidade);
	/*List<Aluno> findByNome(String nome);*/
	List<Aluno> findByRenda(Double renda);
	List<Aluno> findByRa(String ra);
	List<Aluno> findByCidadeAndRenda(String cidade, Double renda);
	
	@Query("SELECT a FROM ALUNO a WHERE a.nome = :nome")
	List<Aluno> findByNome(@Param("nome") String nome);
	
	@Query("SELECT a FROM Aluno a WHERE a.nomeCompleto LIKE:nomeCompleto")
	List<Aluno> findByNomeLike(@Param("nomeCompleto") String nomeCompleto);
	
	@Query("SELECT a FROM Aluno a JOIN a.turma t WHERE t.id = :turmaid")
	List<Aluno> findByTurmaId(@Param("turmaid") Long turmaId);

}