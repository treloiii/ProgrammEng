package com.trelloiii.dao;

import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Интерфейс репозитория заявок. Необходим для извлечения сущностей типа заявка из базы данных
 * @see Request
 * @see CrudRepository
 * @author trelloiii
 */
@Repository
public interface RequestDao extends CrudRepository<Request,Integer> {
}
