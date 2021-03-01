
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Lib {
	
	static int readline;
	static int len = 0;
	static String word = "";
	static int x = 0;//ǰһλ�����ֱ�1
	static int num = 0;
	static HashMap<String, Integer> hash=new HashMap<String,Integer>();
		
	/**
	 * ת��ΪСд��ĸ
	 */
	public static char toLower(char ch){
		if(ch >= 'A' && ch <= 'Z'){
			return (char) ((ch-'A')+'a');
		} 
		return ch;
	}
	
	/**
	 * �ж��Ƿ�������Ч����
	 */
	public static boolean isWord(String word){
        String pattern = "[a-z]{4}.*";
        boolean isMatch = Pattern.matches(pattern,word);
        return isMatch;
    }
	
	/**
	 * ���浥��
	 */
	 public static void saveWord(String inputfile){
		 BufferedReader br = null;
	     try {
	         br = new BufferedReader(new InputStreamReader(new FileInputStream(inputfile)));
	     } catch (FileNotFoundException e) {
	            System.out.println("δ�ҵ�Ŀ���ļ�," + "��ǰ�ļ�λ��" + inputfile);
	     }
	     String str = null;

	     while(true){
	         try {
	             if (!((str= br.readLine())!=null)) break;
	         } catch (IOException e) {
	             System.out.println("�ļ���ȡ��������");
	         }
	         String[] splitStr = str.split("[^0-9a-zA-Z]");
	         for(int i=0;i<splitStr.length;i++){
	             splitStr[i] = splitStr[i].toLowerCase();
	             if(isWord(splitStr[i])){
	                 if(hash.get(splitStr[i])==null){
	                     hash.put(splitStr[i],1);
	                 }else{
	                     hash.put(splitStr[i],hash.get(splitStr[i])+1);
	                 }
	             }
	         }
	     }
	     try {
	         br.close();
	     } catch (IOException e) {
	         System.out.println("�ļ���ȡδ����ȷ�ر�");
	     }
    }
	/**
	 * ͳ�����ַ���
	 */
	public static int countChar(String inputfile) throws IOException {
		int charCount = 0;
		int ch = 0;
		BufferedReader br = new BufferedReader(new FileReader(inputfile));
		while((ch=br.read())!=-1){
			if(ch<128 && ch>0)
				charCount++;
		}
		br.close();
		return charCount;
	}
	 
	
	/**
	 * ͳ���ܵ�����
	 */
		public static int countWords() {
			int countnum = 0;
			Set<Map.Entry<String, Integer>> set=hash.entrySet();
			Iterator<Map.Entry<String, Integer>> it=set.iterator();
			while(it.hasNext()){
				Map.Entry<String, Integer> e=it.next();
				countnum+=e.getValue();
			}
			return countnum;
		}
		
		/**
		 * ͳ����Ч����
		 * @throws IOException 
		 */
		   public static int countLines(String inputfile) throws IOException {
			   BufferedReader br = new BufferedReader(new FileReader(inputfile));
		        String str = null;
		        String pattern = "[^ ].*";
		        int countLine = 0;

		        while((str= br.readLine())!=null){
		            if(str.matches(pattern)){
		                countLine++;
		            }
		        }
		        br.close();
		        return countLine;
		    }
	
	/**
	 * ����Ƶ�Լ��ֵ�������
	 */
	public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String,Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> wordOfList = new ArrayList<>(wordMap.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = (cmp1, cmp2) -> {
            if(cmp1.getValue().equals(cmp2.getValue()))
                return cmp1.getKey().compareTo(cmp2.getKey());
            return cmp2.getValue()-cmp1.getValue();
        };
        wordOfList.sort(cmp);
        return wordOfList;
    }
	


	  /**
	   * ��ӡ
	 * @throws IOException 
	   */
	 public static void printall(String inputfile) throws IOException {
		 StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
	                + "words: " + Lib.countWords() + "\n"
	                + "lines: " + Lib.countLines(inputfile) + "\n");
		 int cnt = 0;
		 List<HashMap.Entry<String, Integer>> List = getSortedList(hash);
	        for(HashMap.Entry<String,Integer> entry:List) {
	            if(cnt<=9){
	                str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
	            }
	            cnt++;
	        }
	        System.out.print(str.toString());
			
	 }
	 
	 /**
	  * д��ָ���ļ�
	  */
	 public static void writeIn(String inputfile,String outputfile) throws IOException {
		 Path path1 = Paths.get(outputfile);
		BufferedWriter writer = null;
		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
                + "words: " + Lib.countWords() + "\n"
                + "lines: " + Lib.countLines(inputfile) + "\n");
		int cnt = 0;
		try {
			writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<HashMap.Entry<String, Integer>> List = getSortedList(hash);
        for(HashMap.Entry<String,Integer> entry:List) {
            if(cnt<=9){
                str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            cnt++;
        }
        writer.write(str.toString());
		writer.close();
	 }
}
