package com.trelloiii.dao;

import com.trelloiii.entities.Pattern;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatternDao extends CrudRepository<Pattern,Integer> {
}
