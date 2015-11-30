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

public class crawlTencentUrlsPipeline  implements Pipeline
{

	public void process(ResultItems resultItems, Task task)
	{
		
		
	
		/**
		 * 抓到的moviename和urls
		 * 
		 */
		List<String> commenturl = new ArrayList<String>();
		List<String> moviename = new ArrayList<String>();
		List<String> movieid =new ArrayList<String>();
		FileWriter fwName = null;
		FileWriter fwCommentUrl = null;
	try{
		
		fwCommentUrl = new FileWriter("./commenturls.txt",true);	
		fwName = new FileWriter("./moviename.txt",true);
		//System.out.println("对应的页面: " + resultItems.getRequest().getUrl());
		for(Map.Entry<String, Object> entry : resultItems.getAll().entrySet())
		{	
		//System.out.println(entry.getKey() + ":\t" + entry.getValue());
			String key = entry.getKey();
			if(key.equals("commentUrls"))
			{	
				//System.out.println(entry.getValue());
				//commenturl.add((String)entry.getValue());
				fwCommentUrl.write(entry.getValue().toString());
				fwCommentUrl.write("\n");	
				fwCommentUrl.flush();
			
			} 
			else if(key.equals("movieName"))
			{	
				//System.out.println(entry.getValue());
				//moviename.add((String)entry.getValue());
				 moviename=(List<String>)entry.getValue();	
				 for(int i=0 ; i<moviename.size();i++)
				 {
					 System.out.println(moviename.get(i));
						fwName.write(moviename.get(i));
						fwName.write("\n");	
						fwName.flush();
						
				 
				 }
			 }	 				
			}	
		fwCommentUrl.close();
		fwName.close();
		}	
		/*try
		{
			
			//System.out.println("success!");
			//System.out.println("size1 :"+commenturl.size()+" size2:"+name.size());
														
			 if(moviename.size()!=0){
					for(int i=0;i<moviename.size();i++)
					{	
						System.out.println(moviename.get(i));
						fwName.write(moviename.get(i));
						fwName.write("\n");	
						fwName.close();
					}}
			 
			if(commenturl.size()!=0){
			for(int j=0;j<commenturl.size();j++)
			{
				fwCommentUrl.write(commenturl.get(j).toString());
				fwCommentUrl.write("\n");	
				fwCommentUrl.close();
				
			}
		}
	}*/


			catch(Exception e) {
				e.printStackTrace();
			}
		
	
	}
		
	
}
