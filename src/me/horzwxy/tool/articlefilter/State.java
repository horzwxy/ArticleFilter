package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class State {

    private StringBuilder content;
    private ArticleFilter.FilterCallback callback;

    private State(ArticleFilter.FilterCallback callback) {
        this.content = new StringBuilder();
        this.callback = callback;
    }

    protected State(State oldState) {
        this.content = oldState.getContent();
        this.callback = oldState.getCallback();
    }

    public abstract State transfer(char c);

    protected StringBuilder getContent() {
        return this.content;
    }

    protected void resetContent() {
        this.content = new StringBuilder();
    }

    protected ArticleFilter.FilterCallback getCallback() {
        return this.callback;
    }

    public static State getInitialState(ArticleFilter.FilterCallback callback) {
        return new State(callback) {
            @Override
            public State transfer(char c) {
                return new PeaceState(this);
            }
        };
    }
}
