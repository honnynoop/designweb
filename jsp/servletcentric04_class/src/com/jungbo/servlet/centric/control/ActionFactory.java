package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import com.jungbo.servlet.centric.config.ActionClassMapping;
import com.jungbo.servlet.centric.help.Command;

public class ActionFactory {
	
	private static ActionFactory actionFactory;
	private ActionClassMapping actionmaps;
	private ActionFactory() {
	}
	
	public ActionClassMapping getActionmaps() {
		return actionmaps;
	}
	public void setActionmaps(ActionClassMapping actionmaps) {
		this.actionmaps = actionmaps;
	}
	public static ActionFactory getInstance(){
		if(actionFactory==null){
			actionFactory=new ActionFactory();
		}
		return actionFactory;
	}
	public  Action getCommand(String command){
		return actionmaps.get(command.trim());
	} //
	public  Action getCommand(HttpServletRequest request){
		String scmd=request.getParameter("command");
		return getCommand(scmd);
	} //
	public  Action getCommand(Command comd){
		return getCommand(comd.getCommand());
	} //
	
}//
