package com.application.shophop.services;

import com.application.shophop.model.Item;
import com.application.shophop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService  {


    @Autowired
    ItemRepository itemRepository;

    public  Item saveaitem(Item item){
       return itemRepository.save(item);
    }

}
