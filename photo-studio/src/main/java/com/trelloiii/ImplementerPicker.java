package com.trelloiii;

import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Request;
import com.trelloiii.services.ImplementerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class ImplementerPicker {

    private ImplementerService implementerService;

    private Request request;
    public ImplementerPicker(Request request,ImplementerService implementerService) {
        this.request = request;
        this.implementerService=implementerService;
    }
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
    private List<Implementer> sortCandidates(){
        List<Implementer> implementers=implementerService.getAll();
        double requestPrice=request.getPattern().getPrice();
        System.out.println(requestPrice);
        implementers=implementers.stream().filter(i->i.getMinimalPrice()<=requestPrice).collect(Collectors.toList());

        implementers.sort(Comparator.comparingInt(Implementer::getExperience));
        return implementers;
    }
}
