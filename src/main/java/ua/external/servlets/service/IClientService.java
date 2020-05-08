package ua.external.servlets.service;

import ua.external.servlets.entity.Client;

import java.util.Optional;

public interface IClientService {
    boolean createClient(Client client) throws ServiceException;

    boolean updateClient(Client client) throws ServiceException;

    Optional<Client> findClientById(Long id) throws ServiceException;
}
