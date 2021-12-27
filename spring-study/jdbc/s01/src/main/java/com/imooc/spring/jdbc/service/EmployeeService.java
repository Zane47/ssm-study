package com.imooc.spring.jdbc.service;

import java.util.Date;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Getter
@Setter
public class EmployeeService {

    private EmployeeDao employeeDao;

    private DataSourceTransactionManager transactionManager;

    public void batchImport() throws Exception {
        // 定义了事务默认的标准配置
        TransactionDefinition definition = new DefaultTransactionDefinition();

        // 开始一个事务, 返回事务状态, 事务状态说明当前事务的执行阶段
        TransactionStatus status = transactionManager.getTransaction(definition);
        // 所有的数据操作都会放入事务区中
        try {
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

            // 提交事务
            transactionManager.commit(status);
        } catch (Exception e) {
            // 回滚事务
            transactionManager.rollback(status);
            e.printStackTrace();
        }


    }

}
