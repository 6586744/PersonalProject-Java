package wordcount_java;
import java.io.*;
import java.util.*;

public class Lib 
{

    static Map<String,Integer> wordsMap=new HashMap<>();


    
    //��ȡ�ļ�����������
    public static String charactersCount(String filePath)
    {

        BufferedReader bufferedReader=null;
        StringBuilder str=null;

        try 
        {
            FileReader reader=new FileReader(filePath);
            bufferedReader=new BufferedReader(reader);
            str=new StringBuilder();
            int flag;
            //str.append(bufferedReader.read());
            while ((flag=bufferedReader.read())!=-1)
            {
                str.append((char)flag);
            }
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }finally 
        {
            try 
            {
                bufferedReader.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        return str.toString();
    }

    
    //��������ͳ��
    public static int charactersNumberCount(String str)
    {

        int num=0;

        char[] temp=str.toCharArray();
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i]>=0&&temp[i]<=127)
            {
                num++;
            }
        }
        num++;
        //num=str.length();

        return num;
    }
    
    
    //��������ͳ��
    static int wordsNumberCount(String str)
    {

        int num=0;
        
        //�������ַ��÷ָ����ֿ�
        String[] word=str.split("\\s+");
        
        //�ж��Ƿ�Ϊ���ʵ�����
        String strings="^[a-zA-Z]{4,}.*";
        for(int i=0;i<word.length;i++)
        {
            if(word[i].matches(strings))
            {
                num++;
                
                //ת��ΪСд
                String insertKey=word[i].toLowerCase();
                if (wordsMap.containsKey(insertKey))
                {
                    int j=wordsMap.get(insertKey);
                    wordsMap.put(insertKey,j+1);
                }
                else 
                {
                    wordsMap.put(insertKey,1);
                }
            }
        }

        return num;
    }

    //������Ƶ�ʽ�������
    public List<Map.Entry<String,Integer>> SortMap()
    {

        List<Map.Entry<String,Integer>> wordList=new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());

        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() 
        {
            @Override
            public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) 
            {
                if (map1.getValue().equals(map2.getValue()))
                {
                    return map1.getKey().compareTo(map2.getKey());
                }
                else 
                {
                    return map2.getValue()-map1.getValue();
                }
            }
        });

        return wordList;
    }


    static int linesNumberCount(String filePath)
    {
    	File file=new File(filePath);
    	int count=0;
    	
    	if(file.exists()) 
    	{
    		try
    		{
    			BufferedReader in = new BufferedReader(new FileReader(file));
    			String line;
    			while((line = in.readLine()) != null)
    			{
    				if(!line.equals("") )
    				{
    					count ++;
    				}
    			}
    			in.close();
    		}
    		catch(FileNotFoundException e)
    		{
    			e.printStackTrace();
    		}
    		catch(IOException e)
    		{
    			e.printStackTrace();
    		}
    		
    	}

        return count;
    }

}

