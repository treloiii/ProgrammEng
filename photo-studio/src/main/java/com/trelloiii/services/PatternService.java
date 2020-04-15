package com.trelloiii.services;

import com.trelloiii.dao.PatternDao;
import com.trelloiii.entities.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatternService {
    @Autowired
    PatternDao dao;

    public Pattern getById(int id){
        return dao.findById(id).orElse(null);
    }
    public List<Pattern> getAll(){
        List<Pattern> result=new ArrayList<>();
        dao.findAll().forEach(result::add);
        return result;
    }

    public void newPattern(Pattern pattern) {
        dao.save(pattern);
    }
}
