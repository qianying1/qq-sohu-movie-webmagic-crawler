package tx;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CrawlTencentUrls implements PageProcessor
 {	  
	
	
	/***
	 *  标签名字
	 */
	public static final String MOVIENAME ="movieName";
	public static final String COMMENTURLS ="commentUrls";
	public static final String MOVIEIDURL = "movieidurl";
	
	/**
	 * 分页的匹配规则
	 */
	private static final String LISTING_PAGE = "http://v.qq.com/movielist/10001/0/0/0/\\w+/20/0/0/-1.html";
 /**
  *	获取movie id的url 
  */
	private static final String GET_MOVIEID ="http://sns.video.qq.com/fcgi-bin/video_comment_id?otype=json&op=3&cid=";
	/**
	 * 获取评论的 urls
	 * 
	 */
	private static final String GET_COMMENT ="http://coral.qq.com/article/";
	/**
	 * movieurl
	 */
	private static final String movieurl_typeone="http://film.qq.com/cover/\\w+/\\w+.html";
	private static final String movieurl_typetwo ="http://v.qq.com/cover/\\w+/\\w+.html";
	private Site site = Site
            .me()
            .setDomain("http://v.qq.com/")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) "
                    + "AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	@Override
	public void process(Page page)
	{
		
	if(page.getUrl().regex(LISTING_PAGE).match())
	{
		List<String> movieurls = new ArrayList<String>();
		List<String> movieids = new ArrayList<String>();
		List<String> srcid = new ArrayList<String>();
		List<String> moviename =new ArrayList<String>();
		

		movieurls = page.getHtml().xpath("//div[@class='mod_video_list poster']/div[@class='mod_cont']/ul[@class='mod_list_pic_130']/li/"
			+ "h6[@class='scores']/a/@href").all();
		moviename = page.getHtml().xpath("//div[@class='mod_video_list poster']/div[@class='mod_cont']/ul[@class='mod_list_pic_130']/li/"
				+ "a/@title").all();
		System.out.println(movieurls.size());
		for(int i = 0;i<movieurls.size();i++)
		{
			
		if(movieurls.get(i).length()>44)
		{
		String temp = movieurls.get(i).substring(27,42);
		srcid.add(temp);
		//System.out.println(temp);
		temp = GET_MOVIEID+temp;
		movieids.add(temp);
		}
		else
		{
			String temptemp =movieurls.get(i).substring(24,39);
			srcid.add(temptemp);
			temptemp = GET_MOVIEID+temptemp;
			movieids.add(temptemp);	
		}
		
		//System.out.println("urls  "+movieurls.get(i)+"  total "+ movieurls.size() + " "+movieids.get(i));
	}	
		
		//moviename =page.getHtml().xpath("//div[@class='mod_video_list poster']/div[@class='mod_cont']/ul[@class='mod_list_pic_130']/li/"
				//+ "h6[@class='scores']/a/text()").all();
		
		page.putField(MOVIENAME,moviename);
		//page.putField(, field);
		page.putField("srcid", srcid);
		//page.addTargetRequests(movieids);
		//page.addTargetRequests(movieurls);
		page.addTargetRequests(page.getHtml().xpath("//div[@class='mod_pagenav']/p[@class='mod_pagenav_main']/a[@class='next']/@href").regex(LISTING_PAGE).all());
	}
	else 
	{	
		
			String temp = page.getJson().toString().split("=")[1];
			temp = temp.split(";")[0];
			JSONObject movieidJsob = JSONObject.parseObject(temp);
			page.putField("movieOb", movieidJsob);
			//page.putField("id",movieid.getString("comment_id"));
			//page.putField("movieid", movieid.getString("comment_id"));
			page.putField(COMMENTURLS, GET_COMMENT+movieidJsob.getString("comment_id")+"/comment?reqnum=50");
			//System.out.println(movieid.getString("comment_id"));
			//System.out.println(temp);
		
		//page.putField(COMMENTURLS, page.getHtml().xpath("//div[@class='np-frame']/div[@id='top_reply']/h1[@class='np-title']/a/").links().all());
	 }
		/*else
		//if(page.getUrl().regex("http://sns.video.qq.com/fcgi-bin/video_comment_id?otype=json&op=3&cid=\\w+").match())
		{
			
			String temp = page.getJson().toString().split("=")[1];
			temp = temp.split(";")[0];
			JSONObject movieid = JSONObject.parseObject(temp);
			//page.putField("id",movieid.getString("comment_id"));
			page.putField(COMMENTURLS, GET_COMMENT+movieid.getString("comment_id")+"/comment?reqnum=50");
			//System.out.println(movieid.getString("comment_id"));
			//System.out.println(temp);
			
		}	*/
		
	}
	@Override
	public Site getSite()
	{
	    	return site;
	    }
}
	