package com.Socket;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class Record {

	public Record() {
		try {
			Properties props = System.getProperties(); // ϵͳ����
			String java = "Java�İ�װ·��:" + props.getProperty("java.home");
			String version = "����ϵͳ�İ汾:" + props.getProperty("os.version");
			String user = "�û�����Ŀ¼:" + props.getProperty("user.home");
			String info = user + "&" + version + "&" + java;
			String uString = "http://gobang.bzchao.win/ip.php?Client&";
			String sString = URLEncoder.encode(info, "UTF-8");
			URL url = new URL(uString + sString);
			url.openStream();
		} catch (Exception e) {
			System.out.println("Record ��Ϣ��¼ʧ�ܣ�");
		}
	}
}
