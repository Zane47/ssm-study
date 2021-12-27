package com.imooc.spring.jdbc.dao;

import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Getter
@Setter
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;


    public Employee findById(Integer eno) {
        String sql = "select * from employee where eno = ?";
        // 进行指定的查询, 将唯一得到的数据转换为对应的对象


        // BeanPropertyRowMapper将bean的属性和每一行的列进行对应(Employee中的属性名和字段名按照驼峰命名格式一致)
        // 完成从数据库记录到实体对象的转化
        //

        // 将指定的sql转换为相应的对象
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{eno}, new BeanPropertyRowMapper<Employee>(Employee.class));
        return employee;
    }


}
