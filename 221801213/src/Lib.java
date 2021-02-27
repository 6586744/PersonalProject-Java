import java.util.Random;
import java.util.*;
import java.io.*;

public class Lib 
{
	public int countChar(File file)	//ͳ���ַ���
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
			return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void showChar(File file) //չʾ�����ַ�
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			char ch;
			while((ch = (char) bfr.read()) != (char)-1)//���ַ���ȡ�ı�����
			{
					System.out.print(ch);
					System.out.print('|');
			}
			bfr.close();
			fr.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public int countLine(File file) //ͳ������
	{
		try 
		{		
			FileReader fr = new FileReader(file);
			BufferedReader bufr = new BufferedReader(fr);
			int i = 0;	//����
			while(bufr.readLine() != null)
			{
				i++;//�ۼ�����
			}
			bufr.close();
			fr.close();		
			return i;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int countWord(File file) //ͳ�Ƶ�����
	{
		try
		{
			FileReader fr=new FileReader(file);
			BufferedReader bfr=new BufferedReader(fr);
			char ch;
			int countStart=0,countEnd=0; //�����ַ���¼
			int countWords=0;
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
				}
				
				if (isSeparator(ch))
				{
					if (str.length()>=4) //�γɵ��ʣ�������һ
					{
						countWords++;
						System.out.println(str);
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
				System.out.println(str);
			}
			
			
			bfr.close();
			fr.close();
			
			return countWords;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println();
		
		return 0;
	}

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
		if (ch >= '1' && ch <= '9')
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
}
