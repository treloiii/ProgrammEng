package com.trelloiii;

import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Pattern;
import com.trelloiii.entities.Request;
import com.trelloiii.entities.RequestStatus;
import com.trelloiii.services.ImplementerService;
import com.trelloiii.services.PatternService;
import com.trelloiii.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер для обработки HTTP запросов
 * @author trelloiii
 */
@RestController
@CrossOrigin
public class Controller {
    /**
     * Сервис отвечающий за логику работы с сотрудниками
     * @see ImplementerService
     */
    @Autowired
    ImplementerService implementerService;
    /**
     * Сервис отвечающий за логику работы с шаблонами
     * @see PatternService
     */
    @Autowired
    PatternService patternService;
    /**
     * Сервис отвечающий за логику работы с заявками
     * @see RequestService
     */
    @Autowired
    RequestService requestService;

    /**
     * @param id - id шаблона
     * @see PatternService#getById(int)
     * @return возвращает найденный шаблон
     */
    @RequestMapping("/get/pattern")
    public Pattern getPatternById(@RequestParam("id") int id){
        return patternService.getById(id);
    }

    /**
     * @see PatternService#getAll()
     * @return возвращает список всех шаблонов
     */
    @RequestMapping("/get/pattern/all")
    public List<Pattern> getPatterns(){
        return patternService.getAll();
    }

    /**
     * @see RequestService#getById(int)
     * @param id - id заявки
     * @return возвращает заявку
     */
    @RequestMapping("/get/request")
    public Request getRequestById(@RequestParam("id") int id){
        return requestService.getById(id);
    }

    /**
     * @see RequestService#getAll()
     * @return возвращает список всех заявок
     */
    @RequestMapping("/get/request/all")
    public List<Request> getRequests(){
        return requestService.getAll();
    }

    /**
     * @param id - id исполнителя
     * @see ImplementerService#getById(int)
     * @return возвращает исполнителя
     */
    @RequestMapping("/get/implementer")
    public Implementer getImplementerById(@RequestParam("id") int id){
        return implementerService.getById(id);
    }

    /**
     * @return возвращает список всех исполнителей
     * @see ImplementerService#getAll()
     */
    @RequestMapping("/get/implementer/all")
    public List<Implementer> getImplementers(){
        return implementerService.getAll();
    }

    /**
     * Метод для создания новой заявки
     * @param patternId - id шаблона по которому должна быть создана заявка
     * @return возвращает либо сообщение об ошибке
     * либо строку с информацией об успешном сохранении заявки
     */
    @RequestMapping("/new/request")
    public String newRequest(@RequestBody int patternId){
        try {
            requestService.newRequest(patternService.getById(patternId),implementerService);
            return "request has been saved";
        }
        catch (IllegalStateException e){
            return e.getMessage();
        }
    }

    /**
     * @param implementer - исполнитель, автоматически преобразуется из Json в Java класс
     *                    Json должен быть структуры аналогичной Java классу
     * @see Implementer
     * @see ImplementerService#newImplementer(Implementer)
     * @return возвращает строку с информацией об успешном сохранении исполнителя
     */
    @RequestMapping("/new/implementer")
    public String newImplementer(@RequestBody Implementer implementer){
        implementerService.newImplementer(implementer);
        return "implementer has been saved";
    }

    /**
     * @param pattern - шаблон, автоматически преобразуется из Json в Java класс
     *                          Json должен быть структуры аналогичной Java классу
     * @see Pattern
     * @see PatternService#newPattern(Pattern)
     * @return возвращает строку с информацией об успешном сохранении шаблона
     */
    @RequestMapping("/new/pattern")
    public String newPattern(@RequestBody Pattern pattern){
        patternService.newPattern(pattern);
        return "pattern has been saved";
    }

    /**
     * @see RequestService#updateStatus(RequestStatus, int)
     * @param requestStatus - статус на который надо поменять текущий статус заявки
     * @see RequestStatus
     * @param requestId - id заявки у которой нужно сменить статус
     * @return возвращает информацию об успешной обновлении заявки либо сообщение об ошибке
     */
    @RequestMapping("/set/requestStatus/{request_id}")
    public String setRequestStatus(@RequestBody String requestStatus,@PathVariable(name = "request_id") int requestId){
        try {
            requestService.updateStatus(RequestStatus.valueOf(requestStatus), requestId);
            return "request updated";
        }
        catch (IllegalArgumentException e){
            return  "wrong id";
        }
    }



}
