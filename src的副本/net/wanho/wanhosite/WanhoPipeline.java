/**
 * 
 */
package net.wanho.wanhosite;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author:caijiankai
 *@date: Nov 15, 2018 12:08:11 PM
 *@Copyright: 2018
 *注意:本内容严格保密,禁止外泄或私自用于商业用途
 *
 */
public class WanhoPipeline implements Pipeline {

	/* (non-Javadoc)
	 * @see us.codecraft.webmagic.pipeline.Pipeline#process(us.codecraft.webmagic.ResultItems, us.codecraft.webmagic.Task)
	 */
	@Override
	public void process(ResultItems resultItems, Task arg1) {
		System.out.println("process.....");
		// 获取抓取过程中保存的数据
		Vector<PageVo> voLst=resultItems.get("e_list");
		//持久化到文件中
		PrintWriter pw=null;
		
			//添加true,代表追加写
			try {
				pw = new PrintWriter(new FileWriter("wanhu.txt",true));
			
			for(PageVo vo:voLst) {
				pw.println(vo);
				pw.flush();
				//保存图片
				saveImg(vo.getImg());
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			pw.close();
		}
	}
	private void saveImg(String img) {
		String imgUrl="http://www.wanho.net"+img;
		InputStream is=null;
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try {
			URL url=new URL(imgUrl);
			URLConnection uc=url.openConnection();	  
			//得到一个图片流
			 is=uc.getInputStream();
			 bis=new BufferedInputStream(is);
			File photofile=new File("photo");
			if(!photofile.exists()) {
				photofile.mkdirs();
			}
			//图片名称
			String imgName=img.substring(img.lastIndexOf("/")+1);
			File saveFile=new File(photofile,imgName);
			
			 bos=new BufferedOutputStream(new FileOutputStream(saveFile));
			byte[] bs=new byte[1024];
			int len;
			//保存
			while((len=bis.read(bs))!=-1) {
				bos.write(bs,0,len);
			}
			bos.close();
			bis.close();
			is.close();
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {		
			e.printStackTrace();
	}finally {
		try {
			bos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			bis.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
		try {
			is.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
		
}
		
		




