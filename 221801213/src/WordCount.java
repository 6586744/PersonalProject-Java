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
		
		File file = new File("test.txt");	
		Lib lib=new Lib();
		
		System.out.println("Lines:"+lib.countLine(file));	//��ȡ����
		System.out.println("Chars:"+lib.countChar(file));	//��ȡ�ַ���
		System.out.println("Words:"+lib.countWord(file));	//��ȡ������
		

	}
}

