package ru.skillfactory.ibankApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.ibankApi.entity.Operations;

import java.util.Date;
import java.util.List;


public interface OperationsRepository extends JpaRepository<Operations, Long> {

    List<Operations> findAllByUserId(long userId);
    List<Operations> findAllByUserIdAndDateAfter(long userId, Date startDate);
    List<Operations> findAllByUserIdAndDateBetween(long userId, Date startDate, Date endDate);
}
