package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһ��������
 */
public class Util {

    /**
     * ���巵�ز���
     */
    private static int word_num = 0;
    private static int char_num = 0;
    private static int line_num = 0;
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
    public static int linesNumber(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // ͨ��ѭ���������ж�ȡ�ļ�
            // ͬʱ��¼��ȡ��������
            while ((line = bufferedReader.readLine().strip(' ')) != null) {
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
}
