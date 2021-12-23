package com.imooc.mybatis.dao;

import com.imooc.mybatis.dto.GoodsDTO;
import com.imooc.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDAO {
    /**
     * 按照价格区间查找
     *
     * @param min
     * @param max
     * @param limit
     * @return
     */
    @Select("select *\n" +
            "        from t_goods\n" +
            "        where current_price between #{min} and #{max}\n" +
            "        order by current_price\n" +
            "        limit 0, #{limit}")
    public List<Goods> selectByPriceRange(@Param("min") Float min,
                                          @Param("max") Float max,
                                          @Param("limit") Integer limit);


    /**
     * 插入
     *
     * @param goods
     * @return
     */
    @Insert("INSERT INTO t_goods(title, sub_title, original_cost, current_price,\n" +
            "        discount, is_free_delivery, category_id)\n" +
            "        VALUES (#{title}, #{subTitle}, #{originalCost}, #{currentPrice},\n" +
            "        #{discount}, #{isFreeDelivery}, #{categoryId});")
    // statement: selectKey要执行的SQL语句
    @SelectKey(statement = "select last_insert_id()", before = false, resultType = Integer.class, keyProperty = "goodsId")
    public int insert(Goods goods);

    /**
     * 返回结果映射集合的注解处理
     */
    @Select("select g.*,c.* from t_goods g, t_category c where g.category_id = c.category_id")
    @Results({
            @Result(property = "goods.goodsId", column = "goods_id", id = true),
            @Result(property = "goods.title", column = "title"),
            @Result(property = "goods.subTitle", column = "sub_title"),
            @Result(property = "goods.originalCost", column = "original_cost"),
            @Result(property = "goods.currentPrice", column = "current_price"),
            @Result(property = "goods.discount", column = "discount"),
            @Result(property = "goods.isFreeDelivery", column = "is_free_delivery"),
            @Result(property = "goods.categoryId", column = "category_id"),
            @Result(property = "category.categoryName", column = "category_name"),
            @Result(property = "category.parentId", column = "parent_id"),
            @Result(property = "category.categoryLevel", column = "category_level"),
            @Result(property = "category.categoryOrder", column = "category_order")
    })
    List<GoodsDTO> selectDTO();

}
