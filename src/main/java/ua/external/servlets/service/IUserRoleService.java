package ua.external.servlets.service;

import ua.external.servlets.entity.UserRole;

import java.util.Optional;

public interface IUserRoleService {
    Optional<UserRole> findUserRoleById(Long id) throws ServiceException;
}
