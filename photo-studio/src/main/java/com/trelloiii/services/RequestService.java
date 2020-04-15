package com.trelloiii.services;

import com.trelloiii.ImplementerPicker;
import com.trelloiii.dao.RequestDao;
import com.trelloiii.entities.Pattern;
import com.trelloiii.entities.Request;
import com.trelloiii.entities.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestDao dao;

    public Request getById(int id){
        return dao.findById(id).orElse(null);
    }

    public List<Request> getAll() {
        List<Request> res=new ArrayList<>();
        dao.findAll().forEach(res::add);
        return res;
    }

    public void newRequest(Pattern pattern,ImplementerService service) throws IllegalStateException{
        Request request=new Request();
        request.setPattern(pattern);
        request.setCreationTime(new Date());
        request.setStatus(RequestStatus.PROCESSING);
        ImplementerPicker picker=new ImplementerPicker(request,service);
        picker.pickImplementer();
        dao.save(request);
    }

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
