package com.kristin.action.picture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;


public class PictureChange {

    static String suffix = "";

    public static void main(String[] args) {
        String newfilebase = "/Users/lihang/mydownloads";
        File file = new File(newfilebase + 1);
        File[] oldfiles = file.listFiles();
        for (File file2 : oldfiles) {
            if (isImageFile(file2)) {
                if (!scaleImage(newfilebase + 1 + "/" + file2.getName(), newfilebase + 11 + "/" + file2.getName())) {
                    System.out.println(file2.getName() + "转化成功！");
                }
            }
        }
    }


    public static boolean isImageFile(File file) {

        String fileName = file.getName();

        //获取文件名的后缀，可以将后缀定义为类变量，共后面的函数使用
        suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

        // 声明图片后缀名数组
        if (!suffix.matches("^[(jpg)|(png)|(gif)]+$")) {
            System.out.println("请输入png,jpg,gif格式的图片");
            return false;
        }
        return true;
    }

    public static boolean scaleImage(String imgSrc, String imgDist) {
        try {
            File file = new File(imgSrc);
            if (!file.exists()) {
                return false;
            }

            InputStream is = new FileInputStream(file);
            Image src = ImageIO.read(is);
            if (src.getWidth(null) <= 600) {
                File tofile = new File(imgDist);
                copyFile(file, tofile);
                is.close();
                return true;
            }

            //获取源文件的宽高
            int imageWidth = ((BufferedImage) src).getWidth();
            int imageHeight = ((BufferedImage) src).getHeight();

            double scale = (double) 600 / imageWidth;

            //计算等比例压缩之后的狂傲
            int newWidth = (int) (imageWidth * scale);
            int newHeight = (int) (imageHeight * scale);

            BufferedImage newImage = scaleImage((BufferedImage) src, scale, newWidth, newHeight);

            File file_out = new File(imgDist);
            ImageIO.write(newImage, suffix, file_out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //用于具体的转化
    public static BufferedImage scaleImage(BufferedImage bufferedImage, double scale, int width, int height) {
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(bufferedImage, new BufferedImage(width, height, bufferedImage.getType()));
    }


    //复制文件
    public static void copyFile(File fromFile, File toFile) throws IOException {
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n = 0;
        while ((n = ins.read(b)) != -1) {
            out.write(b, 0, n);
        }
        ins.close();
        out.close();
    }
}