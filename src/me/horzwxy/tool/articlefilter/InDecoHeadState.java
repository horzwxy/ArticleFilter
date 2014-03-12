package me.horzwxy.tool.articlefilter;

/**
 * Created by horz on 3/12/14.
 */
public class InDecoHeadState extends State {

    private StringBuilder buffer;

    public InDecoHeadState(State oldState) {
        super(oldState);
        this.buffer = new StringBuilder();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("/>".startsWith(testContent)) {
            if("/>".equals(testContent)) {
                return new InParaState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else if(">".equals(testContent)) {
            return new InDecoState(this);
        }
        else {
            if(buffer.length() != 0) {
                buffer = new StringBuilder();
            }
            return this;
        }
    }
}
