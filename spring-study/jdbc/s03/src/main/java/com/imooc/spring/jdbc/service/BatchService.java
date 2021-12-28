package com.imooc.spring.jdbc.service;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Getter
@Setter
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BatchService {

    @Resource
    private EmployeeDao employeeDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
