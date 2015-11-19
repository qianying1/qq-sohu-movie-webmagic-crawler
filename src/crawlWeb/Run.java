package crawlWeb;
import tx.CrawlTencentUrls;
import us.codecraft.webmagic.Spider;
public class Run {
	
  public static void runnerTencent(){
	  String tencentStartUrls ="http://v.qq.com/movielist/10001/0/0/0/0/20/0/0/-1.html";
	  Spider.create(new CrawlTencentUrls()).addUrl(tencentStartUrls).run();
	  
  }

}
