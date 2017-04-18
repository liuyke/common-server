package cn.com.kanjian.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.com.kanjian.model.User;
import cn.com.kanjian.util.ExportExcelUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("upload")
	public String upload() {
		return "upload";
	}
	
	@RequestMapping(value = "/importQualify", method = RequestMethod.POST)
	@ResponseBody
	public Object importQualify(Integer type, String idCard, HttpServletRequest request) throws IOException {
		System.out.println(type + ",-" + idCard);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> it = multipartRequest.getFileNames();
		MultipartFile uploadFile = null;
		while (it.hasNext()) {
			String string = it.next();
			System.out.println("uploadFile---" + string);
			uploadFile = multipartRequest.getFile(string);
			FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), new File("/Users/liuyunke/1.jpg"));
		}
		if (uploadFile == null) {
			return "缺少文件";
		}
		// 判断大小
		long size = uploadFile.getSize();
		if (size > 1024L * 10240L) {
			// return WmCommonUtil.constructResponse(WaimaiApiStatus.FAILED,
			// "文件过大", null);
		}

		String validExtension = "[jpg][jpeg][gif][png][zip][rar]";
		String fileName = uploadFile.getOriginalFilename();
		String extension = FilenameUtils.getExtension(fileName);
		if (!validExtension.contains("[" + extension.toLowerCase() + "]")) {
			// return WmCommonUtil.constructResponse(WaimaiApiStatus.FAILED,
			// "文件类型不支持", null);
		}
		return null;
	}
	
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse resp) {
		resp.setContentType("octets/stream");
		resp.addHeader("Content-Disposition", "attachment;filename=test.xls");
		List<User> users = new ArrayList<User>();
		users.add(new User("zhangsan", "张三", "","", null, "头像1", 1, 1));
		users.add(new User("lisi", "李四", "","", null, "头像2", 1, 1));
		users.add(new User("wangwu", "王五", "","", null, "头像3", 1, 1));
		try {
			ExportExcelUtil.exportExcel(users, User.class, resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
