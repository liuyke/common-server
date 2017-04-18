package com.liuyk.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import net.coobird.thumbnailator.Thumbnails;

public class TestThumbnailator {

	@Test
	public void testCompressImg() {
		try {
			Thumbnails.of("/Users/liuyunke/Pictures/1.jpg").outputQuality(0.5).scale(1)
					.toOutputStream(new ByteArrayOutputStream());
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			new ByteArrayInputStream(arrayOutputStream.toByteArray());
			arrayOutputStream.close();
			byte[] buf = new byte[1023];
			ByteArrayInputStream bin = new ByteArrayInputStream(buf);
			Thumbnails.of(bin).scale(1).toOutputStream(new ByteArrayOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNumFormat() {
		System.out.println(String.format("%.2fM", new File("/Users/liuyunke/Pictures/1.jpg").length() / 1024.0 / 1024));
	}

	@Test
	public void testUnzip() throws Exception {
		List<InputStream> unzip = unzip(new File("/Users/liuyunke/java2.zip"));
		int index = 1;
		for (InputStream inputStream : unzip) {
			IOUtils.copy(inputStream, new FileOutputStream("/Users/liuyunke/" + index++ + ".java"));
		}
	}

	@Test
	public void testInOutStream() throws Exception {
		FileInputStream inputStream = new FileInputStream("/Users/liuyunke/java2.zip");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copy(inputStream, outputStream);
		 ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
		 FileOutputStream fos = new FileOutputStream("/Users/liuyunke/java3.zip");
		 IOUtils.copy(byteArrayInputStream, fos);
	}
	
	public static List<InputStream> unzip(File zipFile) throws IOException {
		ZipFile zip = new ZipFile(zipFile);
		try {
			Enumeration<?> zipEntries = zip.entries();
			List<InputStream> list = new ArrayList<InputStream>();
			while (zipEntries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) zipEntries.nextElement();
				if (!entry.isDirectory()) {
					String entryName = entry.getName();
					long size = entry.getSize();
					System.out.println(FilenameUtils.getBaseName(entryName) + "." + FilenameUtils.getExtension(entryName) + "=" + entry.getSize());
					InputStream in = zip.getInputStream(entry);
					list.add(in);
				}
			}
			return list;
		} finally {
			try {
				zip.close();
			} catch (IOException e) {
				//ignore
			}
		}
	}
	
	@Test
	public void testUnRar() throws Exception {
		unRar(new File("/Users/liuyunke/表情包.rar"));
	}
	
	@Test
	public void testFastJson() throws Exception {
		List<Long> list = JSON.parseArray("[123456]", Long.class);
		System.out.println(list);
	}
	
	private static void unRar(File file) throws IOException, RarException {
        Archive a = null;
        try {
            a = new Archive(file);
            FileHeader fh = null;
            while ((fh = a.nextFileHeader()) != null) {
                if (!fh.isDirectory()) {
                	String fileName = fh.getFileNameW().replace("\\", File.separator);
                    String name = FilenameUtils.getName(fileName);
                    int size = fh.getPackSize();
                    System.out.println(name + "-----" + size);
                	ByteArrayOutputStream os = new ByteArrayOutputStream();
                    a.extractFile(fh, os);
                    ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
                    System.out.println(in);
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (RarException e) {
            throw e;
        } finally {
            try {
                if (a != null)
                    a.close();
            } catch (IOException e) {
            }
        }
    }

}
