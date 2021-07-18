package com.safecornerscoffee.mocha.service;

import com.safecornerscoffee.mocha.domain.Item;
import com.safecornerscoffee.mocha.domain.Product;
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


    public Item getItemById(Long itemId) {
        return itemMapper.getItemById(itemId);
    }

    public List<Item> getItems() {
        return itemMapper.getItems();
    }

    @Transactional
    public Long createItem(CreateItemCommand command) {
        Product product = Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .build();
        productMapper.insertProduct(product);

        Item item = Item.builder()
                .product(product)
                .stock(command.getStock())
                .price(command.getPrice())
                .build();
        itemMapper.insertItem(item);

        return item.getId();
    }

    @Transactional
    public void updateItem(UpdateItemCommand command) {
        Item item = itemMapper.getItemById(command.getItemId());
        if (item == null) {
            throw new IllegalArgumentException("Not Found Item");
        }

        item.setPrice(command.getPrice());
        item.setStock(command.getStock());
        Product product = item.getProduct();
        product.setName(command.getName());
        product.setSlug(command.getName().replace(' ', '-'));
        product.setDescription(command.getDescription());

        productMapper.updateProduct(product);
        itemMapper.updateItem(item);
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemMapper.getItemById(itemId);
        if (item != null) {
            itemMapper.deleteItem(itemId);
            return;
        }

        throw new IllegalArgumentException("Not Found Item");
    }


}
