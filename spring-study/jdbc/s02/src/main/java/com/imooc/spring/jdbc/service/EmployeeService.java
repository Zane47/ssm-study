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

    private BatchService batchService;

    public void batchImport() throws Exception {
        for (int i = 1; i <= 10; i++) {
            /*if (i == 3) {
                // throw new Exception("test");

                // 运行时异常则回滚
                throw new RuntimeException("test");
            }*/
            Employee employee = new Employee();
            employee.setEno(8000 + i);
            employee.setEName("worker" + i);
            employee.setSalary(4000F);
            employee.setDName("市场部");
            employee.setHiredate(new Date());
            employeeDao.insert(employee);
        }
    }

    public void startImportJob() {
        batchService.importJob1();

        if (1 == 1) {
            throw new RuntimeException("test");
        }

        batchService.importJob2();
        System.out.println("batch import done");
    }

}
