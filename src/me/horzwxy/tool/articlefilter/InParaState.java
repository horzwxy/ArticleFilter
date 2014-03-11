package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 10:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class InParaState extends State {

    private StringBuilder buffer;

    public InParaState(State oldState) {
        super(oldState);
        buffer = new StringBuilder();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("&nbsp;".startsWith(testContent)) {
            if("&nbsp;".equals(testContent)) {
                getContent().append("&#160;");
                buffer = new StringBuilder();
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        if("</p>".startsWith(testContent)) {
            if("</p>".equals(testContent)) {
                getContent().append("</paragraph>\n");
                return new InArticleState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else {
            if(buffer.length() != 0) {
                getContent().append(buffer.toString());
                buffer = new StringBuilder();
            }
            getContent().append(c);
            return this;
        }
    }
}
