package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, String> {
}
