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
        if ("<!".startsWith(testContent)) {
            if("<!".equals(testContent)) {
                getContent().append("<!");
                return new InPreState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
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
                return new InBypassedTag(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<link".startsWith(testContent)) {
            if("<link".equals(testContent)) {
                return new InBypassedTag(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<img".startsWith(testContent)) {
            if("<img".equals(testContent)) {
                return new InBypassedTag(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<br".startsWith(testContent)) {
            if("<br".equals(testContent)) {
                return new InBypassedTag(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<input".startsWith(testContent)) {
            if("<input".equals(testContent)) {
                return new InBypassedTag(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("<hr".startsWith(testContent)) {
            if("<hr".equals(testContent)) {
                return new InBypassedTag(this);
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
        else if(testContent.startsWith("<")) {
            if(c == '>') {
                getContent().append(testContent);
                buffer = new StringBuilder();
                return this;
            }
            else if(c == ' ') {
                getContent().append(buffer.toString());
                return new InTagHeadState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if("&nbsp;".startsWith(testContent)) {
            if("&nbsp;".equals(testContent)) {
                buffer = new StringBuilder();
                getContent().append("&#32;");
                return this;
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
