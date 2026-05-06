package com.mongodb.dto;

// ProductWithCategoryDTO.java
public class ProductWithCategoryDTO {
  private String id;
  private String name;
  private Double price;
  private String catId;
  // 這個名稱要跟 Aggregation 中的 .as("category") 一致
  private Object category;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getCatId() {
    return catId;
  }

  public void setCatId(String catId) {
    this.catId = catId;
  }

  public Object getCategory() {
    return category;
  }

  public void setCategory(Object category) {
    this.category = category;
  }

}
