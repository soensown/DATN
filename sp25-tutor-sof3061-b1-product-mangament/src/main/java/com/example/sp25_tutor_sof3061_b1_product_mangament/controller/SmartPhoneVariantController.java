package com.example.sp25_tutor_sof3061_b1_product_mangament.controller;

import com.example.sp25_tutor_sof3061_b1_product_mangament.model.SmartPhoneVariant;
import com.example.sp25_tutor_sof3061_b1_product_mangament.repository.SmartPhoneVariantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smart-phone-variant")
@AllArgsConstructor
public class SmartPhoneVariantController {

    private final SmartPhoneVariantRepository repository;

    // GET http://localhost:8080/smart-phone-variant
    @GetMapping
    public List<SmartPhoneVariant> getAll() {
        return repository.findAll();
    }

    // POST http://localhost:8080/smart-phone-variant
    @PostMapping
    public SmartPhoneVariant addNew(@RequestBody SmartPhoneVariant variant) {
        // Id = null => Them moi
        variant.setId(null);
        return repository.save(variant);
    }

    // PUT http://localhost:8080/smart-phone-variant/{id}
    @PutMapping("/{id}")
    public SmartPhoneVariant updateById(@PathVariable("id") Long id,
                                    @RequestBody SmartPhoneVariant variant) {
        variant.setId(id); // Id khong ton tai = Them moi
        return repository.save(variant);
    }

    // DELETE http://localhost:8080/smart-phone-variant/{id}
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    // GET http://localhost:8080/smart-phone-variant/{id}
    @GetMapping("/{id}")
    public SmartPhoneVariant getById(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }
}
