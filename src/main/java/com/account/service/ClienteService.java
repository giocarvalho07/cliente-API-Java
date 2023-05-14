package com.account.service;

import com.account.domain.Cliente;
import com.account.exception.ClienteNotFoundException;
import com.account.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create(Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ClienteNotFoundException(
                "Cliente não encontrado - ID: " + id + Cliente.class.getName()
        ));
    }

    public Cliente update(Long id, Cliente cliente){
        Cliente updateCliente = findById(id);
        updateCliente.setTipo(cliente.getTipo());
        updateCliente.setTipoTransacao(cliente.getTipoTransacao());
        updateCliente.setValor(cliente.getValor());
        updateCliente.setData(cliente.getData());
        return clienteRepository.save(updateCliente);
    }

    public void delete(Long id){
        findById(id);
        clienteRepository.deleteById(id);
    }



}
