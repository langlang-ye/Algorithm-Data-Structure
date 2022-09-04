package com.langlang.utils;

import java.awt.Toolkit;

/*	获取屏幕使用的分辨率
 * 标准： 按3840*2160为4k的定位，2k当然是1920*1080。大众习惯管2560*1440叫2k。至于1920*1080，叫1080p
 * 1080P: 1920--1080
 * 1440p: 俗称2K 2560--1440
 */
public class ResolutionTest {
	
	public static void main(String[] args) {
		
		// Java代码获取屏幕分辨率//类包使用为： java.awt.Toolkit

		//屏幕分辨率宽度
		int screenW = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		//屏幕分辨率高度
		int screenH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		System.out.println(screenW + "--" + screenH);
		

	}

}
