package com.generation.loja_de_games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.loja_de_games.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository <Produtos, Long>{

	public List <Produtos> findAllByJogoContainingIgnoreCase(@Param("jogo")String jogo);
	
}