package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class InFrameState extends State {

    private StringBuilder buffer;

    public InFrameState(State oldState) {
        super(oldState);
        buffer = new StringBuilder();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("</iframe>".startsWith(testContent)) {
            if ("</iframe>".equals(testContent)) {
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
