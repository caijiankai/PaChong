/**
 * 
 */
package net.wanho.wanhosite;

/**
 * @author:caijiankai
 *@date: Nov 15, 2018 12:40:12 PM
 *@Copyright: 2018
 *注意:本内容严格保密,禁止外泄或私自用于商业用途
 *
 */
public class PageVo {
	
	//爬取网站的标题,内容,图片,放到这个模型中
			private String title;
			private String content;
			private String img;
			/**
			 * @return the title
			 */
			public String getTitle() {
				return title;
			}
			/**
			 * @param title
			 * @param content
			 * @param img
			 */
			public PageVo(String title, String content, String img) {
				super();
				this.title = title;
				this.content = content;
				this.img = img;
			}
			/**
			 * @param title the title to set
			 */
			public void setTitle(String title) {
				this.title = title;
			}
			/**
			 * @return the content
			 */
			public String getContent() {
				return content;
			}
			/**
			 * @param content the content to set
			 */
			public void setContent(String content) {
				this.content = content;
			}
			/**
			 * @return the img
			 */
			public String getImg() {
				return img;
			}
			/**
			 * @param img the img to set
			 */
			public void setImg(String img) {
				this.img = img;
			}
			/* (non-Javadoc)
			 * @see java.lang.Object#toString()
			 */
			@Override
			public String toString() {
				return "PageVo [title=" + title + ", content=" + content + ", img=" + img + "]";
			}
			
			
}
