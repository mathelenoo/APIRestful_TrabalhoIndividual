package org.serratec.individual.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public class InformacaoPublicacao {
	
	@NotBlank(message = "Preencha o campo de Autor corretamente.")
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String autor;
	
	@NotBlank(message = "Preencha a data de publicação corretamente, em formato 'YYYY-MM-DD'.")
	private LocalDate dataPublicacao;
	
	@Size(max = 20)
	@Column(nullable = false, length = 20)
	@NotBlank(message = "Preencha campo da editora corretamente.")
	private String editora;

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

}
