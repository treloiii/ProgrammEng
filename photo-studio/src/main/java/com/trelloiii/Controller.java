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

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    ImplementerService implementerService;
    @Autowired
    PatternService patternService;
    @Autowired
    RequestService requestService;

    @RequestMapping("/get/pattern")
    public Pattern getPatternById(@RequestParam("id") int id){
        return patternService.getById(id);
    }
    @RequestMapping("/get/pattern/all")
    public List<Pattern> getPatterns(){
        return patternService.getAll();
    }

    @RequestMapping("/get/request")
    public Request getRequestById(@RequestParam("id") int id){
        return requestService.getById(id);
    }
    @RequestMapping("/get/request/all")
    public List<Request> getRequests(){
        return requestService.getAll();
    }

    @RequestMapping("/get/implementer")
    public Implementer getImplementerById(@RequestParam("id") int id){
        return implementerService.getById(id);
    }
    @RequestMapping("/get/implementer/all")
    public List<Implementer> getImplementers(){
        return implementerService.getAll();
    }

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
    @RequestMapping("/new/implementer")
    public String newImplementer(@RequestBody Implementer implementer){
        implementerService.newImplementer(implementer);
        return "implementer has been saved";
    }
    @RequestMapping("/new/pattern")
    public String newPattern(@RequestBody Pattern pattern){
        patternService.newPattern(pattern);
        return "pattern has been saved";
    }
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
