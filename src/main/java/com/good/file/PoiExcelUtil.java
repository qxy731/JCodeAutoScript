package com.good.file;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.good.comm.StringUtils;
import com.good.comm.TimeUtil;

public class PoiExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(PoiExcelUtil.class);
    private String decimalFormat = "0.00";// 保存到小数点后2位
    private String dateFormat = "yyyy-MM-dd";
    private Color bgColor = new Color(0, 204, 255);
    public static String COLUMN_NAMES = "columnNames";
    public static String COLUMN_DISPLAY_NAMES = "columnLabels";
    private static int INVALID_PARAMETERS = 1;
    private static int WRITE_EXCEL_EXCEPTION = 2;

    /**
     * @return the decimalFormat
     */
    public String getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @param decimalFormat
     *            the decimalFormat to set
     */
    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    /**
     * @return the dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat
     *            the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the bgColor
     */
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * @param bgColor
     *            the bgColor to set
     */
    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public PoiExcelUtil() {
    }

    /**
     * 将List中的所有对象输出到Excel, list包含的对象必须为HashMap， map中包含了所有字段和值。
     * 通过字段名，就可以从map中得到该字段的值，从ibatis返回的记录集就是一这种形式保存。
     * 
     * 建议List数据类型为String或Integer
     * 
     * @param request
     * @param response
     * @param list
     *              要输出的数据
     * @param sheetName
     *              要输出到excel中的工作表名，如果为空，则取默认值Sheet1
     * @param columnNames
     *              要输出的所有字段名，以逗号分隔
     * @param columnDisplayNames
     *              要输出的字段名的显示名称，通常为中文，以逗号分隔
     * @param columnWidth
     *              输出列的宽度，数值形数组
     * @return String
     *              生成Excel绝对路径
     */
    public static String exportToExcel(HttpServletRequest request, HttpServletResponse response, List<?> list, String sheetName, String columnNames, String columnDisplayNames, int[] columnWidth) {
        String realPath = request.getServletContext().getRealPath("/upload") + File.separator + "exportFiles" + File.separator + "Empty.xlsx";
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String excelFileName = TimeUtil.paserString(new Date(), "yyyyMMddHHmmssS") + ".xlsx";
        String filename = request.getSession().getServletContext().getRealPath("/upload") + File.separator + "exportFiles" + File.separator + excelFileName;
        PoiExcelUtil poiExcelUtil = new PoiExcelUtil();
        int result = poiExcelUtil.writeListToExcel(list, filename, sheetName, columnNames, columnDisplayNames, columnWidth);
        if (result == 0) {
            realPath = filename;
        }
        return realPath;
    }

    /**
     * @param list
     *            要输出的数据
     * @param filename
     *            要输出的excel文件名，包含路径
     * @param sheetName
     *            要输出到excel中的工作表名，如果为空，则取默认值Sheet1
     * @param columnNames
     *            要输出的所有字段名，以逗号分隔
     * @param columnDisplayNames
     *            要输出的字段名的显示名称，通常为中文，以逗号分隔
     * @param columnWidth
     *            输出列的宽度，数值形数组
     * @return 0 - 正常返回， 1 - 输入参数非法，2 - 写excel异常发生
     */
    public int writeListToExcel(List<?> list, String filename, String sheetName, String columnNames, String columnDisplayNames, int[] columnWidth) {
        if (list == null || StringUtils.isEmpty(filename) || StringUtils.isEmpty(columnNames) || StringUtils.isEmpty(columnDisplayNames)) {
            return INVALID_PARAMETERS;
        }
        String[] columnName = columnNames.split(",");
        String[] columnDisplayName = columnDisplayNames.split(",");
        XSSFWorkbook wb = null;

        if (StringUtils.isEmpty(sheetName)) {
            sheetName = "Sheet1";
        }

        int numberOfFields = columnName.length, result = 0;
        int[] columnWidths = new int[numberOfFields];

        try {
            String path = filename.substring(0, filename.lastIndexOf(File.separator));
            if (path != null && !"".equals(path)) {
                File tmp = new File(path);
                if (!tmp.isDirectory()) {
                    tmp.mkdirs();
                }
            }
            File file = new File(filename);
            if (file.exists())
                file.delete();

            wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = null;

            XSSFCellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(new XSSFColor(bgColor));
            style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            XSSFFont font = wb.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);

            for (int j = 0; j < numberOfFields; j++) {
                cell = row.createCell(j, XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(columnDisplayName[j].toString());
                cell.setCellStyle(style);
                columnWidths[j] = columnDisplayName[j].length() * 4 + 2;
                if (columnWidth == null) {
                    sheet.setColumnWidth(j, columnWidths[j] * 256);
                } else {
                    sheet.setColumnWidth(j, columnWidth[j] * 256);
                }
            }

            int rows = 1;
            // 将List中的对象输出到excel
            for (int i = 0; i < list.size(); i++) {
                Object object = list.get(i);
                if (object == null)
                    continue;
                row = sheet.createRow(rows);
                for (int column = 0; column < numberOfFields; column++) {
                    Object obj = PropertyUtils.getProperty(object, columnName[column]);
                    if (obj == null)
                        continue;
                    if (obj instanceof String) {
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Integer) {
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(((Integer) obj).doubleValue());
                    } else if (obj instanceof Timestamp) {
                        cell = row.createCell(column);
                        cell.setCellValue((Timestamp) obj);
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        XSSFDataFormat format = wb.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat(dateFormat + " hh:mm:ss"));
                        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                        cell.setCellStyle(cellStyle);
                    } else if (obj instanceof BigDecimal) {
                        cell = row.createCell(column);
                        cell.setCellValue(((BigDecimal) obj).doubleValue());
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        XSSFDataFormat format = wb.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat(decimalFormat));
                        cell.setCellStyle(cellStyle);
                    } else if (obj instanceof Double) {
                        cell = row.createCell(column);
                        cell.setCellValue(((Double) obj).doubleValue());
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        XSSFDataFormat format = wb.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat(decimalFormat));
                        cell.setCellStyle(cellStyle);
                    } else if (obj instanceof Float) {
                        cell = row.createCell(column);
                        cell.setCellValue(((Float) obj).doubleValue());
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        XSSFDataFormat format = wb.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat(decimalFormat));
                        cell.setCellStyle(cellStyle);
                    } else if ((obj instanceof Long)) {
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(((Long) obj).doubleValue());
                    } else if ((obj instanceof Short)) {
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(((Short) obj).doubleValue());
                    } else if (obj instanceof Date) {
                        cell = row.createCell(column);
                        cell.setCellValue((Date) obj);
                        XSSFCellStyle cellStyle = wb.createCellStyle();
                        XSSFDataFormat format = wb.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat(dateFormat));
                        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
                        cell.setCellStyle(cellStyle);
                    } else if (obj instanceof Boolean) {
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_BOOLEAN);
                        cell.setCellValue(((Boolean) obj).booleanValue());
                    } else {
                        // 把其他类型都作为字符串处理
                        cell = row.createCell(column, XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(obj.toString());
                    }
                }
                rows++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = WRITE_EXCEL_EXCEPTION;
            logger.error("写Excel失败！===>sheetName: " + sheetName + " ===>" + e.getMessage());
        } finally {
            try {
                if (wb != null) {
                    OutputStream fOut = new FileOutputStream(filename);
                    wb.write(fOut);
                    fOut.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * 文件导入公共类.
     * 
     * @param file
     *            文件
     * @param maxCol
     *            最大列
     * @param maxRol
     *            第几行开始读取
     * @return
     * @throws BiffException
     * @throws IOException
     */
    public static List<Map<String, Object>> readFromXlsx(File file, int maxCol, int maxRol) throws IOException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        InputStream in = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(in);
        in.close();
        XSSFSheet sheet = wb.getSheetAt(0);
        if (sheet == null) {
            throw new RuntimeException("文件不存在");
        }
        int maxRow = sheet.getLastRowNum();
        for (int r = maxRol; r <= maxRow; r++) {
            Map<String, Object> map = new HashMap<String, Object>();
            XSSFCell[] cells = new XSSFCell[maxCol];
            for (int n = 0; n < maxCol; n++) {
                cells[n] = sheet.getRow(r).getCell(n);
            }
            if (cells == null || cells.length == 0) {
                continue;
            }
            for (int s = 0; s < maxCol; s++) {
                if (s >= cells.length) {
                    continue;
                }
                switch (cells[s].getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (!"".equals(cells[s].getNumericCellValue())) {
                        map.put(String.valueOf(s), Double.toString(cells[s].getNumericCellValue()));
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    if (!"".equals(cells[s].getStringCellValue()) && cells[s].getStringCellValue() != null) {
                        map.put(String.valueOf(s), cells[s].getStringCellValue());
                    }
                    break;
                default:
                    if (!"".equals(cells[s].getStringCellValue()) && cells[s].getStringCellValue() != null) {
                        map.put(String.valueOf(s), cells[s].getStringCellValue());
                    }
                    break;
                }
            }
            if (map.size() != 0) {
                list.add(map);
            }
        }
        return list;
    }

}
