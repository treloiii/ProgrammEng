package com.trelloiii.dao;

import com.trelloiii.entities.Implementer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс репозитория исполнителей. Необходим для извлечения сущностей типа исполнитель из базы данных
 * @see Implementer
 * @see CrudRepository
 * @author trelloiii
 */
@Repository
public interface ImplementerDao extends CrudRepository<Implementer,Integer> {
}
