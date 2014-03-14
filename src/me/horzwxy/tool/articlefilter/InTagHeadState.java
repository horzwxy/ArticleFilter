package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class InTagHeadState extends State {

    public InTagHeadState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        if(c == '>') {
            getContent().append(c);
            return new PeaceState(this);
        }
        else {
            return this;
        }
    }
}
