package me.horzwxy.tool.articlefilter;

/**
 * Created by horz on 3/12/14.
 */
public class InDecoState extends State {

    public InDecoState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        if(c == '<') {
            return new InDecoTailState(this);
        }
        else {
            getContent().append(c);
            return this;
        }
    }
}
