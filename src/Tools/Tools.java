package Tools;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tools {
	
	public static void clearFile(String filename)
	{
		File file = new File(filename);
		try {
		if(!file.exists())
		{
		file.createNewFile();
		}
		FileWriter filewriter =new FileWriter(file);
		filewriter.write("");
		filewriter.flush();
		filewriter.close();
		
		}catch(IOException e){
			e.printStackTrace();	
		}
		
	}
}

