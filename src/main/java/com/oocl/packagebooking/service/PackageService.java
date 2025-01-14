package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.enums.PackageEnum;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
                    if(aPackage.getStatus() == PackageEnum.FINISHED) {
                        throw new RuntimeException("Can not confirm a confirmed package");
                    }
                    aPackage.setStatus(PackageEnum.FINISHED);
                    return packageRepository.save(aPackage);
                })
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public Package reverse(String no, Package aPackage) {
        Optional.of(aPackage)
                .map(Package::getReserveTime)
                .map(instant -> LocalDateTime.ofInstant(instant, ZoneId.systemDefault()))
                .filter(localDateTime -> localDateTime.getHour() < 20)
                .filter(localDateTime -> localDateTime.getHour() >= 8)
                .orElseThrow(() -> new RuntimeException("Reverse time illegal"));
        return packageRepository.findByNo(no)
                .map(aPackage1 -> {
                    if(aPackage1.getStatus() != PackageEnum.PENDING) {
                        throw new RuntimeException("This package has already been reverse or confirmed");
                    }
                    aPackage1.setStatus(PackageEnum.RESERVING);
                    aPackage1.setReserveTime(aPackage.getReserveTime());
                    return packageRepository.save(aPackage1);
                })
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }
}
