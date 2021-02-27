import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.List;

class WordCount {
    public static void main(String[] args) throws IOException {
        /**
         * ֱ������
         */
        if (args.length != 2) {
            System.out.println("��ʽ����,ʹ��Ĭ���ļ�(input.txt),����(output.txt)");
            String readPath = "input.txt";
            String writePath = "output.txt";
//            long begintime = Utils.getTime();
            //����һ��input.txt�ļ�
            Filetest File = new Filetest();
            File.fileTest();

            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder tempStringbuild = Utils.readIn(readPath);
            //���������ַ������ⱻ����Ϊ�ǵ��ʵ�һ����
            String handleCharacters = tempStringbuild.toString()
                    .replace(".", " ").replace(",", " ")
                    .replace("!", " ").replace("?", " ");
            String characters = tempStringbuild.toString();
            stringBuilder.append("characters:").append(Utils.charNums(characters)).append("\n");
            stringBuilder.append("words:").append(Utils.wordNums(handleCharacters)).append("\n");
            stringBuilder.append("lines:").append(Utils.lineNums(readPath)).append("\n");

            List list;
            list = Utils.mapNums(handleCharacters);
            StringBuilder mapStringbuild = new StringBuilder();
            for (int i = 1; i <= list.size(); i++) {
                mapStringbuild.append(list.get(i - 1)).append("\n");
            }
            String mapWord = mapStringbuild.toString().replace("=", ":");

            System.out.println(stringBuilder + mapWord);

            Utils.writeTo(writePath, (stringBuilder + mapWord));
//            long endTime = Utils.getTime();
//            long time = endTime - begintime;
//            System.out.println("���гɹ�,��ʱ" + time + "����");
        }
        /**
         * ��������������
         */
        else {
            String readPath = args[0];
            String writePath = args[1];
//            long begintime = Utils.getTime();
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder tempStringbuild = Utils.readIn(readPath);
            //���������ַ������ⱻ����Ϊ�ǵ��ʵ�һ����
            String handleCharacters = tempStringbuild.toString()
                    .replace(".", " ").replace(",", " ")
                    .replace("!", " ").replace("?", " ");
            String characters = tempStringbuild.toString();
            stringBuilder.append("characters:").append(Utils.charNums(characters)).append("\n");
            stringBuilder.append("words:").append(Utils.wordNums(handleCharacters)).append("\n");
            stringBuilder.append("lines:").append(Utils.lineNums(readPath)).append("\n");
            List list;
            list = Utils.mapNums(handleCharacters);
            StringBuilder mapStringbuild = new StringBuilder();
            for (int i = 1; i <= list.size(); i++) {
                mapStringbuild.append(list.get(i - 1)).append("\n");
            }
            String mapWord = mapStringbuild.toString().replace("=", ":");
            System.out.println(stringBuilder + mapWord);
            Utils.writeTo(writePath, (stringBuilder + mapWord));
//            long endTime = Utils.getTime();
//            long time = endTime - begintime;
//            System.out.println("���гɹ�,��ʱ" + time + "����");
        }

    }
}