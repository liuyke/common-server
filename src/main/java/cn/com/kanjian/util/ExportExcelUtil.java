package cn.com.kanjian.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

import cn.com.kanjian.annotation.ExcelFiled;

public class ExportExcelUtil {

	/**
	 * 导出excel
	 * 
	 * @param datas 数据集合
	 * @param clz 数据集合的实际类型
	 * @param os excel要输出到的流
	 * @param excelFileName excel文件名称
	 * @throws Exception
	 */
	public static void exportExcel(Collection<?> datas, Class<?> clz, OutputStream os) throws Exception {
		List<ExcelFiledVo> excelFiledVos = getExcelFields(clz);
		HSSFWorkbook workbook = generateExcel(datas, excelFiledVos);
		workbook.write(os);
	}

	private static HSSFWorkbook generateExcel(Collection<?> datas, List<ExcelFiledVo> excelFiledVos) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow titleRow = sheet.createRow(0);
		int index = 0;
		for (ExcelFiledVo vo : excelFiledVos) {//生成excel头信息
			HSSFCell cell = titleRow.createCell(index++);
			cell.setCellValue(vo.getTitle());
		}
		index = 1;
		for(Object d:datas) {//生成数据部分
			HSSFRow dataRow = sheet.createRow(index++);
			int rowIndex = 0;
			for (ExcelFiledVo vo : excelFiledVos) {
				HSSFCell cell = dataRow.createCell(rowIndex++);
				Object value = PropertyUtils.getProperty(d, vo.getField().getName());
				if (value != null) {
					cell.setCellValue(value.toString());
				}
			}
		}
		return workbook;
	}

	private static List<ExcelFiledVo> getExcelFields(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();
		List<ExcelFiledVo> excelFiledVos = new ArrayList<ExcelFiledVo>();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ExcelFiled.class)) {
				ExcelFiled excelFiled = field.getAnnotation(ExcelFiled.class);
				String title = excelFiled.title();
				if (!StringUtils.isEmpty(title)) {
					ExcelFiledVo excelFiledVo = new ExcelFiledVo(title, excelFiled.sequence(), field);
					excelFiledVos.add(excelFiledVo);
				}
			}
		}
		Collections.sort(excelFiledVos);
		return excelFiledVos;
	}

	public static final class ExcelFiledVo implements Comparable<ExcelFiledVo> {
		private String title;
		private int sequence;
		private Field field;

		public ExcelFiledVo() {
		}

		public ExcelFiledVo(String title, int sequence, Field field) {
			super();
			this.title = title;
			this.sequence = sequence;
			this.field = field;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getSequence() {
			return sequence;
		}

		public void setSequence(int sequence) {
			this.sequence = sequence;
		}

		public Field getField() {
			return field;
		}

		public void setField(Field field) {
			this.field = field;
		}

		@Override
		public int compareTo(ExcelFiledVo o) {
			if(o == null) return -1;
			return this.sequence - o.getSequence();
		}
	}

}
