package com.zhaoxp.fastdemo.extend;


import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author zhaoxp
 * @version 1.0
 * @date 2020/1/15 11:16
 **/
public class Test {

    public static void main(String[] args) {
//        PrintService printService = ExtensionLoader.getExtensionLoader(PrintService.class).getDefaultExtension();
//        System.out.println(printService.print("zhaoxp"));
        Compiler compiler = ExtensionLoader.getExtensionLoader(Compiler.class).getAdaptiveExtension();
        System.out.println(compiler);
    }

}
