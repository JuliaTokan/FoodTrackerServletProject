package ua.external.servlets.service;

import ua.external.servlets.entity.EatPeriod;

import java.util.List;
import java.util.Optional;

public interface IEatPeriodService {
    Optional<EatPeriod> findEatPeriodById(Long id) throws ServiceException;

    List<EatPeriod> findAllEatPeriods() throws ServiceException;
}
