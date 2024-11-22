package br.com.fiap.global_solution.controller;

import br.com.fiap.global_solution.dto.PrecoEnergiaRequest;
import br.com.fiap.global_solution.dto.PrecoEnergiaResponse;
import br.com.fiap.global_solution.model.PrecoEnergia;
import br.com.fiap.global_solution.repository.PrecoEnergiaRepository;
import br.com.fiap.global_solution.service.PrecoEnergiaService;
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
@RequestMapping(value = "api/precoEnergia")
public class PrecoEnergiaController {

    @Autowired
    private PrecoEnergiaRepository precoEnergiaRepository;

    @Autowired
    private PrecoEnergiaService precoEnergiaService;

    @PostMapping
    public ResponseEntity<PrecoEnergiaResponse> create(@Valid @RequestBody PrecoEnergiaRequest precoEnergiaRequest) {
        PrecoEnergia precoEnergia = precoEnergiaService.requestToPrecoEnergia(precoEnergiaRequest);
        PrecoEnergia precoEnergiaPersistido = precoEnergiaRepository.save(precoEnergia);
        PrecoEnergiaResponse precoEnergiaResponse = precoEnergiaService.precoEnergiaToResponse(precoEnergiaPersistido);
        return new ResponseEntity<>(precoEnergiaResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PrecoEnergiaResponse>> read() {
        List<PrecoEnergia> precosEnergia = precoEnergiaRepository.findAll();
        if (precosEnergia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<PrecoEnergiaResponse> precoEnergiaResponses = new ArrayList<>();
        for (PrecoEnergia precoEnergia : precosEnergia) {
            PrecoEnergiaResponse precoEnergiaResponse = precoEnergiaService.precoEnergiaToResponse(precoEnergia);
            precoEnergiaResponse.setLink(linkTo(methodOn(PrecoEnergiaController.class).read()).withSelfRel());
            precoEnergiaResponses.add(precoEnergiaResponse);
        }
        return new ResponseEntity<>(precoEnergiaResponses, HttpStatus.OK);
    }

    @GetMapping("/{idPrecoEnergia}")
    public ResponseEntity<PrecoEnergiaResponse> read(@PathVariable Long idPrecoEnergia) {
        Optional<PrecoEnergia> precoEnergia = precoEnergiaRepository.findById(idPrecoEnergia);
        if (precoEnergia.isPresent()) {
            PrecoEnergiaResponse precoEnergiaResponse = precoEnergiaService.precoEnergiaToResponse(precoEnergia.get());
            precoEnergiaResponse.setLink(linkTo(methodOn(PrecoEnergiaController.class).read()).withRel("Lista de preços de energia"));
            return new ResponseEntity<>(precoEnergiaResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{idPrecoEnergia}")
    public ResponseEntity<PrecoEnergiaResponse> update(@PathVariable Long idPrecoEnergia, @Valid @RequestBody PrecoEnergiaRequest precoEnergiaRequest) {
        Optional<PrecoEnergia> precoEnergiaPersistido = precoEnergiaRepository.findById(idPrecoEnergia);
        if (precoEnergiaPersistido.isPresent()) {
            PrecoEnergia precoEnergia = precoEnergiaService.requestToPrecoEnergia(precoEnergiaRequest);
            precoEnergia.setIdPrecoEnergia(idPrecoEnergia);
            PrecoEnergia precoEnergiaAtualizado = precoEnergiaRepository.save(precoEnergia);
            PrecoEnergiaResponse precoEnergiaResponse = precoEnergiaService.precoEnergiaToResponse(precoEnergiaAtualizado);
            return new ResponseEntity<>(precoEnergiaResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{idPrecoEnergia}")
    public ResponseEntity<Void> delete(@PathVariable Long idPrecoEnergia) {
        Optional<PrecoEnergia> precoEnergia = precoEnergiaRepository.findById(idPrecoEnergia);
        if (precoEnergia.isPresent()) {
            precoEnergiaRepository.delete(precoEnergia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
