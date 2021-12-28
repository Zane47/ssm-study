package com.imooc.spring.jdbc.service;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Getter
@Setter
@Service
// 声明式事务核心注解
// 放在类上, 将声明式事务配置应用于当前类所有方法, 默认事务传播为REQUIRED
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Resource
    private BatchService batchService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public void findById(Integer eno) {
        employeeDao.findById(eno);
    }

    public void batchImport() throws Exception {
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                // throw new Exception("test");

                // 运行时异常则回滚
                throw new RuntimeException("test");
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

    public void startImportJob() {
        batchService.importJob1();

        if (1 == 1) {
            throw new RuntimeException("test");
        }

        batchService.importJob2();
        System.out.println("batch import done");
    }

}
