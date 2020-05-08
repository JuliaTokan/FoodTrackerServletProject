package ua.external.servlets.service;

import ua.external.servlets.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface IActivityService {
    Optional<Activity> findActivityById(Long id) throws ServiceException;

    List<Activity> findAllActivities() throws ServiceException;
}
