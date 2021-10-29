package own.controller;

import own.pojo.Person;
import own.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/save")
    public Person getSavePerson(Person person) {
        Person p = personService.save(person);
        logger.info("调用保存save方法中---------");
        return p;
    }

    @GetMapping("/get/{id}")
    public Person getPersonInfo(@PathVariable Long id) {
        Person p = personService.getPersonInfor(id);
        logger.info("调用查询get方法中---------");
        return p;
    }


}
