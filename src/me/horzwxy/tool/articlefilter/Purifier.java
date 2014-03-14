package me.horzwxy.tool.articlefilter;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Purifier {

    protected Purifier() {

    }

    public static Purifier getPurifier(String type) {
        if("washingpost".equals(type)) {
            return new WPostPurifier();
        }
        else if ("bbc".equals(type)) {
            return new BbcPurifier();
        }
        return null;
    }

    public abstract File purifier(File inputFile);
}
