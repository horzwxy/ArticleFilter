package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class InArticleState extends State {

    private StringBuilder buffer;

    public InArticleState(State oldState) {
        super(oldState);
        this.buffer = new StringBuilder();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("<p".startsWith(testContent)) {
            if("<p".equals(testContent)) {
                return new InParaHeadState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("</article>".startsWith(testContent)) {
            if("</article>".equals(testContent)) {
                getCallback().appendOutput(getContent().toString() + "</article>\n");
                return new PeaceState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else {
            if(buffer.length() != 0) {
                buffer = new StringBuilder();
            }
            return this;
        }
    }
}
