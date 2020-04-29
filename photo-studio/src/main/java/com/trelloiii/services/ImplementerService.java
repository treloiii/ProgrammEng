package com.trelloiii.services;

import com.trelloiii.dao.ImplementerDao;
import com.trelloiii.entities.Implementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * Класс определяющий логику работы с исполнителем
 * @author trelloiii
 */
@Service
public class ImplementerService {
    /**
     * Репозиторий исполнителей
     * @see ImplementerDao
     */
    @Autowired
    private ImplementerDao dao;

    /**
     * Метод получения исполнителя по id
     * @param id - id исполнителя
     * @return возвращает исполнителя , либо null
     */
    public Implementer getById(int id){
        return dao.findById(id).orElse(null);
    }
    /**
     * Метод для получения всех исполнителей
     * @return возвращает список всех исполнителей
     */
    public List<Implementer> getAll() {
        List<Implementer> res=new ArrayList<>();
        dao.findAll().forEach(res::add);
        return res;
    }
    public void delete(Implementer implementer){
        dao.delete(implementer);
    }
    /**
     * Метод для добавления нового исполнителя
     * @param implementer - исполнитель, автоматически преобразуется из Json в Java класс
     *                    Json должен быть структуры аналогичной Java классу
     * @see Implementer
     */
    public void newImplementer(Implementer implementer) {
        dao.save(implementer);
    }
}
