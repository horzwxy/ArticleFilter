package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class InPreState extends State {

    public InPreState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        getContent().append(c);
        if (c == '>') {
            return new PeaceState(this);
        }
        else {
            return this;
        }
    }
}
