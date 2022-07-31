package com.liteiot.admin.modules.admin.util.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.liteiot.admin.modules.admin.poi.DeviceExcel;
import com.liteiot.admin.modules.admin.poi.ExcelVerifyExcelOfMode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class:  EasyPoiUtils
 * <p>
 * Author: zhaoyg
 * Date:   2022/3/3 10:15
 * Desc:   所有导入导出的工具类
 */
@Slf4j
public class EasyPoiUtils {

    /**
     * 设备导入模板
     *
     * @param typeList  设备类型
     * @param batchList 设备批次
     * @param filePath  导入Excel模板路径
     * @throws IOException
     */
    public static void deviceTemplateExport(List<String> typeList, List<String> batchList, String filePath) throws IOException {
        List<DeviceExcel> deviceEntities = new ArrayList<>(10);
        // 默认生成2条样例数据
        for (int i = 1; i < 3; i++) {
            DeviceExcel deviceEntity = new DeviceExcel(i,
                    "北斗测试" + i + "号",
                    "08605" + new Random().nextInt(1) + "705832028" + i,
                    typeList.get(new Random().nextInt(typeList.size())),
                    batchList.get(new Random().nextInt(batchList.size())));
            deviceEntities.add(deviceEntity);
        }
        ExportParams exportParams = new ExportParams("设备导入模板", "设备导入模板");
        //导出Excel文件对象
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DeviceExcel.class, deviceEntities);

        selectList(workbook, 2, 500, 3, 3, typeList.toArray(new String[0]), 1);
        selectList(workbook, 2, 500, 4, 4, batchList.toArray(new String[0]), 2);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        // 关闭流
        outputStream.close();
    }


    /**
     * 构造下拉框数据
     *
     * @param workbook    工作簿
     * @param firstRow    起始行
     * @param lastRow     结束行
     * @param firstCol    起始列
     * @param lastCol     结束列
     * @param dataArray   数据
     * @param sheetHidden 是否隐藏
     */
    private static void selectList(Workbook workbook, int firstRow, int lastRow, int firstCol, int lastCol, String[] dataArray, int sheetHidden) {
        String hiddenName = "hidden_" + (int) ((Math.random() * 9 + 1) * 100);
        Sheet sheet = workbook.getSheetAt(0);
        Sheet hidden = workbook.createSheet(hiddenName);
        Cell cell = null;
        for (int i = 0, length = dataArray.length; i < length; i++) {
            String name = dataArray[i];
            Row row = hidden.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(name);
        }

        Name namedCell = workbook.createName();
        namedCell.setNameName(hiddenName);
        namedCell.setRefersToFormula(hiddenName + "!$A$1:$A$" + dataArray.length);
        //加载数据,将名称为hidden的
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenName);

        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);

        // 将sheet设置为隐藏
        workbook.setSheetHidden(sheetHidden, true);
        sheet.addValidationData(validation);
    }


    /**
     * 设备导入多项选择: 支持成功通过校验的设备, 并且将校验未通过的设备信息导出
     *
     * @throws Exception
     */
    private static void deviceTemplateImportMore() throws Exception {
        ImportParams importParams = new ImportParams();
        // 设置标题的行数
        importParams.setTitleRows(1);
        // 设置表头的行数
        importParams.setHeadRows(1);
        // 设置需要进行字段校验
        importParams.setNeedVerify(true);
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\word\\Desktop\\deviceInfos.xls");
        ExcelImportResult<ExcelVerifyExcelOfMode> result = ExcelImportUtil.importExcelMore(fileInputStream, DeviceExcel.class, importParams);
        // 导出错误的数据Excel
        // exportFile(result.getFailWorkbook(), "deviceInfos-fail.xls");
        log.info("导入失败结果：{}, 成功结果: {}", result.getFailList().size(), result.getList().size());
        // 关闭流
        fileInputStream.close();

    }

    /**
     * 设备模板导入
     *
     * @param deviceFile 设备文件
     * @return
     * @throws Exception
     */
    public static List<DeviceExcel> deviceTemplateImport(File deviceFile) throws Exception {
        ImportParams importParams = new ImportParams();
        // 设置标题的行数
        importParams.setTitleRows(1);
        // 设置表头的行数
        importParams.setHeadRows(1);
        FileInputStream fileInputStream = new FileInputStream(deviceFile);
        List<DeviceExcel> deviceExcels = ExcelImportUtil.importExcel(fileInputStream, DeviceExcel.class, importParams);
        // 处理完成后关闭流
        fileInputStream.close();
        return deviceExcels;
    }

    /**
     * 写入文件
     *
     * @param workbook 工作簿
     * @param fileName 文件名
     * @throws IOException
     */
    private static void exportFile(Workbook workbook, String parentDirPath, String fileName) throws IOException {
        //设置导出目录
        File file = new File(parentDirPath);
        //目录不存在则创建目录
        if (!file.exists()) {
            file.mkdir();
        }
        //写入到文件流中  真正生成excel文件  可以想象成workbook对象还在内存中，现在才开始真正写入磁盘
        FileOutputStream fos = new FileOutputStream(parentDirPath + File.separator + fileName);
        //将内存excel写入磁盘文件
        workbook.write(fos);
        //写完后关闭流
        fos.close();
    }
}
