package com.jt.web.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	private HttpClientService httpClient;
	
	private ObjectMapper objectMapper = new ObjectMapper(); 
	@Override
	public Item findItemById(Long itemId) 
	{
		//1.定义url地址
		String url = "http://manage.jt.com/web/item/findItemById";
		//2.封装参数
		Map<String, String> params = new HashMap<>();
		params.put("itemId", itemId+"");
		//3.发起请求,解析返回值结果
		String itemJSON = httpClient.doGet(url, params);
		//4.将JSON转化为对象
		Item item = null;
		try 
		{
			item = objectMapper.readValue(itemJSON, Item.class);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return item;
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) 
	{
		String url = "http://manage.jt.com/web/item/findItemDescById/"+itemId;
		
		String itemDescJSON = httpClient.doGet(url);
		
		ItemDesc itemDesc = null;
		try 
		{
			itemDesc = objectMapper.readValue(itemDescJSON, ItemDesc.class);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return itemDesc;
	}
}
