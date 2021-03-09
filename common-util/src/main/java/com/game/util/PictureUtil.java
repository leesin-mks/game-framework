/*
 * Copyright 2016-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.game.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PictureUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PictureUtil.class);

    /**
     * 生成图像矩阵
     * 
     * @param bufferedImage
     * @return data
     * @throws Exception
     */
    public static int[][] getData(BufferedImage bufferedImage)
    {
        int[][] data = new int[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for (int i = 0; i < bufferedImage.getWidth(); i++)
        {
            for (int j = 0; j < bufferedImage.getHeight(); j++)
            {
                data[i][j] = bufferedImage.getRGB(i, j);
            }
        }
        return data;
    }

    /**
     * 根据模板切图
     * 
     * @param pictureTemplatePath
     * @param dealPicturePath
     * @throws Exception
     */
    public static void pictureTemplatesCut(String pictureTemplatePath, String dealPicturePath, String imagePath, int x,
            int y) throws Exception
    {
        // 文件类型
        String TemplateFiletype = pictureTemplatePath.substring(pictureTemplatePath.lastIndexOf(".") + 1);
        String oriFileType = dealPicturePath.substring(dealPicturePath.lastIndexOf(".") + 1);
        if (StringUtil.isBlank(TemplateFiletype))
        {
            throw new RuntimeException("file type is empty");
        }
        // 源文件流
        File Orifile = new File(dealPicturePath);
        InputStream oriis = new FileInputStream(Orifile);
        // BufferedImage oriImage = ImageIO.read(oriis);
        // 模板图
        BufferedImage imageTemplate = ImageIO.read(new File(pictureTemplatePath));
        BufferedImage dealImage = ImageIO.read(new File(dealPicturePath));
        int width = imageTemplate.getWidth();
        int height = imageTemplate.getHeight();
        // System.out.println("width:"+width);
        // System.out.println("height:"+height);
        /*
         * //源文件宽度 int oriWidth = oriImage.getWidth(); //源文件高度 int oriHeight
         * =oriImage.getHeight();
         */

        // 源文件宽度
        // int oriWidth = 500;
        // // 源文件高度
        // int oriHeight = 375;
        // 源文件宽度
        int oriWidth = dealImage.getWidth();
        // 源文件高度
        int oriHeight = dealImage.getHeight();
        // System.out.println("oriWidth:"+oriWidth);
        // System.out.println("oriHeight:"+oriHeight);

        // 最终图像
        BufferedImage newImage = new BufferedImage(width, height, imageTemplate.getType());
        // new Thread(new PrintActionListener(newImage)).start();
        Graphics2D graphics = newImage.createGraphics();
        graphics.setBackground(Color.white);

        // 随机扣图坐标点
        // Random random = new Random();
        // int x = random.nextInt(oriWidth - width) + 5;
        // int y = random.nextInt(oriHeight - height) + 5;
        // int x = ThreadLocalRandom.current().nextInt(oriWidth / 3, 3 * oriWidth / 4);
        // int y = ThreadLocalRandom.current().nextInt(oriHeight / 3, oriHeight / 2);
        // System.out.println("x:"+x);
        // System.out.println("y:"+y);

        int bold = 5;
        // 获取感兴趣的目标区域
        BufferedImage targetImageNoDeal = getTargetArea(x, y, width, height, oriis, oriFileType);
        // new Thread(new PrintActionListener(targetImageNoDeal)).start();
        // 根据模板图片抠图
        newImage = DealCutPictureByTemplate(targetImageNoDeal, imageTemplate, newImage);
        // new Thread(new PrintActionListener(newImage)).start();
        BufferedImage oriImage = ImageIO.read(Orifile);

        // BufferedImage ori_copy_image = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(),
        // BufferedImage.TYPE_4BYTE_ABGR);
        // 源图生成遮罩
        BufferedImage ori_copy_image = DealOriPictureByTemplate(oriImage, imageTemplate, x, y, imagePath);

        // 设置“抗锯齿”的属性
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();
        ImageIO.write(newImage, TemplateFiletype, new File(imagePath, "pick." + TemplateFiletype));

        /*
         * Graphics2D g2D = ori_copy_image.createGraphics();
         * // 设置“抗锯齿”的属性
         * g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         * g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         * g2D.setStroke(new BasicStroke(bold, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
         * g2D.drawImage(ori_copy_image, 0, 0, null);
         * g2D.dispose();
         */
        // ImageIO.write(ori_copy_image, oriFiletype, new File("D:/slider/111", "huxuhongori." + TemplateFiletype));

    }

    /**
     * 根据模板图片抠图
     * 
     * @param oriImage
     * @param templateImage
     * @return
     */
    public static BufferedImage DealCutPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage,
            BufferedImage targetImage) throws Exception
    {
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);
        // 模板图像宽度
        for (int i = 0; i < templateImageData.length; i++)
        {
            // 模板图片高度
            for (int j = 0; j < templateImageData[0].length; j++)
            {
                // 如果模板图像当前像素点不是白色 copy源文件信息到目标图片中
                if (templateImageData[i][j] != 0)
                {
                    targetImage.setRGB(i, j, oriImageData[i][j]);
                }
            }
        }
        return targetImage;
    }

    public static BufferedImage DealOriPictureByTemplate(BufferedImage oriImage, BufferedImage templateImage, int x,
            int y, String imagePath) throws Exception
    {
        // 源文件备份图像矩阵 支持alpha通道的rgb图像
        BufferedImage ori_copy_image = new BufferedImage(oriImage.getWidth(), oriImage.getHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        // 源文件图像矩阵
        int[][] oriImageData = getData(oriImage);
        // 模板图像矩阵
        int[][] templateImageData = getData(templateImage);

        // copy 源图做不透明处理
        for (int i = 0; i < oriImageData.length; i++)
        {
            for (int j = 0; j < oriImageData[0].length; j++)
            {
                int rgb = oriImage.getRGB(i, j);
                int r = (0xff & rgb);
                int g = (0xff & (rgb >> 8));
                int b = (0xff & (rgb >> 16));
                // 无透明处理
                rgb = r + (g << 8) + (b << 16) + (255 << 24);
                ori_copy_image.setRGB(i, j, rgb);
            }
        }

        for (int i = 0; i < templateImageData.length; i++)
        {
            for (int j = 0; j < templateImageData[0].length; j++)
            {
                int rgb = templateImage.getRGB(i, j);
                // 对源文件备份图像(x+i,y+j)坐标点进行透明处理
                if (rgb != 0)
                {
                    int rgb_ori = ori_copy_image.getRGB(x + i, y + j);
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    rgb_ori = r + (g << 8) + (b << 16) + (80 << 24);
                    ori_copy_image.setRGB(x + i, y + j, rgb_ori);
                }
            }
        }
        ImageIO.write(ori_copy_image, "png", new File(imagePath, "beijing.png"));
        return ori_copy_image;
    }

    public static int color_range = 210;

    public static boolean colorInRange(int color)
    {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        if (red >= color_range && green >= color_range && blue >= color_range)
            return true;
        return false;
    }

    /**
     * 获取目标区域
     * 
     * @param x
     *            随机切图坐标x轴位置
     * @param y
     *            随机切图坐标y轴位置
     * @param targetWidth
     *            切图后目标宽度
     * @param targetHeight
     *            切图后目标高度
     * @param ois
     *            源文件输入流
     * @return targetImage
     * @throws Exception
     */
    public static BufferedImage getTargetArea(int x, int y, int targetWidth, int targetHeight, InputStream ois,
            String fileType) throws Exception
    {
        Iterator<ImageReader> imageReaderList = ImageIO.getImageReadersByFormatName(fileType);
        ImageReader imageReader = imageReaderList.next();
        // 获取图片流
        ImageInputStream iis = ImageIO.createImageInputStream(ois);
        // 输入源中的图像将只按顺序读取
        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rec = new Rectangle(x, y, targetWidth, targetHeight);
        param.setSourceRegion(rec);
        BufferedImage targetImage = imageReader.read(0, param);
        return targetImage;
    }

    public static String imageToBase64(BufferedImage image) throws Exception
    {
        byte[] imageData = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bao);
        imageData = bao.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String BASE64IMAGE = encoder.encodeBuffer(imageData).trim();
        BASE64IMAGE = BASE64IMAGE.replaceAll("\n", "").replaceAll("\r", "");// 删除 \r\n
        return BASE64IMAGE;
    }

    public static BufferedImage base64StringToImage(String base64String)
    {
        try
        {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            return ImageIO.read(bais);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] bufferedImageToByte(BufferedImage bufferedImage)
    {
        byte[] imageInByte = null;
        ByteArrayOutputStream bos = null;
        try
        {
            bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", bos);
            bos.flush();
            // 使用toByteArray()方法转换成字节数组
            imageInByte = bos.toByteArray();
        }
        catch (IOException e)
        {
            LOGGER.error("IO exception: ", e);
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    LOGGER.error("IO exception: ", e);
                }
            }
        }
        return imageInByte;
    }

    public static void main(String[] args) throws Exception
    {
        // pictureTemplatesCut("D:/slider/111/33.png","D:/slider/111/77.jpg");
        int nextInt = ThreadLocalRandom.current().nextInt(1, 5);
        String path = "../CommonLib/img/p0" + 3 + ".jpg";
        String pa = "../CommonLib/img/";

        int x = ThreadLocalRandom.current().nextInt(654 / 3, 3 * 654 / 4);
        int y = ThreadLocalRandom.current().nextInt(300 / 3, 300 / 2);
        System.out.println(x);
        System.out.println(y);
        int result = ((x << 8) | y);
        System.out.println(result);
        int newy = result & 0x00fF;
        int newx = result >> 8;
        System.out.println(newy);
        System.out.println(newx);
        pictureTemplatesCut("../CommonLib/img/template.png", path, pa, x, y);
        BufferedImage imageTemplate1 = ImageIO.read(new File("../CommonLib/img/pick.png"));
        System.out.println(imageTemplate1.getWidth());
        System.out.println(imageTemplate1.getHeight());
    }
}
