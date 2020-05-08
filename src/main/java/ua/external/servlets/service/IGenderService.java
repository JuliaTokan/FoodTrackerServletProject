package ua.external.servlets.service;

import ua.external.servlets.entity.Gender;

import java.util.List;
import java.util.Optional;

public interface IGenderService {
    Optional<Gender> findGenderById(Long id) throws ServiceException;

    List<Gender> findAllGenders() throws ServiceException;
}
