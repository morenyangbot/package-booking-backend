package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.enums.PackageEnum;
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

    public Package confirmReceipt(String id) {
        return packageRepository.findById(id)
                .map(aPackage -> {
                    aPackage.setStatus(PackageEnum.FINISHED);
                    return packageRepository.save(aPackage);
                })
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public Package reverse(String no, Package aPackage) {
        return packageRepository.findByNo(no)
                .map(aPackage1 -> {
                    aPackage1.setStatus(PackageEnum.RESERVING);
                    aPackage1.setReserveTime(aPackage.getReserveTime());
                    return packageRepository.save(aPackage1);
                })
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }
}
