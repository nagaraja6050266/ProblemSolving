package org.inr.tutorial.messenger.dao;

import org.inr.tutorial.messenger.database.Database;
import org.inr.tutorial.messenger.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsDao {

    public Item addItem(Item item) {
        item.setId(Database.getItems().size()+1);
        Database.getItems().put(item.getId(),item);
        return item;
    }

    public Item getItemById(int id){
        if(Database.getItems().containsKey(id)){
            return Database.getItems().get(id);
        }
        return null;
    }

    public List<Item> getAllItems(){
        return new ArrayList<>(Database.getItems().values());
    }

    public Item editItem(int id,Item item){
        if(Database.getItems().containsKey(id)){
            item.setId(id);
            Database.getItems().put(id,item);
            return item;
        }
        return null;
    }

    public Item deleteItem(int id){
        if(Database.getItems().containsKey(id)){
            return Database.getItems().remove(id);
        }
        return null;
    }

}
