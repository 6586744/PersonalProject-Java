import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
	HashMap<String,Integer> has=new HashMap<String,Integer>(); //ͳ�ƴ�Ƶ
	
	public int countChar(File file)	//ͳ���ַ���
	{
		int i=0; //��¼�ַ���
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			while((ch=(char)bfr.read()) != (char)-1) //���ַ���ȡ�ı�����
			{
				if(ch>=0 && ch<=127) 
				{
		               i++; //�ۼ��ַ���
		        }	
			}
			
			bfr.close();
			fr.close();		
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public int countLine(File file) //ͳ������
	{
		int i=0;
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bufr=new BufferedReader(fr);
				
			String str;
			while((str=bufr.readLine())!=null)
			{
				if (!str.equals("")) 
					i++;
			}
			bufr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return i;
	}
	
	public int countWord(File file) //ͳ�Ƶ��ʳ���Ƶ��
	{
		
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			String str="";
			while((ch=(char)bfr.read()) != (char)-1)//���ַ���ȡ�ı�����
			{						
				str+=ch;
			}
			
			String[] strs=str.split("[^a-zA-Z0-9]"); //���ı����ݰ��ָ����ֿ��������ַ���
			
	        for(int i=0;i<strs.length;i++) 
	        {
	            if(strs[i].matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) //�����ж��ַ����Ƿ��ǵ���
	            {
	            	addWordToHash(has,strs[i]); //�ǵ��������ͳ���õ�hashMap
	            	countWords++; //������һ
	            }
	        }
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return countWords;
	}
	
	public void addWordToHash(HashMap<String,Integer> has,String str)
	{
		//ȫ����ΪСд
		str=str.toLowerCase();
		
		if (!has.containsKey(str)) //�����޴˵���
		{ 
			has.put(str,1);
		} 
		else //����У��ͽ�������1
		{
			has.put(str,has.get(str)+1);
		}

	}

	public void addWordToTree(TreeMap<String,Integer> tree,String str)
	{
		if (!tree.containsKey(str)) //�����޴˵���
		{ 
			tree.put(str,1);
		} 
		else //����У��ͽ�������1
		{
			tree.put(str,tree.get(str)+1);
		}

	}
	
	public void outputWords(HashMap<String,Integer> has,File output) //��Ƶ�����
	{
	 
		//����map
		Iterator iterator=has.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word=(String) iterator.next();
			if (has.get(word)>maxOccur)
				maxOccur=has.get(word);
		}
		//System.out.println("�����֣�"+maxOccur);	
		
		//�����ʼ��TreeMap����
		TreeMap[] wordArray=new TreeMap[maxOccur+1];
        for (int i=0;i<=maxOccur;i++) 
        {
        	wordArray[i]=new TreeMap<String,Integer>();
        }
        
        //����TreeMap����
        iterator=has.keySet().iterator(); //���õ�����
		while(iterator.hasNext())
		{
			String word=(String)iterator.next();
			addWordToTree(wordArray[has.get(word)],word);
		}
		
		//����TreeMap����
		int num=1;
		for (int i=maxOccur;i>0;i--) 
        {
        	iterator=wordArray[i].keySet().iterator();
        	while(iterator.hasNext())
    		{
    			String word=(String)iterator.next();
    			if (num<10)
    			{
    				System.out.println(word+": "+i);
    				writeToText(output,word+": "+i+"\n");
    				num++;
    			}
    		}
        }
		
		
	}
	
	public void writeToText(File file,String str) 
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file, true);
			writer.write(str);
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void clearText(File file) //clear output.txt
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file);
			writer.write("");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void process(File input,File output)
	{
		clearText(output);
		
		int countChar1=countChar(input);
		int countWord1=countWord(input);
		int countLine1=countLine(input);
		
		System.out.println("characters: "+countChar1);
		System.out.println("words: "+countWord1);
		System.out.println("lines: "+countLine1);
		
		writeToText(output,"characters: "+countChar1+"\n");
		writeToText(output,"words: "+countWord1+"\n");
		writeToText(output,"lines: "+countLine1+"\n");
		
		outputWords(has,output);
	}


}
