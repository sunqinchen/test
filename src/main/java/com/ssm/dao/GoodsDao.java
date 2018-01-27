package com.ssm.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import com.ssm.entity.Goods;

public interface GoodsDao {
	@Select("select goods_id goodsId,goods_name goodsName,goods_price goodsPrice from goods")
	ArrayList<Goods> selectAll();
	@Select("select goods_id goodsId,goods_name goodsName,goods_price goodsPrice from goods where goods_id=#{goodsId}")
	Goods selectById(Goods goods);
	@Insert("insert into goods(goods_id,goods_name,goods_price) values(#{goodsId},#{goodsName},#{goodsPrice})")
	int save(Goods goods);
	@Update("update goods set goods_name=#{goodsName},goods_price=#{goodsPrice} where goods_id=#{goodsId}")
	int update(Goods goods);
	@Delete("delete from goods where goods_id=#{goodsId}")
	int deleteById(Goods goods);
}
