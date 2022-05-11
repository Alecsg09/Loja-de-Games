package com.generation.loja_de_games.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity //Equivalente a create table.
@Table(name = "tb_produtos") // Para definir autoincremento. 
public class Produtos {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Obrigatorio")
	@Size(min  = 5, max = 100, message = "Para cadastrar é necessario o titulo do jogo.")
	private String jogo;
	
	@NotNull(message = "Obrigatorio")
	@Size(min  = 5, max = 100, message = "Coloque quem desenvolveu o jogo.")
	private String desenvolvedora;
	
	@NotNull(message = "Obrigatorio")
	@Size(min  = 5, max = 100, message = "Para que plataforma ele foi lançado.")
	private String plataforma;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Preço é obrigatório!")
    @Positive(message = "O preço deve ser maior do que zero!")
	private BigDecimal preco; 
	
	@NotNull(message = "Obrigatorio")
	@Size( max = 100, message = "Qual genero o jogo se encontra.")
	private String genero;
	
	@NotNull(message = "Obrigatorio")
	@Size(message = "Coloque uma foto de produto.")
	private String foto;
	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJogo() {
		return jogo;
	}

	public void setJogo(String jogo) {
		this.jogo = jogo;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}




	
	
	
	
	
	
	
	
	
}
