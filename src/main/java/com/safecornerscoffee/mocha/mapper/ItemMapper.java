package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {

    List<Item> getItemById(Item item);
    List<Item> getItems();

    Long insertItem(Item item);
    void updateItem(Item item);
    void deleteItem(Item item);
}
