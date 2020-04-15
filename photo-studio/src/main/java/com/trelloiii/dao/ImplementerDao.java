package com.trelloiii.dao;

import com.trelloiii.entities.Implementer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImplementerDao extends CrudRepository<Implementer,Integer> {
}
