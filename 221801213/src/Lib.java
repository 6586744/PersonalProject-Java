import java.util.Random;
import java.util.*;
import java.io.*;
import java.util.HashMap;

public class Lib 
{
	HashMap<String,Integer> hashMap=new HashMap<String,Integer>(); //ͳ�ƴ�Ƶ
	
	public int countChar(File file)	//ͳ���ַ���
	{
		int i=0; //��¼�ַ���
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			
			while((ch=(char)bfr.read())!=(char)-1) //���ַ���ȡ�ı�����
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
		int count=0;
		try 
		{		
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
				
			String str;
			while((str=bfr.readLine())!=null)
			{
				char[] c=str.toCharArray();
				for(int i=0;i<c.length;i++) 
				{
					if (c[i]!='\n' && c[i]!='\r' && c[i]!='\t') 
					{
						count++;
						break;
					}
				}
			}
			bfr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
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
			    	addWordToHash(hashMap,strs[i]); //�ǵ��������ͳ���õ�hashMap
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
	
	public void addWordToHash(HashMap<String,Integer> hash,String str) //���ַ�������һ��HashMap
	{
		//ȫ����ΪСд
		str=str.toLowerCase();
		
		if (!hash.containsKey(str)) //�����޴˵���
		{ 
			hash.put(str,1);
		} 
		else //����У��ͽ�������1
		{
			hash.put(str,hash.get(str)+1);
		}
	
	}
	
	public void addWordToTree(TreeMap<String,Integer> tree,String str) //���ַ�������һ��TreeMap
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
	
	public String countWordFrequency(HashMap<String,Integer> hash) //��Ƶ�����
	{
		String result="";
		//����map
		Iterator iterator=hash.keySet().iterator();
		int maxOccur=0;
		while(iterator.hasNext())
		{
			String word=(String) iterator.next();
			if (hash.get(word)>maxOccur)
				maxOccur=hash.get(word);
		}
		//System.out.println("�����֣�"+maxOccur);	
		
		//�����ʼ��TreeMap����
		TreeMap[] wordArray=new TreeMap[maxOccur+1];
		for (int i=0;i<=maxOccur;i++) 
		{
			wordArray[i]=new TreeMap<String,Integer>();
		}
		
		//����TreeMap����
		iterator=hash.keySet().iterator(); //���õ�����
		while(iterator.hasNext())
		{
			String word=(String)iterator.next();
			addWordToTree(wordArray[hash.get(word)],word);
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
					//System.out.println(word+": "+i);
					result+=word+": "+i+"\n";
					num++;
				}
			}
		}
		
		return result;
	}
	
	public void writeToText(File file,String str) //׷��д�� output.txt
	{
		try 
		{	   
			FileWriter fw=new FileWriter(file,true); //�ڶ�������true��Ϊappend��ʽ���ļ�
			BufferedWriter bfw=new BufferedWriter(fw);
			bfw.write(str);
				
			bfw.close();
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void clearText(File file) //��� output.txt
	{
		try 
		{	   
			FileWriter writer=new FileWriter(file); //û�еڶ�������true��Ϊд��ʽ��
			writer.write("");
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void process(File input,File output) //�������
	{
		//���ǰ���ԭ������
		clearText(output);	
		
		//ͳ���ı�
		int countChar1=countChar(input);
		int countWord1=countWord(input);
		int countLine1=countLine(input);
		String countWordFrequency1=countWordFrequency(hashMap); 
		
		//�ϳɽ��
		String result="";
		result+="characters: "+countChar1+"\n";
		result+="words: "+countWord1+"\n";
		result+="lines: "+countLine1+"\n";
		result+=countWordFrequency1;
		
		//������
		writeToText(output,result);
		
	}

}
