//package com.wj.excel;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import jxl.Cell;
//import jxl.Workbook;
//import jxl.format.Alignment;
//import jxl.format.Colour;
//import jxl.format.VerticalAlignment;
//import jxl.read.biff.BiffException;
//import jxl.write.Label;
//import jxl.write.WritableCellFormat;
//import jxl.write.WritableFont;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;
//
//public class CWOutputFile {  //相当于类
//
//    /*
//     * wOutputFile方法写结果文件
//     * wOutputFile(文件路径,用例编号,测试验证点,测试数据,预期结果,实际结果)
//     * python如何定义：def wOutputFile(self,filepath,caseNo,testPoint,testData,preResult,fresult)
//     */
//    public void wOutputFile(String filepath, String caseNo, String testPoint, String testData, String preResult, String fresult) throws WriteException, BiffException, IOException {
//        File output = new File(filepath);
//        String result = "";
//        InputStream instream = new FileInputStream(filepath);
//        Workbook readwb = Workbook.getWorkbook(instream);
//        // 根据文件创建一个操作对象
//        WritableWorkbook wbook = Workbook.createWorkbook(output, readwb);
//        WritableSheet readsheet = wbook.getSheet(0);
//
//        // int rsColumns = readsheet.getColumns(); //获取Sheet表中所包含的总列数
//        // 获取Sheet表中所包含的总行数
//        int rsRows = readsheet.getRows();
//        // System.out.println(rsRows);
//        /******************************** 字体样式设置 ****************************/
//
//        // 字体样式
//        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10,
//                WritableFont.NO_BOLD);
//        WritableCellFormat wcf = new WritableCellFormat(font);
//
//        /***********************************************************************/
//
//        Cell cell1 = readsheet.getCell(0, rsRows);
//
//        if (cell1.getContents().equals("")) {
//            Label labetest1 = new Label(0, rsRows, caseNo); // 第1列--用例编号；
//            Label labetest2 = new Label(1, rsRows, testPoint); // 第2列--用例标题；
//            Label labetest3 = new Label(2, rsRows, testData); // 第3列--测试数据；
//            Label labetest4 = new Label(3, rsRows, preResult); // 第4列--预期结果；
//            Label labetest5 = new Label(4, rsRows, fresult); // 第5列--实际结果；
//
//            // 两个值同时相等才会显示通过
//            if (preResult.equals(fresult)) {
//                result = "通过";
//                wcf.setBackground(Colour.BRIGHT_GREEN); // 通过案例标注绿色
//            } else {
//                result = "不通过";
//                wcf.setBackground(Colour.RED); // 不通过案例标注红色
//            }
//            Label labetest6 = new Label(5, rsRows, result, wcf); // 第6列--执行结果；
//            readsheet.addCell(labetest1);
//            readsheet.addCell(labetest2);
//            readsheet.addCell(labetest3);
//            readsheet.addCell(labetest4);
//            readsheet.addCell(labetest5);
//            readsheet.addCell(labetest6);
//        }
//
//        wbook.write();
//        wbook.close();
//    }
//
//    /*
//     * cOutputFile方法创建输出文件
//     * cOutputFile方法返回文件路径，作为wOutputFile的入参
//     */
//    public String cOutputFile(String tradeType) throws IOException,
//            WriteException {
//        String temp_str = "";
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        // 获取时间戳
//        temp_str = sdf.format(date);
//        // 以时间戳命名结果文件，确保唯一
//        String filepath = "D:\\develop\\软件测试\\软件测试练习\\jmeter\\day03\\接口测试实战(一)\\" + tradeType + "_output_" + "_" + temp_str
//                + ".xls";
//
//        File output = new File(filepath); // 把filepath封装成一个File对象
//
//        if (!output.isFile()) {
//            // 如果指定文件不存在，则创建该文件
//            output.createNewFile();
//            WritableWorkbook writeBook = Workbook.createWorkbook(output); // 创建excel的文档对象
//            // createSheet(Sheet名称，第几个Sheet)
//            WritableSheet Sheet = writeBook.createSheet("输出结果", 0);
//            // 字体样式
//            WritableFont headFont = new WritableFont(
//                    WritableFont.createFont("宋体"), 11, WritableFont.BOLD);
//            WritableCellFormat headwcf = new WritableCellFormat(headFont);
//            // 灰色
//            headwcf.setBackground(Colour.GRAY_25);
//            // Sheet.setColumnView(列号,宽度)
//            Sheet.setColumnView(0, 11);
//            Sheet.setColumnView(1, 20);
//            Sheet.setColumnView(2, 40);
//            Sheet.setColumnView(3, 10);
//            Sheet.setColumnView(4, 10);
//            Sheet.setColumnView(5, 10);
//
//            // 设置文字居中对齐方式
//            headwcf.setAlignment(Alignment.CENTRE);
//            // 设置垂直居中
//            headwcf.setVerticalAlignment(VerticalAlignment.CENTRE);
//            // Label(列号,行号,内容)
//            Label label00 = new Label(0, 0, "用例编号", headwcf);
//            Label label10 = new Label(1, 0, "用例标题", headwcf);
//            Label label20 = new Label(2, 0, "测试数据", headwcf);
//            Label label30 = new Label(3, 0, "预期结果", headwcf);
//            Label label40 = new Label(4, 0, "实际结果", headwcf);
//            Label label50 = new Label(5, 0, "执行结果", headwcf);
//
//            Sheet.addCell(label00);
//            Sheet.addCell(label10);
//            Sheet.addCell(label20);
//            Sheet.addCell(label30);
//            Sheet.addCell(label40);
//            Sheet.addCell(label50);
//
//            writeBook.write();
//            writeBook.close();
//        }
//
//        return filepath;
//    }
//}
