package com.jt.web;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient 
{
	@Test
	public void testGet() throws ClientProtocolException, IOException 
	{
		//定义httpClient请求对象（可关闭的Http客户端=Http客户端.创建默认）
		CloseableHttpClient client = HttpClients.createDefault();
		
		//定义访问url
		String url = "https://game.tgbus.com/game/536871367";
		
		//定义请求类型。get/post/put
		HttpGet get = new HttpGet(url);
		
		//发起请求，获取返回值结果
		CloseableHttpResponse respones = client.execute(get);
		
		//判断返回值状态是否--200（返回成功）
		if(respones.getStatusLine().getStatusCode() == 200) //状态链中的状态码
		{
			//获得实体
			HttpEntity entity = respones.getEntity();
			//实体转化字符串
			String result = EntityUtils.toString(entity);
			//
			System.out.println(result);
		}
	}
}
