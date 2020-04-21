package com.trelloiii.dao;

import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Pattern;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Интерфейс репозитория шаблонов. Необходим для извлечения сущностей типа шаблон из базы данных
 * @see Pattern
 * @see CrudRepository
 * @author trelloiii
 */
@Repository
public interface PatternDao extends CrudRepository<Pattern,Integer> {
}
