package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.enums.PackageEnum;
import com.oocl.packagebooking.service.PackageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    public void should_return_test_package_when_save_package() throws Exception {
        Package pkg = generatePackage();
        when(packageService.save(any())).thenReturn(pkg);

        ResultActions resultActions = mockMvc.perform(post("/packages").content("{}").contentType(MediaType.APPLICATION_JSON_UTF8));

        resultActions.andExpect(status().isOk());
        verify(packageService).save(any());
    }

    @Test
    public void should_return_all_list_when_call_get_all() throws Exception {
        when(packageService.findAll()).thenReturn(new ArrayList<Package>());
        ResultActions resultActions = mockMvc.perform(get("/packages"));
        resultActions.andExpect(status().isOk());
        verify(packageService).findAll();
    }

    @Test
    public void should_return_package_when_call_confirm_receipt() throws Exception {
        Package pkg = generatePackage();
        when(packageService.confirmReceipt(any())).thenReturn(pkg);
        ResultActions resultActions = mockMvc.perform(put("/packages/2/confirm"));
        resultActions.andExpect(status().isOk());
        verify(packageService).confirmReceipt(any());
    }

    @Test
    public void should_return_package_when_call_reverse_receipt() throws Exception {
        Package pkg = generatePackage();
        when(packageService.reverse(any(), any())).thenReturn(pkg);
        ResultActions resultActions = mockMvc.perform(put("/packages/2/reverse").content("{}").contentType(MediaType.APPLICATION_JSON_UTF8));
        resultActions.andExpect(status().isOk());
        verify(packageService).reverse(any(), any());
    }
}