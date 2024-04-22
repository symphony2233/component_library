package org.example.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: symphony
 * @create: 2024/04/22
 **/
public class OcrUtils {
    // 截取 PDF 的首页并保存到指定位置
    public static void extractFirstPage(String pdfFilePath, String outputImagePath) throws IOException {
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(pdfFilePath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300); // 500 dpi
            ImageIO.write(image, "jpg", new File(outputImagePath));
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }


    // 对图像进行 OCR 并提取信息
    public static String ocrAndExtractInfo(String imagePath) throws IOException, TesseractException {
        // 创建Tesseract对象
        ITesseract tesseract = new Tesseract();
        // 字体库路径
        tesseract.setDatapath("G:\\java_componet\\component_library\\pdf_ocr\\src\\main\\resources");
        // 设置语言：简体中文
        tesseract.setLanguage("chi_sim");

        BufferedImage image = ImageIO.read(new File(imagePath)); // 加载图像文件
        String ocrResult = tesseract.doOCR(image); // 对图像进行 OCR

        // 替换回车和tal键，让结果为一行
        // ocrResult = ocrResult.replaceAll("\\r|\\n","-").replaceAll(" ","");
        return ocrResult; // 返回 OCR 结果
    }

    // 从 OCR 结果中提取信息的方法（和之前提取信息的方法类似）
    public static String extractInfo(String text, String keyword) {
        int startIndex = text.indexOf(keyword);
        if (startIndex != -1) {
            startIndex += keyword.length();
            int endIndex = text.indexOf('\n', startIndex);
            if (endIndex != -1) {
                return text.substring(startIndex, endIndex).trim();
            }
        }
        return "";
    }
}
