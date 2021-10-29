package own.service;


import own.pojo.Person;

public interface PersonService {
    Person save(Person person);
    Person getPersonInfor(Long id);
}
