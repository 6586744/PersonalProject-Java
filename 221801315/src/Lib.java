import java.io.*;

/**
 * 功能：WordCount中main方法要调用的函数
 * 作者：张福荣
 * 学号：221801315
 * 邮箱：784536133@qq.com
 * 创建时间：2021/2/28 15:22
 * 最后修改时间：2021/3/1 15:04
 */
public class Lib {
    /* 检测所给文件路径是否有效，输入文件不存在则抛出异常，输出文件不存在则创建
       输入参数：输入文件路径inFilePath，输出文件路径outFilePath
       返回值：空
       异常：输入文件不存在异常FileNotFoundException*/
    public static void checkFileValid(String inFilePath, String outFilePath) throws FileNotFoundException {
        File inFile = new File(inFilePath);
        //当输入文件不存在时，打印提示信息，抛出异常
        if (!inFile.exists()) {
            System.out.println("File " + inFilePath + " doesn't exist, program will exit.");
            throw new FileNotFoundException();
        }

        File outFile = new File(outFilePath);
        //当输出文件不存在时，创建文件
        if (!outFile.exists()) {
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* 统计输入文件中的字符总数
       输入参数：输入文件路径inFilePath
       返回值：字符总数count */
    public static int countTotalChar(String inFilePath) {
        int count = 0;    //记录字符总数
        int temp;

        //测试文件不会出现ASCII码以外的字符，因此只需统计文件内容+换行+回车的长度即可
        try {
            Reader in = new InputStreamReader(new FileInputStream(inFilePath));
            //读取文件，直到文件结束
            while ((temp = in.read()) != -1) {
                ++count;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /* 统计输入文件中的单词总数
       输入参数：输入文件路径inFilePath
       返回值：单词总数count */
    public static int countTotalWord(String inFilePath) {
        int count = 0;    //记录单词总数
        int letterCount = 0;     //字母数

        try {
            Reader in = new InputStreamReader(new FileInputStream(inFilePath));
            int temp = in.read();  //记录读取的字符
            //读取文件，直到文件结束
            while (temp != -1) {
                if (isLetter((char) temp))
                    ++letterCount;
                else {
                    //是单词，则继续读取直到分隔符
                    if (letterCount >= 4) {
                        while (!isSeparator((char) temp)) {
                            temp = in.read();
                        }
                        ++count;
                        letterCount = 0;
                    } else {
                        //发现不是单词后，直接跳到分隔符，开始判断下一个单词
                        while (!isSeparator((char) temp)) {
                            temp = in.read();
                        }
                        letterCount = 0;
                    }
                }
                temp = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /* 判断字符是否是字母
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isLetter(char t) {
        if ((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z'))
            return true;
        return false;
    }

    /* 判断字符是否是数字
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isDigit(char t) {
        if (t >= '0' && t <= '9')
            return true;
        return false;
    }

    /* 判断字符是否是分隔符
       输入参数：字符t
       返回值：判断结果的bool值 */
    public static boolean isSeparator(char t) {
        if (isLetter(t))
            return false;
        else if (isDigit(t))
            return false;
        return true;
    }
}
