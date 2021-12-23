import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.entity.GoodsDetail;
import com.imooc.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;


import java.io.Reader;
import java.sql.Connection;
import java.util.*;

public class MyBatisTest {


    @Test
    public void testSqlSessionFactory() {
        SqlSession sqlSession = null;
        try {
            // 利用Reader加载classpath下的mybatis-config.xml核心配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            // 初始化SqlSessionFactory对象，同时解析mybatis-config.xml文件
            // 构造者模式
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(reader);
            System.out.println("SqlSessionFactory加载成功");

            //创建SqlSession对象，SqlSession是JDBC的扩展类，用于与数据库交互
            sqlSession = build.openSession();
            //创建数据库连接（测试用）
            // 1.并不是必须的, 只是为了演示, 正常开发时由mybatis完成, 不需要显式调用
            // 2.实际引入了mybatis, 要避免直接import 数据库jdbc的类
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                // config中 -> (ps: 数据库连接池的实现之一)
                //如果type="POOLED",代表使用连接池,close则是将连接回收到连接池
                //如果type="UNPOOLED",代表直连，close则会调用Connection.close()方法关闭
                sqlSession.close();
            }
        }
    }

    @Test
    public void testMyBatisUtils() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Connection connection = sqlSession.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testGoodsSelectAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<Goods> goodsList = sqlSession.selectList("goods.selectAll");

            int a = 0;

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void testGoodsSelectById() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById", 888);
            System.out.println(goods.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    @Test
    public void testGoodsSelectByPriceRange() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Map<String, Integer> param = new HashMap<>();
            param.put("min", 888);
            param.put("max", 999);
            param.put("limit", 10);
            // 如果可以保证id全局唯一, 也可以不增加namespace: goods.selectByPriceRange
            List<Goods> list = sqlSession.selectList("selectByPriceRange", param);

            int a = 0;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    /**
     * selectGoodsMap
     */
    @Test
    public void testSelectGoodsMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // Map 返回结果乱序
            // LinkedHashMap 返回结果有序
            List<Map> list = sqlSession.selectList("goods.selectGoodsMap2");
            // 太过灵活，无法进行编译时检查
            // map中的数据类型未知
            for (Map map : list) {
                map.get("original_cost");
                System.out.println(map);
            }
            int a = 0;
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }

    /**
     * ResultMap
     */
    @Test
    public void testSelectGoodsDTO() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            List<GoodsDTO> dtos = sqlSession.selectList("goods.selectGoodsDTO");
            for (GoodsDTO dto : dtos) {
                System.out.println(dto.getGoods().getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (sqlSession != null) {
                MyBatisUtils.closeSession(sqlSession);
            }
        }
    }


    /**
     * insert test, < select >
     */
    @Test
    public void testInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();

            Goods goods = new Goods();
            goods.setTitle("测试数据");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(300f);
            goods.setCurrentPrice(200f);
            goods.setDiscount(0.7f);
            goods.setIsFreeDelivery(0);
            goods.setCategoryId(44);

            //insert()方法返回值代表本次成功插入的记录总数
            int num = sqlSession.insert("goods.insert1", goods);
            assert num == 1;
            sqlSession.commit();//提交事务数据
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();//回滚事务
            }
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    /**
     * insert test, useGeneratedKeys
     */
    @Test
    public void testInsert2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();

            Goods goods = new Goods();
            goods.setTitle("测试数据");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(300f);
            goods.setCurrentPrice(200f);
            goods.setDiscount(0.7f);
            goods.setIsFreeDelivery(0);
            goods.setCategoryId(44);

            //insert()方法返回值代表本次成功插入的记录总数
            int num = sqlSession.insert("goods.insert2", goods);
            assert num == 1;
            sqlSession.commit();//提交事务数据
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();//回滚事务
            }
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * update
     */
    @Test
    public void testUpdate() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();

            // 先select, 再更新, 保证数据影响最小
            Goods goods = session.selectOne("goods.selectById", 739);
            goods.setTitle("test update, 测试更新商品" + System.currentTimeMillis());

            // update()方法返回值代表本次成功修改的记录总数
            int num = session.update("goods.update", goods);
            assert num == 1;
            session.commit();//提交事务数据
        } catch (Exception e) {
            if (session != null) {
                session.rollback();//回滚事务
            }
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }


    /**
     * del
     */
    @Test
    public void testDelete() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            //看这里
            //delete()方法返回值代表本次成功删除的记录总数
            int num = session.delete("goods.delete", 2678);
            assert num == 1;
            session.commit();//提交事务数据

        } catch (Exception e) {
            if (session != null) {
                session.rollback();//回滚事务
            }
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }


    /**
     * 测试 ${}
     */
    @Test
    public void testSelectByTitle() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Map param = new HashMap();
            // 这里需要用单引号注明
            // param.put("title", "' '");

            param.put("title", "'' or 1 = 1");
            // ${}, 原值传递
            // select * from t_goods where title = '' or 1 = 1

            // #{}, 预编译
            // select * from t_goods where title = "'' or 1 = 1"

            param.put("order", " order by title desc");


            List<Goods> objects = session.selectList("goods.selectByTitle", param);
            for (Goods goods : objects) {
                System.out.println(goods.getTitle() + ": " + goods.getCurrentPrice());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(session);
        }
    }


    /**
     * 动态SQL
     */
    @Test
    public void testDynamicSQL() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            Map param = new HashMap();
            param.put("categoryId", 44);
            // param.put("currentPrice", 5);
            List<Goods> lists = session.selectList("goods.dynamicSQL1", param);
            for (Goods g : lists) {
                System.out.println(g.getTitle() + ": " + g.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }
    }

    /**
     * 一级缓存
     */
    @Test
    public void testLevelOneCache() {
        SqlSession session = null;

        try {
            session = MyBatisUtils.openSession();
            // 在同一个sqlSession 中执行相同的sql语句都是指向了同一个对象
            // hashcode 也就验证了sqlSession执行相同的查询语句 将从缓存中的JVM内存中读取的数据
            Goods good1 = session.selectOne("goods.selectById", 888);
            Goods good2 = session.selectOne("goods.selectById", 888);
            assert good1.hashCode() == good2.hashCode();
            System.out.println("good1: " + good1.hashCode() + " good2: " + good2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }

        try {
            //一级缓存 生命周期太短了
            session = MyBatisUtils.openSession();
            //查询相同的ID sql语句只执行了一遍
            Goods goods1 = session.selectOne("goods.selectById", 888);
            session.commit();//commit 会把当前的namespace下 所有缓存进行强制清空
            Goods goods2 = session.selectOne("goods.selectById", 888);
            System.out.println("good1: " + goods1.hashCode() + " good2: " + goods2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }
    }


    /**
     * 二级缓存
     */
    @Test
    public void testLevelTwoCache() {
        SqlSession session = null;

        try {
            session = MyBatisUtils.openSession();
            // 在同一个sqlSession 中执行相同的sql语句都是指向了同一个对象
            // hashcode 也就验证了sqlSession执行相同的查询语句 将从缓存中的JVM内存中读取的数据
            Goods good1 = session.selectOne("goods.selectById", 888);
            Goods good2 = session.selectOne("goods.selectById", 888);
            assert good1.hashCode() == good2.hashCode();
            System.out.println("good1: " + good1.hashCode() + " good2: " + good2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }

        try {
            //一级缓存 生命周期太短了
            session = MyBatisUtils.openSession();
            //查询相同的ID sql语句只执行了一遍
            Goods goods1 = session.selectOne("goods.selectById", 888);
            session.commit();//commit 会把当前的namespace下 所有缓存进行强制清空
            Goods goods2 = session.selectOne("goods.selectById", 888);
            System.out.println("good1: " + goods1.hashCode() + " good2: " + goods2.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }
    }


    /**
     * 多表级联: 一对多
     */
    @Test
    public void testOneToMany() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            List<Goods> list = session.selectList("goods.oneToMany");
            for (Goods goods : list) {
                System.out.println(goods.getTitle() + ":" + goods.getGoodsDetailList().size());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            if (session != null) {
                MyBatisUtils.closeSession(session);
            }
        }

    }


    /**
     * 多表级联: 多对一
     */
    @Test
    public void testManyToOne() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            //查询相同的ID sql语句只执行了一遍 存入缓存
            List<GoodsDetail> list = sqlSession.selectList("goodsDetail.selectManyToOne");
            //注意 association 会优先将查询到的goods_id优先传递给goods,
            for (GoodsDetail goodsDetail : list) {
                System.out.println(goodsDetail.getGoodsId() + ": " +
                        goodsDetail.getGdPicUrl() + ":" +
                        goodsDetail.getGoods().getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    /**
     * 分页
     */
    @Test
    public void testPageHelper() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            // 在查询语句前先配置分页数据 startPage会自动查询分页
            // 从第二页开始 加载十条数据
            // startPage会自动将下一次查询进行分页
            PageHelper.startPage(3, 10);
            // 在开始查询语句
            Page<Goods> page = (Page) sqlSession.selectList("goods.selectPage");
            System.out.println("总页数：" + page.getPages());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("当前页数:" + page.getPageNum());
            System.out.println("开始的行数：" + page.getStartRow());
            System.out.println("结束的行数：" + page.getEndRow());
            List<Goods> result = page.getResult();
            for (Goods goods : result) {
                System.out.println(goods.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    /**
     * 批处理插入
     */
    @Test
    public void testBatchInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            //测试插入10000条数据
            long start = new Date().getTime();
            List<Goods> list = new ArrayList();
            for (int i = 0; i < 10000; i++) {
                Goods goods = new Goods();
                goods.setTitle("测试插入：" + i);
                goods.setSubTitle("测试商品:" + i);
                goods.setDiscount(200f);
                goods.setCategoryId(1);
                goods.setIsFreeDelivery(1);
                goods.setCurrentPrice(100f);
                goods.setOriginalCost(100f);
                list.add(goods);
            }
            sqlSession.insert("goods.batchInsert", list);
            // sqlSession.commit();//提交事务数据

            long end = new Date().getTime();
            // 09:21:55.511 [main] DEBUG [DEBUG][goods.batchInsert][debug][143] -> <==    Updates: 10000
            // 所用时间：5265ms
            System.out.println("所用时间：" + (end - start) + "ms");//7253ms
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    /**
     * 普通的一次插入一条的操作所用的时间,
     * 与批插入做对比, 10000次
     */
    @Test
    public void testBatchInsert2(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            //测试插入10000条数据
            long start = new Date().getTime();
            List list = new ArrayList();
            for (int i = 0; i < 10000; i++) {
                Goods goods = new Goods();
                goods.setTitle("测试插入："+i);
                goods.setSubTitle("测试商品:"+i);
                goods.setDiscount(200f);
                goods.setCategoryId(1);
                goods.setIsFreeDelivery(1);
                goods.setCurrentPrice(100f);
                goods.setOriginalCost(100f);
                sqlSession.insert("goods.insertGoods", goods);
            }
            // sqlSession.commit();//提交事务数据
            long end = new Date().getTime();
            System.out.println("所用时间："+(end-start)+"ms");//509749ms 一条一条的进行插入 所用的时间要比批量插入长很多
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }


    /**
     * 测试batchDelete
     */
    @Test
    public void testBatchDelete() {
        SqlSession sqlSession = null;
        try {
            long start = new Date().getTime();
            sqlSession = MyBatisUtils.openSession();
            List<Integer> list = new ArrayList<>();
            list.add(1920);
            list.add(1921);
            list.add(1922);
            sqlSession.delete("batch.batchDelete", list);
            // sqlSession.commit();//提交事务数据
            long end = new Date().getTime();
            System.out.println("所用时间："+(end-start)+"ms");//14178ms 一条一条的进行插入 所用的时间要比批量插入长很多
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            MyBatisUtils.closeSession(sqlSession);
        }

    }



}