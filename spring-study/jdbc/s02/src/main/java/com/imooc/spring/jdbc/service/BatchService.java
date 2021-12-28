package com.imooc.spring.jdbc.service;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BatchService {

    private EmployeeDao employeeDao;

    /**
     * 批量导入10条研发部人员信息
     */
    public void importJob1() {
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setEno(7000 + i);
            employee.setEName("RD" + i);
            employee.setSalary(4000F);
            employee.setDName("研发部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }


    /**
     * 批量导入10条市场部人员信息
     */
    public void importJob2() {
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setEno(6000 + i);
            employee.setEName("market" + i);
            employee.setSalary(4000F);
            employee.setDName("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }

    }

}
