package com.langlang.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;

/*	获取屏幕使用的分辨率
 * 标准： 按3840*2160为4k的定位，2k当然是1920*1080。大众习惯管2560*1440叫2k。至于1920*1080，叫1080p
 * 1080P: 1920--1080
 * 1440p: 俗称2K 2560--1440
 * 4K: 3840 * 2160
 */
public class ResolutionTest {
	
	public static void main(String[] args) {
		
		// Java代码获取屏幕分辨率//类包使用为： java.awt.Toolkit

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = defaultToolkit.getScreenSize();
		double width2 = screenSize.getWidth();
		double height2 = screenSize.getHeight();
		System.out.println(String.format("显示器尺寸：%s x %s",width2,height2));

		GraphicsDevice graphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		DisplayMode disMode = graphDevice.getDisplayMode();
		int width = disMode.getWidth();
		int height = disMode.getHeight();
		System.out.println(String.format("显示器分辨率：%s x %s",width,height));

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().
						getDefaultConfiguration();

		AffineTransform tx = gc.getDefaultTransform();
		double uiScaleX = tx.getScaleX();
		double uiScaleY = tx.getScaleY();
		System.out.println(String.format("显示器分辨率缩放比例，X：%s，Y：%s",uiScaleX,uiScaleY));
	}

}
