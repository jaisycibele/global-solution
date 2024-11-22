package br.com.fiap.global_solution.controller;

import br.com.fiap.global_solution.dto.UsuarioRequest;
import br.com.fiap.global_solution.dto.UsuarioResponse;
import br.com.fiap.global_solution.model.Usuario;
import br.com.fiap.global_solution.repository.UsuarioRepository;
import br.com.fiap.global_solution.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioService.requestToUsuario(usuarioRequest);
        Usuario usuarioPersistido = usuarioRepository.save(usuario);
        UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioPersistido);
        return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> read() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UsuarioResponse> usuarioResponses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuario);
            usuarioResponse.setLink(linkTo(methodOn(UsuarioController.class).read()).withSelfRel());
            usuarioResponses.add(usuarioResponse);
        }
        return new ResponseEntity<>(usuarioResponses, HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> read(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuario.get());
            usuarioResponse.setLink(linkTo(methodOn(UsuarioController.class).read()).withRel("Lista de usuarios"));
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> update(@PathVariable Long idUsuario, @Valid @RequestBody UsuarioRequest usuarioRequest) {
        Optional<Usuario> usuarioPersistido = usuarioRepository.findById(idUsuario);
        if (usuarioPersistido.isPresent()) {
            Usuario usuario = usuarioService.requestToUsuario(usuarioRequest);
            usuario.setIdUsuario(idUsuario);
            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            UsuarioResponse usuarioResponse = usuarioService.usuarioToResponse(usuarioAtualizado);
            return new ResponseEntity<>(usuarioResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
