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
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("<script".startsWith(testContent)) {
            if("<script".equals(testContent)) {
                return new InScriptState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<style".startsWith(testContent)) {
            if("<style".equals(testContent)) {
                return new InStyleState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<meta".startsWith(testContent)) {
            if("<meta".equals(testContent)) {
                return new InMetaState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<link".startsWith(testContent)) {
            if("<link".equals(testContent)) {
                return new InLinkState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<iframe".startsWith(testContent)) {
            if("<iframe".equals(testContent)) {
                return new InFrameState(this);
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
