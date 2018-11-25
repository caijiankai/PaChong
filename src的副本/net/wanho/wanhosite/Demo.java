/**
 * 
 */
package net.wanho.wanhosite;

import us.codecraft.webmagic.Spider;

import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author:caijiankai
 *@date: Nov 15, 2018 11:50:08 AM
 *@Copyright: 2018
 *注意:本内容严格保密,禁止外泄或私自用于商业用途
 *
 */
public class Demo {

	/**
	 * 程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		for(int i=0;i<5;i++) {
			
		
		Spider
		//爬取过程
		.create(new WanhoPageProcessor())
		//爬取结果进行保存
		.addPipeline(new WanhoPipeline())
		//第一个页面
		.addUrl("https://www.deribit.com/pages/docs/api?&lang=zh&_=1542184545326")
		//启动3个线程
		.thread(3)
		//开始
		.run();

	}
	}

}
