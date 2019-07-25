package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, String> {
    Optional<Package> findByNo(String id);
}
