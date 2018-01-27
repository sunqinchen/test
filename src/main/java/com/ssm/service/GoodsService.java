package com.ssm.service;
import java.util.ArrayList;
import com.ssm.entity.Goods;

public interface GoodsService {
	ArrayList<Goods> selectAll();
	Goods selectById(Goods goods);
	int save(Goods goods);
	int update(Goods goods);
	int deleteById(Goods goods);
}
