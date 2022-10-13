package com.langlang.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/*	获取屏幕使用的分辨率
 * 标准： 按3840*2160为4k的定位，2k当然是1920*1080。大众习惯管2560*1440叫2k。至于1920*1080，叫1080p
 * 1080P: 1920--1080
 * 1440p: 俗称2K 2560--1440
 * 4K: 3840 * 2160
 * 总结：
 * 显示器尺寸： 是笔记本本身的显示器使用到的分辨率了，外接显示器后，如果外接的显示器分辨率高于笔记本显示器分辨率，则会出现外接器显示是缩放设置后的，具体缩放多少和它有关
 * 显示器分辨率： 显示器的物理分辨率，
 * 注意第一次： 外接显示器插入的时候， 显示器分辨率通过缩放最后实际使用的分辨率等于显示器尺寸
 * 显示器分辨率缩放比例： 一般都是1.0
 *
 * 笔记本本身：
 * 显示器尺寸：1280.0 x 720.0
 * 显示器分辨率：1920 x 1080
 * 显示器分辨率缩放比例，X：1.0，Y：1.0
 *
 * 外接显示器：
 * 显示器尺寸：1280.0 x 720.0
 * 显示器分辨率：3840 x 2160
 * 显示器分辨率缩放比例，X：1.0，Y：1.0
 * 缩放比例： 300%
 *
 * 投影：
 * 显示器尺寸：2560.0 x 1440.0
 * 显示器分辨率：3840 x 2160
 * 显示器分辨率缩放比例，X：1.0，Y：1.0
 * 投影：采用屏幕复制，显示器尺寸不变，显示器的分辨率还是显示器本身的分辨率，
 * 仅第二块屏幕， 也就是只使用外接的显示器，显示器尺寸4K
 *
 *
 */
public class ResolutionTest {

    public static void main(String[] args) {

        // Java代码获取屏幕分辨率//类包使用为： java.awt.Toolkit

        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        double width2 = screenSize.getWidth();
        double height2 = screenSize.getHeight();
        System.out.println(String.format("显示器尺寸：%s x %s", width2, height2));

        GraphicsDevice graphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode disMode = graphDevice.getDisplayMode();
        int width = disMode.getWidth();
        int height = disMode.getHeight();
        System.out.println(String.format("显示器分辨率：%s x %s", width, height));

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().
                        getDefaultConfiguration();

        AffineTransform tx = gc.getDefaultTransform();
        double uiScaleX = tx.getScaleX();
        double uiScaleY = tx.getScaleY();
        System.out.println(String.format("显示器分辨率缩放比例，X：%s，Y：%s", uiScaleX, uiScaleY));
    }

}
