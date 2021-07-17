package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.dto.CreateItemCommand;
import com.safecornerscoffee.mocha.dto.UpdateItemCommand;
import com.safecornerscoffee.mocha.mapper.ItemMapper;
import com.safecornerscoffee.mocha.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;


    public List<Item> getItems() {
        return null;
    }

    public Item getItemById(Long itemId) {
        return null;
    }

    @Transactional
    public Long createItem(CreateItemCommand command) {
        return null;
    }

    @Transactional
    public void updateItem(UpdateItemCommand command) {

    }

    @Transactional
    public void deleteItem(Long itemId) {

    }


}
