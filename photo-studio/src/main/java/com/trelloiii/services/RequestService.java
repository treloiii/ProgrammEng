package com.trelloiii.services;

import com.trelloiii.ImplementerPicker;
import com.trelloiii.dao.ImplementerDao;
import com.trelloiii.dao.RequestDao;
import com.trelloiii.entities.Pattern;
import com.trelloiii.entities.Request;
import com.trelloiii.entities.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Класс определяющий логику работы с шаблонами
 * @author trelloiii
 */
@Service
public class RequestService {
    /**
     * Репозиторий заявок
     * @see RequestDao
     */
    @Autowired
    RequestDao dao;
    /**
     * Метод для получения заявки по id
     * @param id - id заявки
     * @return возвращает заявку
     */
    public Request getById(int id){
        return dao.findById(id).orElse(null);
    }
    /**
     * Метод для получения всех заявок
     * @return возвращает список всех заявок
     */
    public List<Request> getAll() {
        List<Request> res=new ArrayList<>();
        dao.findAll().forEach(res::add);
        return res;
    }

    /**
     * Метод для создания новой заявки
     * @param pattern - Шаблон который будет фигурировать в заявке
     * @param service - Сервис исполнителей
     * @see Pattern
     * @see ImplementerService
     * @throws IllegalStateException выбрасывается из метода pickImplementer()
     * @see ImplementerPicker#pickImplementer()
     */
    public void newRequest(Pattern pattern,ImplementerService service) throws IllegalStateException{
        Request request=new Request();
        request.setPattern(pattern);
        request.setCreationTime(new Date());
        request.setStatus(RequestStatus.PROCESSING);
        ImplementerPicker picker=new ImplementerPicker(request,service);
        picker.pickImplementer();
        dao.save(request);
    }
    /**
     * Метод для изменения статуса заявки
     * @param requestStatus - статус на который надо поменять текущий статус заявки
     * @see RequestStatus
     * @param requestId - id заявки у которой нужно сменить статус
     * @throws IllegalArgumentException выбрасывается если запроса с данным requestId не существует
     */
    public void updateStatus(RequestStatus requestStatus, int requestId) throws IllegalArgumentException {
        Request request=dao.findById(requestId).orElse(null);
        if (request != null) {
            request.setStatus(requestStatus);
            dao.save(request);
        }
        else{
            throw new IllegalArgumentException("request doesn't exists");
        }
    }
}
