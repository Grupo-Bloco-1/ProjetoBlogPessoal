package org.generation.blogPessoal.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){

		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "caique ramos", "caique@email.com.br", "153780"));
		
		usuarioRepository.save(new Usuario(0L, "giovanna buonadulce", "giovanna@email.com.br", "153780"));
		
		usuarioRepository.save(new Usuario(0L, "larissa cardoso","larissa@email.com.br", "153780"));

        usuarioRepository.save(new Usuario(0L, "pedro sampaio", "pedro@email.com.br", "153780"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario especifico para testes")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("samuel@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("samuel@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios para testes")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Samuel Santos"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Larissa Santos"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Douglas Santos"));
	}

}
