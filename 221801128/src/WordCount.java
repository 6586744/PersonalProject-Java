
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCount {
	static String inputfile;
	static String outputfile;
	static int count = 0;
	static int num = 0;
	
	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String[] strArr = input.split(" ");
		inputfile = strArr[0];
		outputfile = strArr[1];
		in.close();
		
		File dir = new File("");
		inputfile = dir.getCanonicalPath()+"\\"+inputfile;
		outputfile = dir.getCanonicalPath()+"\\"+outputfile;
		System.out.println("��ȡ�ļ��ĵ�ַ��"+inputfile);
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lib.solve(br);
		br.close();
		Lib.sortWords();//����Ӵ�С
		Lib.printall(inputfile);
		//д��ָ���ļ�
		Lib.writeIn(inputfile, outputfile);   
	}
}


