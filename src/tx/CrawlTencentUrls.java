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
	
	/**
	 * 分页的匹配规则
	 */
	private static final String LISTING_PAGE = "http://v.qq.com/movielist/10001/0/0/0/\\w+/20/0/0/-1.html";

	
	private Site site = Site
            .me()
            .setDomain("http://v.qq.com/")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) "
                    + "AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	@Override
	public void process(Page page) {
			
		///System.out.println(page.getUrl().toString());
		if(page.getUrl().regex(LISTING_PAGE).match()){
			List<String> movieurls = new ArrayList<String>();
			List<String> movieids = new ArrayList<String>();
			
			movieurls = page.getHtml().xpath("//h6[@class='scores']").links().regex("http://film.qq.com/cover/\\w+/\\w+.html").all();
			for(int i = 0;i<movieurls.size();i++)	
			{	
				String temp = movieurls.get(i).substring(27,42);
				System.out.println(temp);
				//movieids.add(temp);
				
				System.out.println("urls  "+movieurls.get(i)+"  total "+movieurls.size());
					
				//String temp = movieurls.get(i).substring(beginIndex, endIndex)
				//movieurls.get(i) =  		
				}
			page.addTargetRequests(movieurls);
			}
		if(page.getUrl().regex("http://film.qq.com/cover/\\w+/\\w+.html").match()){
			page.putField(MOVIENAME, page.getHtml().xpath("//div[@class='mod_player_title cf']/h1[@class='player_title']/text()"));
			page.putField(COMMENTURLS, page.getHtml().xpath("//div[@class='np-frame']/div[@id='top_reply']/h1[@class='np-title']/a/").links().all());
		}
		
			page.addTargetRequests(page.getHtml().xpath("//div[@class='mod_pagenav']").links().regex(LISTING_PAGE).all());
	    }
	@Override

	public Site getSite() {
	    	return site;
   }
}
	


