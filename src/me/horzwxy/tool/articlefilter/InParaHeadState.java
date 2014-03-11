package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class InParaHeadState extends State {

    public InParaHeadState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        if(c == '>') {
            getContent().append("<paragraph>\n");
            return new InParaState(this);
        }
        else {
            return this;
        }
    }
}
