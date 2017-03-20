package cn.yyun.ppt.utils;

import java.math.BigDecimal;

public class ImageUtils {
	public static int getNewHeight(int width,int height,int newWidth) {
		BigDecimal decimalNewWith = new BigDecimal(newWidth);
		BigDecimal decimalWith = new BigDecimal(width);
		BigDecimal decimalHeight = new BigDecimal(height);
		int newHeight = decimalNewWith.divide(decimalWith,4,BigDecimal.ROUND_DOWN).multiply(decimalHeight).intValue();
		return newHeight;
	}
}
