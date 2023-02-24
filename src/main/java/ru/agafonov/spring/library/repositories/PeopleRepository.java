package ru.agafonov.spring.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agafonov.spring.library.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findByFio(String name);

    List<Person> findByFioOrderByBirthday(String name);

    List<Person> findByEmail(String name);

    List<Person> findByFioStartingWith(String startWith);

    List<Person> findByFioOrEmail(String name, String email);
}
