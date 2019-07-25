package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService implements BaseService<Package, String> {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public Package save(Package aPackage) {
        return packageRepository.save(aPackage);
    }

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    @Override
    public void deleteById(String s) {
        packageRepository.deleteById(s);
    }

    @Override
    public Optional<Package> findById(String s) {
        return packageRepository.findById(s);
    }
}
