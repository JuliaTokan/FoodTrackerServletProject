package ua.external.servlets.service;

import ua.external.servlets.entity.Gender;

import java.util.List;
import java.util.Optional;

/**
 * The interface implements behavior for GenderService.
 */
public interface IGenderService {
    Optional<Gender> findGenderById(Long id) throws ServiceException;

    List<Gender> findAllGenders() throws ServiceException;
}
