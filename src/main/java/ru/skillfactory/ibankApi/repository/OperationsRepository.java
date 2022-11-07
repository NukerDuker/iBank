package ru.skillfactory.ibankApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillfactory.ibankApi.entity.Operations;


public interface OperationsRepository extends JpaRepository<Operations, Long> {

}
