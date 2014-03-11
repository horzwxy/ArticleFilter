package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeaceState extends State {

    private StringBuilder buffer;

    public PeaceState(State oldState) {
        super(oldState);
        buffer = new StringBuilder();
        resetContent();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("<article".startsWith(testContent)) {
            if("<article".equals(testContent)) {
                getContent().append("<article");
                return new InArticleState(this);
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
