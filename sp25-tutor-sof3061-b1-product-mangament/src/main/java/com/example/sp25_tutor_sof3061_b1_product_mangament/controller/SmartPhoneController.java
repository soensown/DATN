package com.example.sp25_tutor_sof3061_b1_product_mangament.controller;

import com.example.sp25_tutor_sof3061_b1_product_mangament.model.SmartPhone;
import com.example.sp25_tutor_sof3061_b1_product_mangament.repository.SmartPhoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smart-phone")
@AllArgsConstructor // ham khoi tao voi toan bo tham so => Inject
public class SmartPhoneController {

    private final SmartPhoneRepository repository;

    // GET http://localhost:8080/smart-phone
    @GetMapping
    public List<SmartPhone> getAll() {
        return repository.findAll();
    }

    // POST http://localhost:8080/smart-phone
    @PostMapping
    public SmartPhone addNew(@RequestBody SmartPhone smartPhone) {
        // Id = null => Them moi
        smartPhone.setId(null);
        return repository.save(smartPhone);
    }

    // PUT http://localhost:8080/smart-phone/{id}
    @PutMapping("/{id}")
    public SmartPhone updateById(@PathVariable("id") Long id,
                                 @RequestBody SmartPhone smartPhone) {
        smartPhone.setId(id); // Id khong ton tai = Them moi
        return repository.save(smartPhone);
    }

    // DELETE http://localhost:8080/smart-phone/{id}
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    // GET http://localhost:8080/smart-phone/{id}
    @GetMapping("/id")
    public SmartPhone getById(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }
}
