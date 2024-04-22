package org.example.controller;

import net.sourceforge.tess4j.TesseractException;
import org.example.utils.OcrUtils;

import java.io.IOException;

/**
 * @description:
 * @author: symphony
 * @create: 2024/04/22
 **/

public class TestController {

    public static void main(String[] args) {
        try {
            OcrUtils.extractFirstPage("G:\\faming.pdf","G:\\1.jpg");

            String result = OcrUtils.ocrAndExtractInfo("G:\\1.jpg");
            System.out.println(result);

            System.out.println("====================");
            String inventionTitle = OcrUtils.extractInfo(result, "发 明 名 称:");
            String inventor = OcrUtils.extractInfo(result, "发”明 人:");
            String patentNumber = OcrUtils.extractInfo(result, "专 利 号:");
            String patentOwner = OcrUtils.extractInfo(result, "专 利 权 人:");
            String publicationDate = OcrUtils.extractInfo(result, "专利申请日:");


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TesseractException e) {
            throw new RuntimeException(e);
        }


    }
}
