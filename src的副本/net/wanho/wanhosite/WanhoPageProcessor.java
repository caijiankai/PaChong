/**
 * 
 */
package net.wanho.wanhosite;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author:caijiankai
 *@date: Nov 15, 2018 11:52:51 AM
 *@Copyright: 2018
 *注意:本内容严格保密,禁止外泄或私自用于商业用途
 *
 */
public class WanhoPageProcessor implements PageProcessor {

	//部分一:抓取网站的相关配置,包括编码,抓取间隔,重试次数
	private Site site=
			Site.me()
			.setTimeOut(10000)
			.setRetryTimes(3)
			.setSleepTime(1000)
			.setCharset("UTF-8");
		
	/* 
	 * 获得站点
	 * @see us.codecraft.webmagic.processor.PageProcessor#getSite()
	 */
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	/* 
	 * 
	 * 爬取过程
	 * @see us.codecraft.webmagic.processor.PageProcessor#process(us.codecraft.webmagic.Page)
	 */
	@Override
	public void process(Page page) {
		//获取当前页的所有
		List<String> list=page.getHtml().xpath("//div[@class='main_l']/ul/li").all();
		
		//要保存喜报的集合(线程安全的集合vector)
		Vector<PageVo> voLst=new Vector<>();
		//遍历喜报
		String title;
		String content;
		String img;
		for(String item:list) {
			Html tmp=Html.create(item);
			System.out.println(tmp);
			
			System.out.println("-------------------------");
			//标题
			 title=tmp.xpath("//div[@class='content']/h4/a/text()").toString();
			//内容
			 content=tmp.xpath("//div[@class='content']/p/text()").toString();
			//图像
			 img=tmp.xpath("//a/img/@src").toString();
			 //	加入集合	 
			PageVo vo=new PageVo(title,content,img);
			voLst.add(vo);
			//System.out.println(vo);
		}
		//保存数据至page中,后续进行持久化
		page.putField("e_list", voLst);
		//加载其他页
		page.addTargetRequests(getOtherUrls());
		if(list.size()<=0) {
			//忽略这个页面
			page.setSkip(true);
		}
		
	}

	/**
	 * @return
	 */
	private List<String> getOtherUrls() {
		// TODO Auto-generated method stub
		
		List<String> urlLsts=new ArrayList<>();
		for(int i=2;i<5;i++) {
			urlLsts.add("http://www.wanho.net/a/jyxb/list_15_"+i+".html");
		}
		return urlLsts;
	}

}
