import com.imooc.mybatis.dao.GoodsDAO;
import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import com.imooc.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyBatisTestAnnotation {
    /**
     * select
     */
    @Test
    public void testSelectByPriceRange() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            // getMapper方式
            // 虽然持有的是接口, 但是执行的时候根据GoodsDAO中的配置信息, 动态生成实现类
            GoodsDAO goodsDAO = session.getMapper(GoodsDAO.class);
            List<Goods> goodsList = goodsDAO.selectByPriceRange(100f, 500f, 20);
            System.out.println(goodsList.size());
            for (Goods goods : goodsList) {
                System.out.println(goods.getGoodsId() + ":" + goods.getTitle());
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
     * insert
     */
    @Test
    public void testInsert() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            // getMapper方式
            // 虽然持有的是接口, 但是执行的时候根据GoodsDAO中的配置信息, 动态生成实现类
            GoodsDAO goodsDAO = session.getMapper(GoodsDAO.class);

            Goods goods = new Goods();
            goods.setTitle("测试数据2");
            goods.setSubTitle("测试子标题2");
            goods.setOriginalCost(300f);
            goods.setCurrentPrice(200f);
            goods.setDiscount(0.7f);
            goods.setIsFreeDelivery(0);
            goods.setCategoryId(44);

            int num = goodsDAO.insert(goods);
            assert num == 1;
            session.commit();
            System.out.println(goods.getGoodsId());
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
     * resultMap
     */
    @Test
    public void testResultMap() {
        SqlSession session = null;
        try {
            session = MyBatisUtils.openSession();
            // getMapper方式
            // 虽然持有的是接口, 但是执行的时候根据GoodsDAO中的配置信息, 动态生成实现类
            GoodsDAO goodsDAO = session.getMapper(GoodsDAO.class);
            List<GoodsDTO> dtos = goodsDAO.selectDTO();
            System.out.println(dtos.size());
            for (GoodsDTO dto : dtos) {
                System.out.println(dto.getGoods().getGoodsId());
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


}
