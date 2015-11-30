package crawlWeb;
import tx.CrawlTencentUrls;
import tx.crawlTencentUrlsPipeline;
import us.codecraft.webmagic.Spider;
import Tools.Tools;
public class Run {
	
  public static void runnerTencent(){
	  Tools.clearFile("./commenturls.txt");
	  Tools.clearFile("./moviename.txt");
	  String tencentStartUrls ="http://v.qq.com/movielist/10001/0/0/0/0/20/0/0/-1.html";
	  Spider.create(new CrawlTencentUrls()).addUrl(tencentStartUrls).thread(10).run();
	  
  }

}
