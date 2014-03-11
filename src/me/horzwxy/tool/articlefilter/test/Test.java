package me.horzwxy.tool.articlefilter.test;

import me.horzwxy.tool.articlefilter.ArticleFilter;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) {
        ArticleFilter interpreter = new ArticleFilter(new File("example.html"));
        interpreter.doWork(new File("output.xml"));
    }
}
