package com.trelloiii.dao;

import com.trelloiii.entities.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDao extends CrudRepository<Request,Integer> {
}
