package me.horzwxy.tool.articlefilter;

/**
 * Created by horz on 3/12/14.
 */
public class InDecoTailState extends State {

    public InDecoTailState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        if (c == '>') {
            return new InParaState(this);
        }
        else {
            return this;
        }
    }
}
