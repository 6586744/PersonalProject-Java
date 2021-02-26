import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

class WordCount{
    public static void main(String []args){
        StringBuilder stringBuilder=new StringBuilder();
        long begintime = Utils.getTime();
        StringBuilder tempStringbuild=Utils.readIn("test.txt");
        long endTime = Utils.getTime();
        long time = endTime - begintime;
        System.out.println("����successful,��ʱ"+time+"����");
        //���������ַ������ⱻ����Ϊ�ǵ��ʵ�һ����
        String handleCharacters = tempStringbuild.toString()
                .replace(".", " ").replace(",", " ")
                .replace("!", " ").replace("?", " ");
        String characters=tempStringbuild.toString();
        begintime=Utils.getTime();
        stringBuilder.append("characters:"+Utils.charNums(characters)+"\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ�����ַ���successful,��ʱ"+time+"����");
        begintime=Utils.getTime();
        stringBuilder.append("words:"+Utils.wordNums(handleCharacters)+"\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ���е�����successful,��ʱ"+time+"����");
        begintime=Utils.getTime();
        stringBuilder.append("lines:"+Utils.lineNums("test.txt")+"\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ����successful,��ʱ"+time+"����");
        List<Map.Entry<String, Integer>> list;
        begintime=Utils.getTime();
        list=Utils.mapNums(handleCharacters);
        StringBuilder mapStringbuild=new StringBuilder();
        for(int i=1;i<=list.size();i++){
            mapStringbuild.append(list.get(i-1)+"\n");
        }
        String mapWord=mapStringbuild.toString().replace("=",":");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ���д�Ƶsuccessful,��ʱ"+time+"����");
        System.out.println(stringBuilder+mapWord);
        begintime=Utils.getTime();
        Utils.writeTo("result.txt",(stringBuilder+mapWord).toString());
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("д���ļ�successful,��ʱ"+time+"����");
    }
}