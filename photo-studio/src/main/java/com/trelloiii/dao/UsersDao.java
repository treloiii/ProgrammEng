package com.trelloiii.dao;

import com.trelloiii.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersDao extends CrudRepository<Users,Integer> {
    Users findByLogin(String login);
}
