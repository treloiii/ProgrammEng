package com.trelloiii;

import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import com.trelloiii.entities.Implementer;
import com.trelloiii.entities.Pattern;
import com.trelloiii.entities.Request;
import com.trelloiii.entities.RequestStatus;
import com.trelloiii.services.ImplementerService;
import com.trelloiii.services.PatternService;
import com.trelloiii.services.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class PhotoStudioApplicationTests {
	@Autowired
	ImplementerService implementerService;
	@Autowired
	PatternService patternService;
	@Autowired
	RequestService requestService;
	/*
	Тесты на добавление и удаление записей в таблицу исполнителей через сервисы
	 */
	@Test
	void testAddDeleteReadImplementerToDatabase() {
		Implementer implementer=new Implementer();
		implementer.setAge(1);
		implementer.setEmail("e");
		implementer.setExperience(1);
		implementer.setMinimalPrice(1);
		implementer.setPhone("1");
		implementer.setName("test_case");
		implementer.setSurname("test_case");
		int sizeBefore=implementerService.getAll().size();

		implementerService.newImplementer(implementer);
		List<Implementer> implementers=implementerService.getAll();
		Assertions.assertEquals(implementer.getName(),implementers.get(implementers.size()-1).getName());
		Assertions.assertEquals(sizeBefore,implementers.size()-1);

		implementerService.delete(implementer);
		Assertions.assertEquals(sizeBefore,implementerService.getAll().size());
	}
	/*
        Тесты на добавление и удаление записей в таблицу шаблоны через сервисы
    */
	@Test
	void testAddDeleteReadPatternToDatabase(){
		int sizeBefore=patternService.getAll().size();
		Pattern p=new Pattern();
		p.setPrice(1);
		p.setName("test_case");
		p.setDescription("test_case");
		patternService.newPattern(p);
		List<Pattern> patterns=patternService.getAll();
		Assertions.assertEquals(p.getName(),patterns.get(patterns.size()-1).getName());
		Assertions.assertEquals(sizeBefore,patterns.size()-1);

		patternService.delete(p);
		Assertions.assertEquals(sizeBefore,patternService.getAll().size());
	}
	/*
		Тесты на обновление статуса заявки
	 */
	@Test
	void testUpdateRequestStatus(){
		Request request=requestService.getAll().get(0);
		RequestStatus initial=request.getStatus();
		RequestStatus[] statuses=new RequestStatus[]{RequestStatus.DONE,
				RequestStatus.INPROGRESS,RequestStatus.PROCESSING};
		for(RequestStatus status:statuses){
			requestService.updateStatus(status,request.getId());
			Assertions.assertEquals(status.name(),requestService.getAll().get(0).getStatus().name());
		}
		requestService.updateStatus(initial,request.getId());
		Assertions.assertEquals(initial.name(),requestService.getAll().get(0).getStatus().name());
	}


}
