
import java.util.Date;

import com.imooc.spring.jdbc.dao.EmployeeDao;
import com.imooc.spring.jdbc.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JdbcTemplateTest {

    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过id获取,单条记录
     */
    @Test
    public void testFindById() {
        Employee employee = employeeDao.findById(3308);
        System.out.println(employee);
    }

    /**
     * 查询list
     */
    @Test
    public void testFindByDname() {
        List<Employee> byDname = employeeDao.findByDname("研发部");
        System.out.println(byDname);
    }


    /**
     * 没有与之对应的字段
     */
    @Test
    public void findMapByDname() {
        List<Map<String, Object>> list = employeeDao.findMapByDname("研发部");
        System.out.println(list);
    }


    /**
     * insert
     */
    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEno(8888);
        employee.setEName("zhaoliu");
        employee.setSalary(6666F);
        employee.setDName("研发部");
        employee.setHiredate(new Date());


        employeeDao.insert(employee);
    }


    /**
     * update
     */
    @Test
    public void testUpdate() {
        Employee employee = employeeDao.findById(8888);
        employee.setSalary(employee.getSalary() + 1000);

        int count = employeeDao.update(employee);
        System.out.println("# of update: " + count);
    }


    /**
     * 删除
     */
    @Test
    public void testDelete() {
        int count = employeeDao.delete(8888);
        System.out.println("# of delete: " + count);
    }


}
