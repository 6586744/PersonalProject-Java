import java.util.Random;
import java.util.*;
import java.io.*;

public class WordCount
{
	public WordCount()
	{
	}
	
	public static void main(String args[])
	{
		
		File file = new File("input.txt");	
		Lib lib=new Lib();
		
		System.out.println("characters: "+lib.countChar(file));	//��ȡ�ַ���
		lib.countWord(file);	//��ȡ������
		System.out.println("lines: "+lib.countLine(file));	//��ȡ����
		lib.countWordFrequency(file);	//��ȡ����Ƶ��
		

	}
}

