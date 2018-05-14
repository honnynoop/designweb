package com.jungbo.servlet.centric.control;
import javax.servlet.http.HttpServletRequest;
import com.jungbo.servlet.centric.config.ActionClassMapping;
import com.jungbo.servlet.centric.help.Command;
public class ActionFactory {
	private static ActionFactory actionFactory;
	private ActionFactory() {}
	public static ActionFactory getInstance(){
		if(actionFactory==null){
			actionFactory=new ActionFactory();
		}
		return actionFactory;
	}
	//---------------------�̱�
	private ActionClassMapping actionmaps;
	public ActionClassMapping getActionmaps() {
		return actionmaps;
	}
	//�ݵ�� Ŭ���� ������ ���� �о�;��Ѵ�.
	public void setActionmaps(ActionClassMapping actionmaps) {
		this.actionmaps = actionmaps;
	}
	//getCommand �����ε�  ActionClassMapping���� ���ε�����ã��
	public  Action getCommand(String command){
		return actionmaps.get(command.trim());
	} 
	//request -> getCommand (String) 
	public  Action getCommand(HttpServletRequest request){
		String scmd=request.getParameter("command");
		return getCommand(scmd);
	} 
	//Command -> getCommand (String) 
	public  Action getCommand(Command comd){
		return getCommand(comd.getCommand());
	} //
}//
