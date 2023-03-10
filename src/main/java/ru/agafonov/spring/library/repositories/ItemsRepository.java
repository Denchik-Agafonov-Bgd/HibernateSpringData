package ru.agafonov.spring.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.agafonov.spring.library.models.Item;
import ru.agafonov.spring.library.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemName(String ItemName);

    List<Item> findByOwner(Person owner);


}
