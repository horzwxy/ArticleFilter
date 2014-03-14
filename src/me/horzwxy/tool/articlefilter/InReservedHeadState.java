package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 10:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class InReservedHeadState extends State {

    public InReservedHeadState(State oldState) {
        super(oldState);
    }

    @Override
    public State transfer(char c) {
        getContent().append(c);
        if(c == '>') {
            return new PeaceState(this);
        }
        else {
            return this;
        }
    }
}
