package com.albino.tecnologia.osworks.controller;

import com.albino.tecnologia.osworks.controller.dto.EnderecoDTO;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.service.impl.EnderecoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoServiceImpl enderecoService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Endereco> encontrarpeloEndereco(@PathVariable Long id) {

        Endereco endereco = enderecoService.encontrarPeloEndereco(id);

        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Page<Endereco>> PagearTodosEnderecos(Pageable pageable) {

        Page<Endereco> enderecoList = enderecoService.listarTodosEnderecos(pageable);

        return ResponseEntity.ok(enderecoList);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Endereco> criarEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) {

        Endereco enderecoCriado = enderecoService.criarEndereco(enderecoDTO);

        return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {

        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDTO);

        return ResponseEntity.ok(enderecoAtualizado);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCEIRO')")
    public ResponseEntity<Void> inativarEndereco(@PathVariable Long id) {

        enderecoService.inativarEndereco(id);

        return ResponseEntity.noContent().build();
    }
}
