package com.trelloiii;

import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Request;
import com.trelloiii.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для выбора исполнителя при подаче заявки
 * @author trelloiii
 */
public class ImplementerPicker {
    /**
     * Сервис исполнителей
     * @see ImplementerService
     */
    private ImplementerService implementerService;
    /**
     * Заявка
     * @see Request
     */
    private Request request;

    /**
     * Конструктор
     * @param request - заявка
     * @param implementerService - сервис исполнителей
     */
    public ImplementerPicker(Request request,ImplementerService implementerService) {
        this.request = request;
        this.implementerService=implementerService;
    }

    /**
     * Метод выбирает исполнителя.
     * При выборе учитываются параметры опыта работы, цены заявки и минимальной оплаты исполнителя.
     * Выбирается исполнитель с наилучшим соотношением цены и опыта работы
     * @throws IllegalStateException выбрасывается если исполнитель не найден
     */
    public void pickImplementer() throws IllegalStateException{
        List<Implementer> candidates=sortCandidates();
        System.out.println(candidates.size());
        if(candidates.size()>=2) {
            Implementer one = candidates.get(0);
            Implementer two = candidates.get(1);
            if(one.getMinimalPrice()<two.getMinimalPrice()){
                request.setImplementer(one);
            }
            else if(one.getMinimalPrice()==two.getMinimalPrice()){
                if(one.getExperience()>two.getExperience())
                    request.setImplementer(one);
                else if(one.getExperience()<two.getExperience())
                    request.setImplementer(two);
                else{
                    Random r=new Random();
                    int rand=r.nextInt(1);
                    request.setImplementer(candidates.get(rand));
                }
            }
            else{
                double expDif=1-(double)two.getExperience()/one.getExperience()*100;
                double priceDif=1-(double)two.getMinimalPrice()/one.getMinimalPrice()*100;
                if(expDif<priceDif){
                    request.setImplementer(two);
                }
                else{
                    request.setImplementer(one);
                }
            }
        }
        else if(candidates.size()==1){
            request.setImplementer(candidates.get(0));
        }
        else{
            throw new IllegalStateException("no candidates available");
        }
    }

    /**
     * Отбирает из всех исполнителей тех что имеют минимальный уровень оплаты ниже либо равным цене заявки.
     * Сортирует полученный список по возрастанию опыта работы исполнителей
     * @return возвращает список исполнителей
     */
    private List<Implementer> sortCandidates(){
        List<Implementer> implementers=implementerService.getAll();
        double requestPrice=request.getPattern().getPrice();
        System.out.println(requestPrice);
        implementers=implementers.stream().filter(i->i.getMinimalPrice()<=requestPrice).collect(Collectors.toList());

        implementers.sort(Comparator.comparingInt(Implementer::getExperience));
        return implementers;
    }
}
