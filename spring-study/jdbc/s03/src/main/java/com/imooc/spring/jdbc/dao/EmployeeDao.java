package com.imooc.spring.jdbc.dao;

import com.imooc.spring.jdbc.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Repository
public class EmployeeDao {

    @Resource
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

    /**
     * 查询多条记录
     */
    public List<Employee> findByDname(String dName) {
        String sql = "select * from employee where dname = ?";
        // 查询复合数据
        List<Employee> list = jdbcTemplate.query(sql, new Object[]{dName},
                new BeanPropertyRowMapper<Employee>(Employee.class));
        return list;
    }


    /**
     * 没有与之对应的字段
     * as用来模拟无法进行有效的属性与字段的映射
     */
    public List<Map<String, Object>> findMapByDname(String dName) {
        String sql = "select eno as empno, salary as s from employee where dname = ?";

        // 查询结果, 按照列表返回, 同时默认将数据按map对象进行包裹
        // Map中的key是原始的字段名, value是字段名所对应的数值
        // queryForList: 不管有没有对应的实体属性, 都将其放入map中, 每一个map对应一条记录
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList(sql, new Object[]{dName});
        return maps;
    }

    /**
     * insert
     */
    public int insert(Employee employee) {
        String sql = "insert into employee(eno, ename, salary, dname, hiredate) values(?, ?, ?, ?, ?)";

        int count = jdbcTemplate.update(sql,
                new Object[]{employee.getEno(), employee.getEName(), employee.getSalary(), employee.getDName(), employee.getHiredate()});
        return count;
    }


    /**
     * update
     */
    public int update(Employee employee) {
        String sql = "update employee set ename = ?, salary = ?, dname = ?, hiredate = ? where eno = ?";
        int count = jdbcTemplate.update(sql,
                new Object[]{employee.getEName(), employee.getSalary(), employee.getDName(), employee.getHiredate(), employee.getEno()});
        return count;
    }


    /**
     * 删除
     */
    public int delete(int eno) {
        String sql = "delete from employee where eno = ?";
        int count = jdbcTemplate.update(sql, new Object[]{eno});
        return count;
    }

}
