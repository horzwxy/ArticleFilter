package me.horzwxy.tool.articlefilter;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArticleFilter {
    private File htmlFile;

    public ArticleFilter(File htmlFile) {
        this.htmlFile = htmlFile;
    }

    public void doWork(File outputFile) {
        try {
            final PrintWriter writer = new PrintWriter(outputFile);

            FilterCallback callback = new FilterCallback() {
                @Override
                public void appendOutput(String s) {
                    writer.println(s);
                }
            };
            State currentState = State.getInitialState(callback).transfer(' ');

            BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
            String line = reader.readLine();
            while (line != null) {
                for(int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    currentState = currentState.transfer(c);
                }
                currentState = currentState.transfer('\n');
                line = reader.readLine();
            }

            writer.println(currentState.getContent().toString());

            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface FilterCallback {
        public void appendOutput(String s);
    }
}
