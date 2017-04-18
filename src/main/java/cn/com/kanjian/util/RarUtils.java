package cn.com.kanjian.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

public class RarUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RarUtils.class);

	/**
	 * 解压rar文件，并返回解压的文件列表
	 * 
	 * @param file  目标rar文件
	 * @return 解压的文件列表
	 * @throws RarException 解压出错
	 * @throws IOException IO异常
	 */
	public static List<File> unRarFile(File file) throws IOException, RarException {
		List<File> files = new ArrayList<File>();
		String extension = FilenameUtils.getExtension(file.getName());
		if (!"rar".equalsIgnoreCase(extension)) {
			throw new IllegalArgumentException("不支持的文件类型：" + extension);
		}
		
		String destDir = file.getParent();// 解压到目标文件的同一级目录
		File out = new File(destDir);
		if (!out.exists()) {
			out.mkdirs();
		}
		
		Archive a = null;
		try {
			a = new Archive(file);
			FileHeader fh = null;
			while ((fh = a.nextFileHeader()) != null) {
				String fileName = fh.getFileNameW().replace("\\", File.separator);
				if (fh.isDirectory()) { // 文件夹
					File fol = new File(destDir + File.separator + fileName);
					fol.mkdirs();
				} else {
					File destFile = new File(destDir + File.separator + fileName);
					if (!destFile.getParentFile().exists()) {
						destFile.getParentFile().mkdirs();
					}
					if (destFile.exists())
						destFile.delete();
					else
						destFile.createNewFile();
					FileOutputStream os = new FileOutputStream(destFile);
					a.extractFile(fh, os);
					os.close();
					files.add(destFile);
				}
			}
		} catch (IOException e) {
			LOGGER.error("读写文件异常", e);
			throw e;
		} catch (RarException e) {
			LOGGER.error("解压文件出错", e);
			throw e;
		} finally {
			try {
				if (a != null)
					a.close();
			} catch (IOException e) {
				LOGGER.warn("关闭流异常", e);
			}
		}
		return files;
	}

	public static void main(String[] args) {
		try {
			List<File> unRarFile = unRarFile(new File("/Users/liuyunke/Downloads/表情包.rar"));
			for (File file : unRarFile) {
				System.out.println(file.getAbsolutePath() + "/" + file.getName());
			}
		} catch (Exception e) {
			LOGGER.error("解压文件异常", e);
		}
	}

}