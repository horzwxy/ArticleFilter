package me.horzwxy.tool.articlefilter;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class InScriptState extends State {

    private StringBuilder buffer;

    public InScriptState(State oldState) {
        super(oldState);
        buffer = new StringBuilder();
    }

    @Override
    public State transfer(char c) {
        String testContent = buffer.toString() + c;
        if("</script>".startsWith(testContent)) {
            if ("</script>".equals(testContent)) {
                return new PeaceState(this);
            }
            else {
                buffer.append(c);
                return this;
            }
        }
        else {
            if(buffer.length() != 0) {
                buffer = new StringBuilder();
            }
            return this;
        }
    }
}
