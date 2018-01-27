package com.ssm.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.dao.GoodsDao;
import com.ssm.entity.Goods;
import com.ssm.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{

	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public ArrayList<Goods> selectAll() {
		return goodsDao.selectAll();
	}

	@Override
	public Goods selectById(Goods goods) {
		return goodsDao.selectById(goods);
	}

	@Override
	public int save(Goods goods) {
		return goodsDao.save(goods);
	}

	@Override
	public int update(Goods goods) {
		return goodsDao.update(goods);
	}

	@Override
	public int deleteById(Goods goods) {
		return goodsDao.deleteById(goods);
	}
}
