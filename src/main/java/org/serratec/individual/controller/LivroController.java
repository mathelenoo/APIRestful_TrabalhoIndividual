package org.serratec.individual.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.individual.model.Livro;
import org.serratec.individual.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController // controlador REST
@RequestMapping("/livros") // mapear solicitações HTTP 
@Validated // Bean Validation
public class LivroController {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping //listar todos os livros
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.ok(livroRepository.findAll());
	}
	
	@GetMapping("/{id}") //listar livro pelo id
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		Optional<Livro> livroOpt = livroRepository.findById(id);
		if (livroOpt.isPresent()) {
			return ResponseEntity.ok(livroOpt.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping //criar um novo registro
    @ResponseStatus(HttpStatus.CREATED)
    public Livro createLivro(@Valid @RequestBody Livro livro) {
        return livroRepository.save(livro);
    }
	
	@PutMapping("/{id}") //atualizar um registro presente
	public ResponseEntity<Livro> atualizar(@PathVariable Long id,@Valid @RequestBody Livro livro) {
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livro.setId(id);
		livro = livroRepository.save(livro);
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/{id}") //deletar do banco de dados pelo Postman
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}


}
