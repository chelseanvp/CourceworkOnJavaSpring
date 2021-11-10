package com.example.sweater.repos;

import com.example.sweater.domain.Item;
import com.example.sweater.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item,Long> {
    List<Item> findByTag(String tag);
    List<Item> findByAuthor(User user);
    Item findById(Integer id);

    @Override
    void deleteById(Long aLong);
}
