package com.roaddb.paper.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.roaddb.paper.model.Category;
import com.roaddb.paper.repository.CategoryRepository;

@Component
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryRepository categoryRepository;

    public JSONObject addCategory(Category category) {
        Category res = categoryRepository.save(category);
        if (res != null) {
            JSONObject jsonObject = new JSONObject(res);
            return jsonObject;
        }
        return null;
    }


    public JSONArray addCategorys(List<Category> categorys) {
        JSONArray jsonArray = new JSONArray();
        for (Category category : categorys) {
            JSONObject jsonObject = addCategory(category);
            if (jsonObject != null) {
                jsonArray.put(jsonObject);
            } else {
                System.out.println("save category error: " + category.toString());
                return null;
            }
        }
        return jsonArray;
    }



}
