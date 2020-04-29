package com.trelloiii.services;

import com.trelloiii.dao.ImplementerDao;
import com.trelloiii.dao.PatternDao;
import com.trelloiii.entities.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс определяющий логику работы с шаблонами
 * @author trelloiii
 */
@Service
public class PatternService {
    /**
     * Репозиторий шаблонов
     * @see PatternDao
     */
    @Autowired
    PatternDao dao;
    /**
     * Метод для получения шаблона по id
     * @param id - id шаблона
     * @return возвращает найденный шаблон
     */
    public Pattern getById(int id){
        return dao.findById(id).orElse(null);
    }
    /**
     * Метод для получения всех шаблонов
     * @return возвращает список всех шаблонов
     */
    public List<Pattern> getAll(){
        List<Pattern> result=new ArrayList<>();
        dao.findAll().forEach(result::add);
        return result;
    }
    public void delete(Pattern pattern){
        dao.delete(pattern);
    }
    /**
     * Метод для добавления нового шаблона
     * @param pattern - шаблон, автоматически преобразуется из Json в Java класс
     *                          Json должен быть структуры аналогичной Java классу
     * @see Pattern
     */
    public void newPattern(Pattern pattern) {
        dao.save(pattern);
    }
}
