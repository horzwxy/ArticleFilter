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
        // replace the escape letter representation
        if("&nbsp;".startsWith(testContent)) {
            if("&nbsp;".equals(testContent)) {
                getContent().append("&#160;");
                buffer = new StringBuilder();
                return this;
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        // try to match ending tag
        else if("</p>".startsWith(testContent)) {
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
            // fail to match a closing tag, must be in content or a decoration tag
            if(buffer.length() != 0) {
                return new InDecoHeadState(this);
            }
            else {
                getContent().append(c);
                return this;
            }
        }
    }
}
