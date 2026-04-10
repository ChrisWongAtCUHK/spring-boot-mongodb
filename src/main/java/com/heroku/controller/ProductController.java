package com.heroku.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.model.Product;
import com.heroku.repository.ProductRepository;
import com.heroku.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductRepository repository;

  @Autowired
  private ProductService productService;

  // 取得所有產品
  @GetMapping
  public List<Product> getAll() {
    return repository.findAll();
  }

  // 依名稱搜尋 (對應你之前的 "Air" 練習)
  @GetMapping("/search")
  public List<Product> search(@RequestParam String name) {
    return repository.findByNameContaining(name);
  }

  // 新增產品
  @PostMapping
  public Product create(@RequestBody Product product) {
    return repository.save(product);
  }

  // 刪除產品
  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    repository.deleteById(id);
  }

  @GetMapping("/getStockValueReport")
  public List<Map> getStockValueReport() {
    return productService.getStockValueReport();
  }

  @GetMapping("/getProductsWithCategory")
  public List<Map> getProductsWithCategory() {
    return productService.getProductsWithCategory();
  }
}
