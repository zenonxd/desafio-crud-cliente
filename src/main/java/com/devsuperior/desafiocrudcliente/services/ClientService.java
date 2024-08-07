package com.devsuperior.desafiocrudcliente.services;

import com.devsuperior.desafiocrudcliente.dto.ClientDTO;
import com.devsuperior.desafiocrudcliente.entities.Client;
import com.devsuperior.desafiocrudcliente.repositories.ClientRepository;
import com.devsuperior.desafiocrudcliente.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado."));

        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findALl (Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);

        return clients.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();

        copyDtoToEntity(dto, entity);

        entity = clientRepository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {

        Client entity = clientRepository.getReferenceById(id);

        copyDtoToEntity(dto, entity);

        entity = clientRepository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public boolean delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
    }


}
