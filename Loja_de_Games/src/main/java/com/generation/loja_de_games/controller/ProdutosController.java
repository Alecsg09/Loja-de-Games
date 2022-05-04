package com.generation.loja_de_games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.loja_de_games.model.Produtos;
import com.generation.loja_de_games.repository.CategoriaRepository;
import com.generation.loja_de_games.repository.ProdutosRepository;


@RestController
@RequestMapping ("/produtos")
@CrossOrigin (origins = "*" , allowedHeaders = "*")


public class ProdutosController {
	
	@Autowired 
	private ProdutosRepository produtosRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity <List<Produtos>> getAll (){
        return ResponseEntity.ok(produtosRepository.findAll());
        
        
    }
	@GetMapping("/{id}")
    public ResponseEntity <Produtos> getById(@PathVariable Long id){
        return produtosRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
        // select* from tb_postagens where id = id
	}  @GetMapping("/jogo/{jogo}") 
       public ResponseEntity<Object> getByJogo(@PathVariable String jogo){
          return ResponseEntity.ok(produtosRepository.findAllByJogoContainingIgnoreCase(jogo));

             
	}
        
	@PostMapping
    public ResponseEntity <Produtos> postProdutos(@Valid @RequestBody Produtos produtos){

        if (categoriaRepository.existsById(produtos.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity <Produtos> putProdutos(@Valid @RequestBody Produtos produtos){

        if (produtosRepository.existsById(produtos.getId())) {

            if (categoriaRepository.existsById(produtos.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

		
	//Metódo Delete
    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)//Para trazer o status sem conteúdo
    public void deleteProdutos (@Valid @PathVariable Long id){
        Optional<Produtos> produtos = produtosRepository.findById(id); //como se fosse map

        if (produtos.isEmpty()) //checagem se é vazio...
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); //caso não encontre "joga" uma nova resposta

        produtosRepository.deleteById(id); //chamo o método postagemRepository
    }
}
	

	



	

	
