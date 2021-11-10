package com.example.sweater.repos;

import com.example.sweater.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item,Long> {
    List<Item> findByTag(String tag);
}
