package com.imooc.spring.jdbc.service;
import java.util.Date;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeService {

    private EmployeeDao employeeDao;

    public void batchImport() throws Exception {
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                throw new Exception("test");
            }
            Employee employee = new Employee();
            employee.setEno(8000 + i);
            employee.setEName("worker" + i);
            employee.setSalary(4000F);
            employee.setDName("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

}
