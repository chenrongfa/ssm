package com.yy.ssm.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component(value="ImageCode")
public class ImagesRandomCode {

	@Value("${ImageCode.width}")
	private String width;

	@Value("${ImageCode.height}")
	private String height;

	@Value("${ImageCode.codeLength}")
	private String codeLength;

	@Value("${ImageCode.randomString}")
	private String randomString;

	@Value("${ImageCode.sessionKey}")
	private String sessionKey;

	@Value("${ImageCode.font.name}")
	private String fontName;

	@Value("${ImageCode.font.style}")
	private String fontStyle;

	@Value("${ImageCode.font.size}")
	private String fontSize;

	public BufferedImage getImage(HttpServletRequest request) {
		int width=Integer.parseInt(this.width);
		int height=Integer.parseInt(this.height);
		int codeLength=Integer.parseInt(this.codeLength);
		int fontSize=Integer.parseInt(this.fontSize);
		int fontStyle=Integer.parseInt(this.fontStyle);
		// 在内存中创建图片
		BufferedImage image = new BufferedImage(width,height ,
				BufferedImage.TYPE_INT_RGB);
		// 获取图片上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景颜色
		g.setColor(getRandColor(100, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, xl, y + yl);
		}
		// 取随机产生的认证码
		String sRand = randomRand(codeLength);
		int strWidth = width / 2 - g.getFontMetrics().stringWidth(sRand)
				/ codeLength - fontSize;
		int strHeight = height / 2 + g.getFontMetrics().getHeight() / 4;
		for (int i = 0; i < codeLength; i++) {
			String rand = sRand.substring(i, i + 1);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6 + strWidth, strHeight);
		}
		// System.out.println(sRand);
		
		SecurityUtils.getSubject().getSession().setAttribute(sessionKey, sRand);
		request.setAttribute("sRand", sRand);
		g.dispose();
		return image;
	}

	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);

	}

	public static String randomResult(int length) {
		String[] i = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		List<String> l = new ArrayList<String>();
		l.addAll(Arrays.asList(i));
		Random ran = new Random();
		String s = "";
		while (l.size() > 10 - length)
			s += l.remove(ran.nextInt(l.size()));
		s = s.replaceAll("^(0)(\\d)", "$2$1");
		return s;
	}

	private String randomRand(int n) {
		String rand = "";
		int len = randomString.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = ((Math.random())) * len;
			rand = rand + randomString.charAt((int) r);
		}
		return rand;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(String codeLength) {
		this.codeLength = codeLength;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}


	
}
