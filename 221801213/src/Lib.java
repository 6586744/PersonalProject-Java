import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
	public boolean isLetter(char ch) //�ж��ǲ�����ĸ
	{
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
		{
			return true;
		}
		return false;
	}
	
	public boolean isNumber(char ch) //�ж��ǲ�������
	{
		if (ch >= '0' && ch <= '9')
		{
			return true;
		}
		return false;
	}
	
	public boolean isSeparator(char ch) //�ж��ǲ��Ƿָ���
	{
		if ( ch == ' ' || (!isLetter(ch) && !isNumber(ch)) )
		{
			return true;
		}
		return false;
	}

	public void countChar(File file,File output)	//ͳ���ַ���
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			int i = 0; //��¼�ַ���
			while((ch = (char) bfr.read()) != (char)-1) //���ַ���ȡ�ı�����
			{
					i++; //�ۼ��ַ���
			}
			
			bfr.close();
			fr.close();		
			
			System.out.println("characters: "+i);
			writeToText(output,"characters: "+i+"\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void countLine(File file,File output) //ͳ������
	{
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bufr=new BufferedReader(fr);
			int i=0;	
			String str;
			while((str=bufr.readLine())!=null)
			{
				if (!str.equals("")) 
					i++;
			}
			bufr.close();
			fr.close();		
			System.out.println("lines: "+i);
			writeToText(output,"lines: "+i+"\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void countWord(File file,File output) //ͳ�Ƶ�����
	{
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			String str="";
			
			while((ch = (char) bfr.read()) != (char)-1)//���ַ���ȡ�ı�����
			{						
				if (isLetter(ch)) //������ĸ�ͼ���
				{
					str+=ch;	
				}
				
				if (isNumber(ch))	
				{
					if (str.length()>=4) //��4����ĸ��ͷʱ�ſ�ʼ��¼����
						str+=ch;
					else
					{
						str=""; //���γɵ��ʣ����
					}
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //�γɵ��ʣ�������һ
					{
						countWords++;
						//System.out.println(str);
						str="";
					}
					else
					{
						str=""; //���γɵ��ʣ����
					}
				}
				
			}
			
			if(str.length()>=4) //ͳ�����һ������
			{
				countWords++;
				//System.out.println(str);
			}
			
			
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		System.out.println("words: "+countWords);
		writeToText(output,"words: "+countWords+"\n");
	}
	
	public void countWordFrequency(File file,File output) //ͳ�Ƶ��ʳ���Ƶ��
	{
		HashMap<String,Integer> has=new HashMap<String,Integer>(); //ͳ�ƴ�Ƶ
		int countWords=0;
		
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			String str="";
			
			while((ch = (char) bfr.read()) != (char)-1)//���ַ���ȡ�ı�����
			{						
				if (isLetter(ch)) //������ĸ�ͼ���
				{
					str+=ch;	
				}
				
				if (isNumber(ch))	
				{
					if (str.length()>=4) //��4����ĸ��ͷʱ�ſ�ʼ��¼����
						str+=ch;
					else
					{
						str=""; //���γɵ��ʣ����
					}
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //�γɵ��ʣ�������һ
					{
						countWords++;
						addWordToHash(has,str);
						str="";
					}
					else
					{
						str=""; //���γɵ��ʣ����
					}
				}			
			}
			
			if(str.length()>=4) //ͳ�����һ������
			{
				countWords++;
				addWordToHash(has,str);
			}			
			
			bfr.close();
			fr.close();
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}

		outputWords(has,output); //��HashMap���������
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
		Iterator iterator = has.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word = (String) iterator.next();
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
        iterator = has.keySet().iterator(); //���õ�����
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
			FileWriter writer = new FileWriter(file, true);
			writer.write(str);
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void clearText(File file) //output.txt
	{
		try 
		{	   
			FileWriter writer = new FileWriter(file);
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
		
		countChar(input,output);	//
		countWord(input,output);	//
		countLine(input,output);	//
		countWordFrequency(input,output);	//
	}
}
