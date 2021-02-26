package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһ��������
 */
public class Util {

    /**
     * ���巵�ز���
     * 1.������
     * 2.�ַ���
     * 3.����
     * 4.key���浥�ʣ�value�洢�õ��ʳ��ֵĴ���
     */
    private static int word_num = 0;
    private static int char_num = 0;
    private static int line_num = 0;
    private static HashMap<String,Integer> map = new HashMap<String, Integer>();


    /**
     * 1.����������ʽ��ͳ���ַ���
     *
     * @param character
     * @return
     */
    public static int charNums(String character) {
        // ��д������ʽ��ѯ����
        String regexs = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regexs);
        Matcher matcher = pattern.matcher(character);
        // ����һ��Integerȥ�洢��ĸ��Ŀ
        while (matcher.find()) {
            // �ҵ�һ����ĸ���ۼ�
            char_num++;
        }
        // ƥ����ϣ����ؽ��
        return char_num;
    }

    /**
     * 2.����������ʽ��ͳ�Ƶ�����
     * @param words
     * @return
     */
    public static int wordNums(String words) {
        // ����������ʽ��ȥ���ݿո�����ƪ����
        // temp�ַ������齫�������еĵ���

        String[] temps = words.split("\\s+");

        // �������⣺����ĸ��ͷ�ҳ��ȴ���4�ĵ���
        String regexs = "^[a-zA-Z]{4,}.*";

        // ѭ������������飬����������ʽȥƥ��
        for (String i : temps) {
            if (i.matches(regexs)) {
                // ƥ��ɹ���������1
                word_num++;
            }
        }
        return word_num;
    }


    /**
     * 3.�������ж�ȡʵ�ֻ�ȡ����
     * @param path
     * @return
     */
    public static int lineNums(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // ͨ��ѭ���������ж�ȡ�ļ�
            // ͬʱ��¼��ȡ��������
            while ((line = bufferedReader.readLine()) != null) {
                //ƥ������ǿհ��ַ�
                if (line.length() != 0 && !line.matches("\\s+")) {
                    line_num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line_num;
    }


    /**
     * 4.ͨ��map��ͳ�Ƶ��ʵĳ��ִ���
     * @param words
     * @return
     */
    public static List mapNums(String words){
        String[] temps = words.split("\\s+");
        String regexs = "^[a-zA-Z]{4,}.*";
        for (String i : temps) {
            if (i.matches(regexs)) {
                // map�в��������¼�¼
                if (!map.containsKey(i.toLowerCase())) {
                    map.put(i.toLowerCase(), 1);
                } else {
                    // ���������num+1
                    int num = (int)map.get(i.toLowerCase());
                    map.put(i.toLowerCase(), num + 1);
                }
            }
        }
        List list = new ArrayList<>(map.entrySet());
        // ���������ڲ���
        // ���ȸ���Ƶ�ʱȽϣ����Ƶ����ͬ���Ƚ��ֵ�˳��
        list.sort(new Comparator<Map.Entry>(){
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {

                return (new Integer((Integer) o1.getValue())).compareTo((Integer) o2.getValue()) != 0 ? new Integer((Integer) o2.getValue()).compareTo((Integer) o1.getValue()) : ((String) o1.getKey()).compareTo((String) o2.getKey());
            }
        });
        // ����list ǰʮ�� ���ݣ�Ҳ�����ֵ�ǰʮ�ĸ�Ƶ��
        return list.size() < 10 ? list.subList(0, list.size()) : list.subList(0, 10);
    }



    public static void main(String[] args) {
        System.out.println(Util.mapNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
    }
}
