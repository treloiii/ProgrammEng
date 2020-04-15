package com.trelloiii.services;

import com.trelloiii.dao.ImplementerDao;
import com.trelloiii.entities.Implementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplementerService {
    @Autowired
    private ImplementerDao dao;

    public Implementer getById(int id){
        return dao.findById(id).orElse(null);
    }

    public List<Implementer> getAll() {
        List<Implementer> res=new ArrayList<>();
        dao.findAll().forEach(res::add);
        return res;
    }

    public void newImplementer(Implementer implementer) {
        dao.save(implementer);
    }
}
