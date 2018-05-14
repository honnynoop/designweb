package com.jungbo.servlet.centric.help;
import javax.servlet.http.HttpServletRequest;
public class Command {

	private String command;
	public Command(HttpServletRequest request) {
		command=request.getParameter("command");
		if(command==null || command.trim().equals("")){
			command="null";
		}
	}//
	public String getCommand() {
		return command;
	}//
	@Override
	public String toString() {
		return "Command [command=" + command +"]";
	}
}//
