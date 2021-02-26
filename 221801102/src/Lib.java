import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Text file analyser
 */
public class Lib {

    private String inFile;
    private String outFile;
    private int charNum;
    private int wordNum;
    private int lineNum;
    private Map<String, Integer> topWord;

    private Pattern wordPattern = Pattern.compile("(^|\\s)[A-Za-z]{4}\\S*");
    private Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public int getLineNum() {
        return lineNum;
    }

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public int getCharNum() {
        return charNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public Map<String, Integer> getTopWord() {
        // clone a map to prevent modification of the resulted map
        return new LinkedHashMap<>(topWord);
    }

    public Lib(String inFile, String outFile) throws IOException {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /**
     * process all data
     */
    public void process() throws IOException {
        int charNum = this.charNum;
        int wordNum = this.wordNum;
        int lineNum = this.lineNum;
        Map<String, Integer> topWord = this.topWord;
        this.charNum = 0;
        this.wordNum = 0;
        this.lineNum = 0;
        this.topWord = new HashMap<>();
        try {
            String str = readFile();
            this.charNum = str.length();
            Matcher matcher = linePattern.matcher(str);
            while (matcher.find()) {
                this.lineNum++;
            }
            matcher = wordPattern.matcher(str);
            while (matcher.find()) {
                String word = matcher.group(0).trim();
                Integer count = this.topWord.get(word);
                if (count == null) {
                    count = 0;
                }
                this.topWord.put(word, count + 1);
                this.wordNum++;
            }
            this.topWord = this.topWord.entrySet().stream()
                .sorted(
                    Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        } catch (IOException e) {
            // restore
            this.charNum = charNum;
            this.wordNum = wordNum;
            this.topWord = topWord;
            this.lineNum = lineNum;
            throw e;
        }
    }

    /**
     * process character
     */
    public void processCharNum() throws IOException {
        int charNum = this.charNum;
        this.charNum = 0;
        try {
            String str = readFile();
            this.charNum = str.length();
        } catch (IOException e) {
            // restore
            this.charNum = charNum;
            throw e;
        }
    }

    /**
     * process the number of words
     */
    public void processWordNum() throws IOException {
        int wordNum = this.wordNum;
        this.wordNum = 0;
        try {
            String str = readFile();
            Matcher matcher = wordPattern.matcher(str);
            while (matcher.find()) {
                this.wordNum++;
            }
        } catch (IOException e) {
            // restore
            this.wordNum = wordNum;
            throw e;
        }
    }

    /**
     * process the top 10 number of occurrence of words
     */
    public void processWordRank() throws IOException {
        Map<String, Integer> topWord = this.topWord;
        this.topWord = new HashMap<>();
        try {
            String str = readFile();

            Matcher matcher = wordPattern.matcher(str);
            while (matcher.find()) {
                String word = matcher.group(0).trim();
                Integer count = this.topWord.get(word);
                if (count == null) {
                    count = 0;
                }
                this.topWord.put(word, count + 1);
            }
            this.topWord = this.topWord.entrySet().stream()
                .sorted(
                    Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        } catch (IOException e) {
            // restore
            this.topWord = topWord;
            throw e;
        }
    }

    public void processLineNum() throws IOException {
        int lineNum = this.lineNum;
        this.lineNum = 0;
        try {
            String str = readFile();
            Matcher matcher = linePattern.matcher(str);
            while (matcher.find()) {
                this.lineNum++;
            }
        } catch (IOException e) {
            // restore
            this.lineNum = lineNum;
            throw e;
        }
    }

    private String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        StringBuilder builder = new StringBuilder();
        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (c != 13) {
                    builder.append((char) c);
                }
            }
        } catch (IOException e) {
            reader.close();
            throw e;
        }
        return builder.toString();
    }

    /**
     * write data to file in a hard-encoding format
     */
    public void output() throws IOException {
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(outFile)
        );
        writer.write("characters: " + charNum + "\n");
        writer.write("words: " + wordNum + "\n");
        writer.write("lines: " + lineNum + "\n");
        for (Map.Entry<String, Integer> entry : topWord.entrySet()) {
            String s = entry.getKey();
            Integer integer = entry.getValue();
            writer.write(s + ": " + integer + "\n");
        }
        writer.close();
    }
}
