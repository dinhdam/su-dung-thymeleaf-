package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> productMap;
    private static int i = 0;

    static{
        productMap = new HashMap<>();
        productMap.put(i,new Product(i++,"luu tien", "9", "Phone"));
        productMap.put(i,new Product(i++,"Xuan Toan", "12", "Laptop"));
        productMap.put(i,new Product(i++,"Xuan Ninh", "9", "TV"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        product.setId(getCurrentID());
        productMap.put(i++,product);
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        String name = product.getName();
        String price = product.getPrice();
        String desc = product.getDescription();
        if(!name.equals(productMap.get(id).getName())){
            productMap.get(id).setName(name);
        }
        if(!price.equals(productMap.get(id).getPrice())){
            if(!price.contains("$")){
                price+="$";
            }
            productMap.get(id).setPrice(price);
        }
        if(!desc.equals(productMap.get(id).getDescription())){
            productMap.get(id).setDescription(desc);
        }
    }

    @Override
    public void remove(int id) {
        productMap.remove(id);
    }

    public int getCurrentID(){
        return i;
    }
}
