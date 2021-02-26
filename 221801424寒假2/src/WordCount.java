import java.util.List;

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
        stringBuilder.append("characters:").append(Utils.charNums(characters)).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ�����ַ���successful,��ʱ"+time+"����");
        begintime=Utils.getTime();
        stringBuilder.append("words:").append(Utils.wordNums(handleCharacters)).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ���е�����successful,��ʱ"+time+"����");
        begintime=Utils.getTime();
        stringBuilder.append("lines:").append(Utils.lineNums("test.txt")).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ����successful,��ʱ"+time+"����");
        List list;
        begintime=Utils.getTime();
        list=Utils.mapNums(handleCharacters);
        StringBuilder mapStringbuild=new StringBuilder();
        for(int i=1;i<=list.size();i++){
            mapStringbuild.append(list.get(i - 1)).append("\n");
        }
        String mapWord=mapStringbuild.toString().replace("=",":");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("��ȡ���д�Ƶsuccessful,��ʱ"+time+"����");
        System.out.println(stringBuilder+mapWord);
        begintime=Utils.getTime();
        Utils.writeTo("result.txt", (stringBuilder+mapWord));
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("д���ļ�successful,��ʱ"+time+"����");
    }
}