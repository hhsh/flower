package com.hajea.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DoImage {

	/*
	 * png -> jpg 图片受损严重。
	 * png -> png 图片无损。
	 * 
	 * jpg -> jpg 图片受损轻微。
	 * jpg -> png 图片无损，而且图片急剧增大。
	 */
	public static void main(String[] args) throws Exception {
		/*
		String fullImagePath = "C:\\Users\\sam\\Pictures\\2015-06-24_113740.png";
		BufferedImage bufImg = readImage(fullImagePath);
		
		fullImagePath = "C:\\Users\\sam\\Pictures\\2015-06-24_113740_2.png";
		
		writeImage(fullImagePath,bufImg,"png");
		*/
		
		String fullImagePath = "C:\\Users\\sam\\Pictures\\bk.jpg";
		BufferedImage bufImg = readImage(fullImagePath);
		
		fullImagePath = "C:\\Users\\sam\\Pictures\\bk_2.png";
		
		writeImage(fullImagePath,bufImg,"png");
		
	}

	private static BufferedImage readImage(String fullImagePath) throws IOException  {
        File imageFile = new File(fullImagePath);
        if (!imageFile.exists()) {
            throw new IOException("Not found the images:" + fullImagePath);
        }
        return ImageIO.read(imageFile);
	}
	
	private static void writeImage(String fullImagePath, BufferedImage bufImage, String formatName) throws IOException {
        File file = new File(fullImagePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        ImageIO.write(bufImage, formatName, file);
    } 
	
	private static void writeImage(String fullImagePath, BufferedImage bufImage) throws IOException {
		writeImage(fullImagePath,bufImage,"jpg");
	}
	
	 
	 

}
