package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import com.jungbo.servlet.centric.help.Command;
public class ActionFactory {
	private static ActionFactory actionFactory;
	private ActionFactory() {
	}
	public static ActionFactory getInstance(){
		if(actionFactory==null){
			actionFactory=new ActionFactory();
		}
		return actionFactory;
	}
	public  Action getCommand(String command){
		Action comm=null;
		if(command.equalsIgnoreCase("list")){
			comm=new CustUserListAction();
		}else if(command.equalsIgnoreCase("add")){
			comm=new CustUserAddAction();
		}else if(command.equalsIgnoreCase("detail")){
			comm=new CustUserDetailAction();
		} else if(command.equalsIgnoreCase("bfupdate")){
			comm=new CustUserBeforeUpdateAction();
		} else if(command.equalsIgnoreCase("update")){
			comm=new CustUserUpdateAction();
		} else if(command.equalsIgnoreCase("delete")){
			comm=new CustUserDeleteAction();
		} else if(command.equalsIgnoreCase("muldel")){
			comm=new CustUserMultiDeleteActioin();
		} else{
			comm=new CustUserNullAction();
		}
		return comm;
	} //
	public  Action getCommand(HttpServletRequest request){
		String scmd=request.getParameter("command");
		return getCommand(scmd);
	} //
	public  Action getCommand(Command comd){
		return getCommand(comd.getCommand());
	} //
	
}//
