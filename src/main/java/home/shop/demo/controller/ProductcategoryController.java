package home.shop.demo.controller;

import home.shop.demo.domain.ProductCategory;
import home.shop.demo.service.ProductcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productcategory")
public class ProductcategoryController {

    @Autowired
    private ProductcategoryService productcategoryService;

    public ProductcategoryController(ProductcategoryService productcategoryService){
        this.productcategoryService = productcategoryService;
    }

    @GetMapping("/list")
    public Iterable<ProductCategory>list(){
        return productcategoryService.getAllProductcategory();
    }
}

