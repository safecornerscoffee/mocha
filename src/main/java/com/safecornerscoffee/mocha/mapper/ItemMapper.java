package com.safecornerscoffee.mocha.mapper;

import com.safecornerscoffee.mocha.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemMapper {

    Item getItemById(Long itemId);
    List<Item> getItems();

    void insertItem(Item item);
    void updateItem(Item item);
    void deleteItem(Long itemId);
}
