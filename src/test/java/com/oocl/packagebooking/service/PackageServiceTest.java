package com.oocl.packagebooking.service;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.enums.PackageEnum;
import com.oocl.packagebooking.repository.PackageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PackageServiceTest {
    @TestConfiguration
    static class PackageServiceTestContextConfiguration{
        @Bean
        public PackageService packageService(){
            return new PackageService();
        }
    }

    @MockBean
    private PackageRepository packageRepository;

    @Autowired
    private PackageService packageService;

    private Package generatePackage() {
        Package pkg = new Package();
        pkg.setId("1");
        pkg.setNo("1");
        pkg.setStatus(PackageEnum.PENDING);
        pkg.setCustomerName("YT");
        pkg.setPhone("12333");
        return pkg;
    }

    @Test
    public void should_return_package_when_save_package() {
        Package pkg = generatePackage();
        when(packageRepository.save(any())).thenReturn(pkg);

        Package savedPkg = packageService.save(pkg);

        assertEquals(pkg.getId(), savedPkg.getId());
        verify(packageRepository).save(any());
    }

    @Test
    public void should_return_all_packages_when_fetch_all() {
        when(packageRepository.findAll()).thenReturn(new ArrayList<Package>());
        packageService.findAll();
        verify(packageRepository).findAll();
    }

}