package com.imooc.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.common.utils.IMoocJSONResult;
import com.imooc.curator.utils.ZKCurator;
import com.imooc.web.service.CulsterService;

/**
 * @Description: 订购商品controller
 */
@Controller
public class PayController {
	
	@Autowired
	private CulsterService buyService;
	
	@Autowired
	private ZKCurator zkCurator;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/buy")
	@ResponseBody
	public IMoocJSONResult doGetlogin(String itemId) {
		
		boolean result = buyService.displayBuy(itemId);
		return IMoocJSONResult.ok(result?"订单创建成功。。":"订单创建失败");
	}
	
	/**
	 * 模拟集群环境下数据不一致
	 * @param itemId
	 * @return
	 */
	@GetMapping("/buy2")
	@ResponseBody
	public IMoocJSONResult doGetlogin2(String itemId) {
		boolean result = buyService.displayBuy(itemId);
		return IMoocJSONResult.ok(result?"订单创建成功。。":"订单创建失败");
	}
	
	/**
	 * 判断zk是否连接
	 * @param itemId
	 * @return
	 */
	@GetMapping("/isZKAlive")
	@ResponseBody
	public IMoocJSONResult isZKAlive() {
		boolean isAlive = zkCurator.isZKAlive();
		return IMoocJSONResult.ok(isAlive?"连接。。":"断开");
	}
	
	
}
