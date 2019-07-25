package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PostMapping
    public Package save(@RequestBody Package aPackage) {
        return packageService.save(aPackage);
    }

    @GetMapping
    public List<Package> findAll() {
        return packageService.findAll();
    }

    @GetMapping("/{id}")
    public Package findById(@PathVariable("id") String s) throws Exception {
        return packageService.findById(s).orElseThrow(() -> new RuntimeException("Record not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String s) {
        packageService.deleteById(s);
    }

    @PutMapping
    public Package update(@RequestBody Package aPackage) {
        return packageService.save(aPackage);
    }
}
