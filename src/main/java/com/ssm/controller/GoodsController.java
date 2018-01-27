package com.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.entity.Goods;
import com.ssm.service.GoodsService;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsService goodsServiceImpl;
	
	@RequestMapping("/save.do")
	public String save(Goods goods) {
		int row = goodsServiceImpl.save(goods);
		if(1 == row) {
			return "success";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/update.do")
	public String update(Goods goods) {
		int row = goodsServiceImpl.update(goods);
		if(1 == row) {
			return "success";
		}else {
			return "error";
		}
	}
	
	@RequestMapping("/delete.do")
	public String delete(Goods goods) {
		int row = goodsServiceImpl.deleteById(goods);
		if(1 == row) {
			return "success";
		}else {
			return "error";
		}
	}
	
	/*@RequestMapping("/selectById.do")
	public String selectById(Goods goods) {
		Goods g = goodsServiceImpl.selectById(goods);
		if( null != g) {
			System.out.println(g);
			return "success";
		}else {
			return "error";
		}
	}*/
	
	//返回json对象
	@RequestMapping(value="/selectById.do")
	public @ResponseBody Goods selectById(Goods goods,HttpServletResponse resp) {
		resp.setContentType("application/json;charset=utf-8");
		Goods g = goodsServiceImpl.selectById(goods);
		if( null != g) {
			return g;
		}else {
			return null;
		}
	}
	
	/*@RequestMapping("/selectAll.do")
	public String selectAll() {
		ArrayList<Goods> goodss = goodsServiceImpl.selectAll();
		if( null != goodss) {
			System.out.println(goodss);
			return "success";
		}else {
			return "error";
		}
	}*/
	
	@RequestMapping("/selectAll.do")
	public @ResponseBody Map selectAll(HttpServletResponse resp,
			@RequestParam(required=true,defaultValue="1") int page,
			@RequestParam(required=false,defaultValue="3") int pageSize) {
		resp.setContentType("application/json;charset=utf-8");
		//1.!!!!设置开始页PageHelper 必须放在selectAll()之前
		PageHelper.startPage(page,pageSize);
		ArrayList<Goods> goodss = goodsServiceImpl.selectAll();
		//2.创建PageInfo
		PageInfo<Goods> pageGoodss = new PageInfo<Goods>(goodss);
		System.out.println("------------"+pageGoodss);
		//3.创建一个List
		Map map = new HashMap();
		map.put("pageGoods",pageGoodss); //放了分页的所有信息
		if( null != goodss && pageGoodss !=null) {
			return map;
			 
		}else {
			return null;
		}
	}
}
