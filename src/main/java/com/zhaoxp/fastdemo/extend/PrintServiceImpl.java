package com.zhaoxp.fastdemo.extend;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/1/15 11:14
 **/
public class PrintServiceImpl implements PrintService {

    @Override
    public String print(String content) {
        return "aa,"+content;
    }
}
