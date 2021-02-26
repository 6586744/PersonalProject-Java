import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lib {
	
	public static char toLower(char ch){//ת��ΪСд��ĸ
		if(ch >= 'A' && ch <= 'Z'){
			return (char) ((ch-'A')+'a');
		} 
		return ch;
	}
	
	public static void towords(String word,ArrayList<String> words,ArrayList<Integer> value) {//�����ʼ�������
		int m = 0;
		if(!word.equals(null)) {
			for(int i = 0;i<words.size();i++) {
				if(word.equals(words.get(i).toString())) {
					int y = (int)value.get(i) + 1;
					value.set(i, y);
					m = 1;
				}
			}
			if(m == 0) {
				words.add(word);
				value.add(1);
			}
		}	
	}
	
	public static void sortWords(ArrayList<String> words,ArrayList<Integer> value) {//��Ƶ�ʴӴ�С����
		for(int i = 0;i<words.size()-1;i++) {
			for(int j = 1;j<words.size();j++) {
				if(value.get(i)<value.get(j)) {
					int x = value.get(i);
					int y = value.get(j);
					value.set(j, x);
					value.set(i, y);
					String word1 = words.get(i).toString();
					String word2 = words.get(j).toString();
					words.set(j, word1);
					words.set(i, word2);
				}
			}
		}
	}
	
	public static int countWords(ArrayList<Integer> value) {//ͳ���ܵ�����
		int countnum = 0;
		for(int i = 0;i<value.size();i++) {
			countnum+=value.get(i);
		}
		return countnum;
	}
	
	   public static int countLines(File file) {//ͳ����Ч����
	        int countline = 0;
	        try {
	            Scanner in = new Scanner(file);
	            while (in.hasNextLine()) {
	                String str = in.nextLine();
	                if (!str.equals("")) {
	                    countline++;
	                }
	            }
	            in.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("��ȡʧ��");
	            e.printStackTrace();
	        }
	        return countline;
	    }
}
