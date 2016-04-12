package com.yoko.Controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize.NA;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import javax.swing.tree.TreePath;

import org.junit.Test;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.yoko.dao.DBOperation;

/**
 * @author ren
 * @ͼƬ�ϴ�
 */
public class SmartuploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		/** �ж��Ƿ��½ ****δ��*** */
		String login = (String) request.getSession().getAttribute("login");
		if (login.equals("true")) {
			request.getSession().setAttribute("login", "true");
		}
		/** �ж��Ƿ��¼ */
		DBOperation dbo = new DBOperation();
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html; charset=gbk");

		/****** ��ʼ����ͼƬ�洢·�� ******/
		String tomcatPath = getServletContext().getRealPath("\\");
		String temppath = "";
		StringTokenizer tokenizer = new StringTokenizer(tomcatPath, "\\");
		int st = tokenizer.countTokens() - 2;
		while (st-- > 0) {
			temppath += tokenizer.nextToken() + "\\";
		}
		temppath += "src\\YokohamaImage\\";
		testPath(temppath);
		/*** ����ͼƬ�洢·������ ****/

		SmartUpload upload = new SmartUpload();
		Request request2 = upload.getRequest();
		PageContext pageContext = JspFactory
				.getDefaultFactory()
				.getPageContext(this, request, response, null, true, 8192, true);
		upload.initialize(pageContext);// 1��initialize
		try {
			upload.upload();// 2��upload
			String caseName = tString(request2.getParameter("casename"));
			String intro = tString(request2.getParameter("intro"));
			int i = upload.getFiles().getCount();
			String[] img = new String[4];// ����������ͼƬ��
			for (int j = 0; j < i; j++) {
				String ext = upload.getFiles().getFile(j).getFileExt();// ��ȡ�ļ���׺��
				String path = "";
				if (isImg(ext)) {// ͨ���жϺ�׺ȷ���Ƿ�ΪͼƬ
					String name = getStr() + j + "." + ext;// ������
					path = temppath + name;
					img[j] = name;
					upload.getFiles().getFile(j).saveAs(path);// saveAs();���ڴ洢ʱ�����ļ���,����ΪĿ¼+�ļ���
					System.out.println(path + "�ϴ��ɹ�");
				} else {
					System.out.println(path + "�ϴ�ʧ��");
				}
			}
			boolean ret = dbo.uploadCase(caseName, img[0], img[1], img[2],
					img[3], intro);
			System.out.println("�������ݿ�" + ret);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

	/** ת���ַ��������ʽ */
	private String tString(String str) {
		String temp = null;
		try {
			temp = new String(str.getBytes("gbk"), "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/** ��ȡ��ǰʱ�� */
	public String getStr() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String temp = sdf.format(date);
		System.out.println(temp);
		return temp;
	}

	/** ����ϴ��ļ��Ƿ�ΪͼƬ */
	public boolean isImage(String name) {
		boolean ret = true;
		try {
			Image image = ImageIO.read(new File(name));
			if (image == null) {
				ret = false;
				System.out.println(name + "����ͼƬ");
			} else {
				System.out.println(name + "��ͼƬ");
			}
		} catch (IOException ex) {
			ret = false;
			System.out.println(name + "����ͼƬ.");
		}
		return ret;
	}

	public boolean isImg(String name) {
		boolean ret = false;
		String format = "bmp.dib.gif.jfif.jpe.jpeg.jpg.png.tif.tiff.ico";
		String temp = name.toLowerCase();
		StringTokenizer tokenizer = new StringTokenizer(format, ".");
		while (tokenizer.hasMoreElements()) {
			String f = tokenizer.nextToken();
			if (f.equals(temp) || f == temp) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	/** ����·���Ƿ���� */
	public void testPath(String path) {
		File file = new File(path);
		if (file.exists()) {
			System.out.println("Ŀ¼����");
		} else {
			System.out.println("Ŀ¼������,����Ŀ¼");
			file.mkdirs();
		}
	}

	@Test
	public void test() {
		// getStr();
		// testPath("E:\\JavaInstall\\apache-tomcat-7.0.64\\wtpwebapps\\Yokohama\\Images\\");
		/*
		 * String path =
		 * "E:\\JavaInstall\\apache-tomcat-7.0.64\\wtpwebapps\\Yokohama\\";
		 * StringTokenizer tokenizer = new StringTokenizer(path, "\\"); int i =
		 * tokenizer.countTokens(); int j = i - 2; System.out.println(i + ";");
		 * while (j-- > 0) { System.out.println(tokenizer.nextToken()); }
		 */
		// System.out.println(isImg("Png"));

	}
}
