package com.heroku.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ProductService {

  @Autowired
  private MongoTemplate mongoTemplate; // 必須注入這個來執行 Aggregation

  public List<Map> getStockValueReport() {
    Aggregation agg = Aggregation.newAggregation(
        // 1. 過濾掉沒有庫存的
        match(Criteria.where("stock").gt(0)),
        // 2. 計算總價值並分組 (這裡以名稱分組，或是 null 代表全體)
        project("name", "price", "stock")
            .andExpression("price * stock").as("totalValue"),
        // 3. 排序
        sort(Sort.Direction.DESC, "totalValue"));

    return mongoTemplate.aggregate(agg, "products", Map.class).getMappedResults();
  }

  public List<Map> getProductsWithCategory() {
    // 定義 Lookup 步驟
    LookupOperation lookupOperation = LookupOperation.newLookup()
        .from("categories") // 從哪個集合關聯 (Right Table)
        .localField("catId") // 本地集合的欄位 (Foreign Key)
        .foreignField("_id") // 目標集合的欄位 (Primary Key)
        .as("categoryDetails"); // 輸出的陣列欄位名稱

    Aggregation agg = newAggregation(
        lookupOperation,
        // 因為 lookup 完會是陣列 [ {title: "..."} ]，通常我們會用 unwind 把它攤平
        unwind("categoryDetails"));

    return mongoTemplate.aggregate(agg, "products", Map.class).getMappedResults();
  }
}
