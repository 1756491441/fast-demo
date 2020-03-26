package com.zhaoxp.fastdemo.extend;

import org.apache.dubbo.common.extension.SPI;

@SPI("impl")
public interface PrintService {

    String print(String content);
}
