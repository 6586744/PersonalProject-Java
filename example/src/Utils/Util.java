package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ṩһ��������
 */
public class Util {


    private static int word_num = 0;
    private static int char_num = 0;

    /**
     * ����������ʽ��ͳ���ַ���
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

    
}
