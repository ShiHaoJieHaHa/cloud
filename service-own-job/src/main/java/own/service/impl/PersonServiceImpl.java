package own.service.impl;

import own.pojo.Person;
import own.repository.PersonRepository;
import own.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired
    PersonRepository personRepository;

    @Override
    @Cacheable(cacheNames = "PersonServiceImplSave", keyGenerator = "keyGenerator",cacheManager = "cacheManager")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        logger.info("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    //@Cacheable(cacheNames = "PersonServiceImplgetPerson", keyGenerator = "keyGenerator",cacheManager = "cacheManager")
    public Person getPersonInfor(Long id) {
        if (personRepository.findById(id).isPresent()){
            Person p = personRepository.findById(id).get();
            //logger.info("为id、key为:" + p.getId() + "数据做了缓存");
            logger.info("调用getPersonInfor方法");
            return p;
        }else{
             logger.error("没有查询到想要的结果");
           return null;
        }

    }
}
