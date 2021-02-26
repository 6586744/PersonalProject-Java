
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCount {
	static String inputfile;
	static String outputfile;

	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<Integer> value = new ArrayList<Integer>();
	
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
		System.out.println(inputfile);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int readline;
		int len = 0;
		String word = "";
		int x = 0;//ǰһλ�����ֱ�1
		int numOfLine = 1;
		
		while((readline = br.read())!= -1) {
			char c =Lib.toLower((char)readline);
			if(!String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
				num++;
			}
			if(c == '\n') {
				numOfLine++;
			}
			if(len<4) {
				if(c>='a' && c<='z') {
					if(x == 0) {
						word+=c;
						len+=1;
					}
					else {
						len = 0;
						word = "";
						x = 0;
					}
				}
				else if(c>='0' && c<='9'){
					len = 0;
					word = "";
					x = 1;
				}
				else {
					len = 0;
					word = "";
					x = 0;
				}
				
				
			}
			else if(len>=4){
				if(c>='a' && c<='z') {
					if(x == 0) {
						word+=c;
						len+=1;
					}
					else {
						Lib.towords(word,words,value);
						word = "";
						len = 0;
						x = 0;
					}
				}
				else if(c>='0' && c<='9') {
					if(x == 0) {
						x = 1;
						word = word+""+c;
						len+=1;
					}
					else {
						word = word+""+c;
						len+=1;
					}
				}
				else if(!((c>='a' && c<='z')||(c>=0 && c<=9))) {
					Lib.towords(word,words,value);
					word = "";
					len = 0;
					x = 0;
				}
			}
		}
		if(readline == -1 && len>=4) {
			Lib.towords(word,words,value);
		}
		br.close();
		System.out.println(num);//������ַ���
		Lib.sortWords(words,value);//����Ӵ�С
		for(int i = 0;i<words.size();i++) {
			if(i>=10) {
				break;
			}
			System.out.println(words.get(i).toString()+""+":"+value.get(i));
		}
		
		//д��ָ���ļ�
		Path path1 = Paths.get(outputfile);
		BufferedWriter writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		writer.write("�ַ���:"+num+"\n");//д�����ַ���
		writer.write("��������:"+words.size()+"\n");//д�뵥������
		writer.write("��Ч����:"+numOfLine+"\n");//д��������      
		writer.write("���ʵĳ��ִ���:"+"\n");
		for(int i = 0;i<words.size();i++) {//д��Ƶ��ǰʮ�ĵ��ʼ�Ƶ��
			if(i>=10) {
				break;
			}
			writer.write(words.get(i).toString()+""+":"+value.get(i)+"\n");
		}
		writer.close();   
	}
}


