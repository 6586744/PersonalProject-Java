package word.count.pro;

import java.io.*;
import java.util.Scanner;
import java.lang.String;
    
public class WordCount {
	public static void main(String[] args) {
		//��ȡ�ļ���
		Scanner input = new Scanner(System.in);
		String inputStr = input.nextLine();
		input.close();
        String[] files = inputStr.split(" "); 
        String inputFile = files[0];
        String outputFile = files[1];
		//ͨ����װ���Ĺ��ܵĽӿڼ���
        CoreCount coreCount = new CoreCount(inputFile);
        coreCount.Count();
        /* �����ã�
        System.out.print(coreCount.getCharCount());
        */
        //��������ļ�
		File file = new File(outputFile);
		if (file.exists()) {
			System.out.print("File \"" + outputFile + "\" already exist.");
		}
		try {
			file.createNewFile();
		} catch (IOException e){			
		}
    }
}
