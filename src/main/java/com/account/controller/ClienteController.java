package com.account.controller;

import com.account.domain.Cliente;
import com.account.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> listar(){
        List<Cliente> clienteList = clienteService.findAll();
        return  ResponseEntity.ok().body(clienteList);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> listarId(@PathVariable Long id){
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente){
        Cliente createCliente = clienteService.create(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                createCliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente updateCliente = clienteService.update(id, cliente);
        return ResponseEntity.ok().body(updateCliente);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
