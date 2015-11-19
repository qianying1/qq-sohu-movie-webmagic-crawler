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

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class crwalTencentUrlsPipeline  implements Pipeline
{

	public void process(ResultItems resultItems, Task task)
	{
		
		/**
		 * 抓到的moviename和urls
		 * 
		 */
		
		List<String> moviename = new ArrayList<String>();
		List<String> commenturl = new ArrayList<String>();
		FileWriter fwName = null;
		FileWriter fwCommentUrl = null;
		System.out.println("对应的页面: " + resultItems.getRequest().getUrl());
		
		for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet())
		{
			String key = entry.getKey();
			if(key.equals(CrawlTencentUrls.MOVIENAME))
			{
				moviename =(List<String>) entry.getValue();
			} else if(key.equals(CrawlTencentUrls.COMMENTURLS))
			{
				
				commenturl = (List<String>)entry.getValue();	
				//System.out.println(commenturl);
			}
			try{
				fwName = new FileWriter("./moviename.txt",true);
				fwCommentUrl = new FileWriter("./commenturls.txt",true);
				for(int i=0;i<moviename.size();i++)
				{
					fwName.write(moviename.get(i));
					fwName.write("\n");					
					fwCommentUrl.write(commenturl.get(i));
					fwCommentUrl.write("\n");
				}
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
}
