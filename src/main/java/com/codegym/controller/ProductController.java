package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService products;

    @GetMapping()
    public String showHomePage(Model model){
        model.addAttribute("products",products.findAll());
        return "home";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/save")
    public String addNewProduct(Product product, RedirectAttributes redirectAttributes){
        products.save(product);
        redirectAttributes.addFlashAttribute("message","Add product successfully");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showUpdatePage(Model model, @PathVariable int id){
        model.addAttribute("product",products.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String updateProduct(Product product, RedirectAttributes redirectAttributes){
        products.update(product.getId(),product);
        redirectAttributes.addFlashAttribute("message","Save product successfully");
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(Model model, @PathVariable int id){
        model.addAttribute("product",products.findById(id));
        return "delete";
    }
}
