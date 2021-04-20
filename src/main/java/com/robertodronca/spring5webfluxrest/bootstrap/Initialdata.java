package com.robertodronca.spring5webfluxrest.bootstrap;

import com.robertodronca.spring5webfluxrest.domain.Category;
import com.robertodronca.spring5webfluxrest.domain.Vendor;
import com.robertodronca.spring5webfluxrest.repositories.CategoryRepository;
import com.robertodronca.spring5webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initialdata implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Initialdata(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadVendors();
    }

    private void loadVendors(){
        if (vendorRepository.count().block() == 0){

            System.out.println("####  Loading vendor data on bootstrap ####");

            vendorRepository.save(Vendor.builder()
                    .firstName("Joe")
                    .lastName("Ghita")
                    .build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Alex")
                    .lastName("Pavel")
                    .build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Paul")
                    .lastName("Enache")
                    .build()).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());
        }
    }

    private void loadCategories() {
        if (categoryRepository.count().block() == 0){
            //load Data
            System.out.println("####  Loading category data on bootstrap ####");

            categoryRepository.save(Category.builder()
                    .description("Fruits")
                    .build()).block();
            categoryRepository.save(Category.builder()
                    .description("Nuts")
                    .build()).block();
            categoryRepository.save(Category.builder()
                    .description("Breads")
                    .build()).block();
            categoryRepository.save(Category.builder()
                    .description("Meats")
                    .build()).block();
            categoryRepository.save(Category.builder()
                    .description("Eggs")
                    .build()).block();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());


       }
    }
}
