import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class WordCount {
	public static void main(String[] args) throws IOException{
		File f1=new File(args[0]);
		FileWriter writer=new FileWriter(args[1]);
		
		//ͳ���ļ����ַ�������Ӧ�����һ�У���ֻ��Ҫͳ��Ascii�룬���ֲ��迼��,�ո�ˮƽ�Ʊ�������з��������ַ�
		try{
			int num=getCharNum(f1);
			writer.write("characters��"+num);
			writer.write("\n");
			System.out.println("characters��"+num);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}
		
		//ͳ���ļ�����Ч��������Ӧ��������У����κΰ����ǿհ��ַ����У�����Ҫͳ�ơ�
		try{
			int line=getLine(f1);
			writer.write("lines��"+line);
			writer.close();
			System.out.println("lines��"+line);
		}
		catch(IOException exc){
			System.out.println("File error!");
		}

	}
	
	//ʵ��ͳ���ļ����ַ����Ĺ���
	public static int getCharNum(File file) throws FileNotFoundException {
		InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
		int num=0;
		try {
			while(inputStreamReader.read()!=-1)
			{
				num++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	//ʵ��ͳ���ļ������Ĺ���
	public static int getLine(File file){
		int line=0;
		try {
			if(file.exists()) {
				long fileLength=file.length();
				LineNumberReader lineNumberReader=new LineNumberReader(new FileReader(file));
				lineNumberReader.skip(fileLength);
				line=lineNumberReader.getLineNumber();
				lineNumberReader.close();
			}
			else
				System.out.println("file does not exist.");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
}
