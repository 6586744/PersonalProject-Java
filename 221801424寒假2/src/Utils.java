import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһ��������
 */
public class Utils {

    /**
     * ���巵�ز���
     * 1.������
     * 2.�ַ���
     * 3.����
     * 4.key���浥�ʣ�value�洢�õ��ʳ��ֵĴ���
     * 5.�洢�ļ�������Ϣ
     */
    private static int word_num = 0;
    private static int char_num = 0;
    private static int line_num = 0;
    private static HashMap<String, Integer> map = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();


    /**
     * 1.����������ʽ��ͳ���ַ���
     *
     * @param characters
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int charNums(String characters) {
        // ͨ��������ʽ��ƥ��
        String regexs = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regexs);
        Matcher matcher = pattern.matcher(characters);
        while (matcher.find()) {
            // �ҵ�һ����ĸ���ۼ�
            char_num++;
        }
        // ƥ����ϣ����ؽ��
        return char_num;
    }

    /**
     * 2.����������ʽ��ͳ�Ƶ�����
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
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
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static int lineNums(String path) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "\\" + path);
            bufferedReader = new BufferedReader(fileReader);
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
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            e.printStackTrace();
        }
        return line_num;
    }


    /**
     * 4.ͨ��map��ͳ�Ƶ��ʵĳ��ִ���
     *
     * @param words
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static List mapNums(String words) {
        //���ÿո����ָ����ʣ�������������ʽ��ƥ����ȷ�ĵ���
        String[] temps = words.split("\\s+");
        String regexs = "^[a-zA-Z]{4,}.*";
        for (String i : temps) {
            if (i.matches(regexs)) {
                // map�в��������¼�¼
                if (!map.containsKey(i.toLowerCase())) {
                    map.put(i.toLowerCase(), 1);
                } else {
                    // ���������num+1
                    int num = map.get(i.toLowerCase());
                    map.put(i.toLowerCase(), num + 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        // ���������ڲ���
        // ���ȸ���Ƶ�ʱȽϣ����Ƶ����ͬ���Ƚ��ֵ�˳��
        list.sort((Comparator<Map.Entry>) (o1, o2)
                -> ((Integer) o1.getValue()).compareTo((Integer) o2.getValue()) != 0
                ? ((Integer) o2.getValue()).compareTo((Integer) o1.getValue()) : ((String) o1.getKey()).compareTo((String) o2.getKey()));
        // ����list ǰʮ�� ���ݣ�Ҳ�����ֵ�ǰʮ�ĸ�Ƶ��
        return list.size() < 10 ? list.subList(0, list.size()) : list.subList(0, 10);
    }

    /**
     * 5.���ڶ�ȡ�ļ������ַ�
     *
     * @param path
     * @return
     */
    @SuppressWarnings("JavaDoc")
    public static StringBuilder readIn(String path) {
        //�����ļ����ڵ�ǰ��Ŀ��
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(new File("").getAbsolutePath() + "\\" + path));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("�ļ�������");
            e.printStackTrace();
        }
        return stringBuilder;
    }


    /**
     * 6.д����Ϣ
     *
     * @param path
     * @param message
     */
    @SuppressWarnings("JavaDoc")
    public static void writeTo(String path, String message) {
        try {
            //���ɵ��ļ����ڵ�ǰ��Ŀ��
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(new File("").getAbsolutePath() + "\\" + path));
            bufferedWriter.write(message);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getTime() {
        Date newDate = new Date();
        return newDate.getTime();
    }

//    /**
//     * ����
//     *
//     * @param args
//     */
// @SuppressWarnings("JavaDoc")
// public static void main(String[] args) {
//        //System.out.println(args[0]);
//        System.out.println(Utils.wordNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.charNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.mapNums("ssss444 563ff 11d fase11 windows95 windows98 windows2000"));
//        System.out.println(Utils.lineNums(new File("").getAbsolutePath()+"\\1.txt"));
//        System.out.println(new File("").getAbsolutePath());
//        Utils.writeTo("2.txt","hello world");
//        }
}
