package com.liwei;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by liwei on 16/10/1.
 */
public class FileTest {

    @Test
    public void test01(){
        File file = new File("/Users/liwei/codes/Blog/src/main/webapp/static/userImages");
        String[] files = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.startsWith("liwei");
            }
        });
        for(String fileName :files){
            System.out.println(fileName);
        }

    }
}
