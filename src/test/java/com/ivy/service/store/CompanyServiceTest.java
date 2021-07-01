package com.ivy.service.store;

import com.github.pagehelper.PageInfo;
import com.ivy.domain.store.Company;
import com.ivy.service.store.impl.CompanyServiceImpl;
import com.ivy.utils.MD5Util;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * CompanyServiceTest
 *
 * @Author: ivy
 * @CreateTime: 2021-06-25
 */
public class CompanyServiceTest {
    private static CompanyService companyService = null;

    @BeforeClass
    public static void init() {
        companyService = new CompanyServiceImpl();
    }

    @Test
    public void testSave() {
        Company company = new Company();
        company.setName("测试数据");
        companyService.save(company);
    }
    @Test
    public void findAll(){
        String pwd= "root";
        String s = MD5Util.md5(pwd);
        System.out.println(s);
    }

    @AfterClass
    public static void destory() {
        companyService = null;
    }
}
