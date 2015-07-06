package fnd.ishrivas.edh.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import fnd.ishrivas.edh.common.Const;
import fnd.ishrivas.edh.common.SystemUtils;
import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;
import net.neoremind.sshxcute.task.impl.ExecShellScript;

public class SSHController {
	public static SSHExec ssh;
	public static boolean isSSHInitialized = false;
	public static EBizInstance122x ebsl;
	public static String ebsPath;
	public static ArrayList<String> managedSrvList = new ArrayList<String>();
	
	public static boolean initializeSSH(EBizInstance122x ebs){
		ebsl = ebs;
		ConnBean connection = new ConnBean(ebs.host, ebs.mtUsername, ebs.mtPassword);
		ssh = SSHExec.getInstance(connection);
		
		if(ssh!=null)
			return (isSSHInitialized = true);
		else
			return (isSSHInitialized = false);
	}
	
	public static void sourceR122x(){
		if(!isSSHInitialized)
			return;

		CustomTask sourceTask = new ExecCommand(Const.CMD_SOURCE_EBS_RUN, "echo GETPATH=$PATH");
		Result result;
		try {
			ssh.connect();
			result = ssh.exec(sourceTask);
			
			if(result.isSuccess){
				String rout = result.sysout;
				ebsPath = rout.substring(rout.indexOf("GETPATH=")+8);
				Const.ENV_PATH = ebsPath;
			}
		} catch (TaskExecFailException e) {
			e.printStackTrace();
		}
		
	}
	
	/* TODO: Get it from instance 
	 * hardcoded for now
	 */
	private static void populateManagedServerList(){
		managedSrvList.add("oacore_server1");
		managedSrvList.add("oaea_server1");
		managedSrvList.add("forms_server1");
		managedSrvList.add("forms-c4ws_server1");
		managedSrvList.add("oafm_server1");
	}
	
	public static Result setup(EBizInstance122x ebs) throws Exception{
		
		Result res = new Result();
		
		initializeSSH(ebs);
		populateManagedServerList();
		generateScript(Const.SCRIPT_TYPE_BOUNCE_ALL, null);
		for(String mserv : managedSrvList)
			generateScript(Const.SCRIPT_TYPE_BOUNCE_MANAGED, mserv);
		
		sourceR122x();
		
		/* Make temp dir for scripts on instance */
		CustomTask mkdirTask = new ExecCommand("cd ~", "mkdir ebs_dev_helper");
		ssh.connect();
		Result res1 = ssh.exec(mkdirTask);
		
		/* Upload generated script(s) */
		ssh.uploadSingleDataToServer(Const.TEMP_SCRIPTS_DIR+"//edh_bounceall.sh", Const.TEMP_SCRIPTS_DIR_INSTANCE);
		for(String mserv : managedSrvList)
			ssh.uploadSingleDataToServer(Const.TEMP_SCRIPTS_DIR+"\\edh_bounce_"+mserv+".sh", Const.TEMP_SCRIPTS_DIR_INSTANCE);
		
		/* Fix permissions */
		CustomTask permTask = new ExecCommand("cd ~", "chmod -R 777 ebs_dev_helper");
		Result res2 = ssh.exec(permTask);
		
		res.isSuccess = res2.isSuccess;
		res.sysout = res1.sysout + res2.sysout;
		res.error_msg = res1.error_msg + res2.error_msg;
		
		return res;
		
	}
	
	
	private static void generateScript(String scriptType, String managedServerName) throws FileNotFoundException{
		if(!isSSHInitialized)
			return;
			
		
		if(scriptType.equals(Const.SCRIPT_TYPE_BOUNCE_ALL)){
			SystemUtils.setUnixLineSeparator();
			
			PrintWriter pw = new PrintWriter(Const.TEMP_SCRIPTS_DIR+"\\edh_bounceall.sh");
			pw.println("echo \""+ebsl.weblogicAdminPassword+"\" | { adstpall.sh "+ebsl.appsUsername+"/"+ebsl.appsPassword+"; }");
			pw.println("echo \""+ebsl.weblogicAdminPassword+"\" | { adstrtal.sh "+ebsl.appsUsername+"/"+ebsl.appsPassword+"; }");
			pw.close();
			
			SystemUtils.resetLineSeperator();
		}
		
		if(scriptType.equals(Const.SCRIPT_TYPE_BOUNCE_MANAGED)){
			if(managedServerName==null)
				return;
			
			SystemUtils.setUnixLineSeparator();
			PrintWriter pw = new PrintWriter(Const.TEMP_SCRIPTS_DIR+"\\edh_bounce_"+managedServerName+".sh");
			pw.println("echo \""+ebsl.weblogicAdminPassword+"\" | { admanagedsrvctl.sh stop "+managedServerName+"; }");
			pw.println("echo \""+ebsl.weblogicAdminPassword+"\" | { admanagedsrvctl.sh start "+managedServerName+"; }");
			pw.close();
			
			SystemUtils.resetLineSeperator();		
		}
	}
	
	public static Result startBounce(String type) throws TaskExecFailException{
		Result res = null;
		if(type.equals(Const.SCRIPT_TYPE_BOUNCE_ALL)){

			CustomTask bounceAllTask = new ExecCommand("export PATH=\""+ebsPath+"\"", "sh "+Const.TEMP_SCRIPTS_DIR_INSTANCE+"/edh_bounceall.sh");
			ssh.connect();

			res = ssh.exec(bounceAllTask);
			return res;
			
		}
		return res;
	}
	
	public static Result startBounceManagedServer(String managedSrvName) throws TaskExecFailException{
		Result res = null;

			CustomTask bounceManagedTask = new ExecCommand("export PATH=\""+ebsPath+"\"", "sh "+Const.TEMP_SCRIPTS_DIR_INSTANCE+"/edh_bounce_"+managedSrvName+".sh");
			ssh.connect();

			res = ssh.exec(bounceManagedTask);
			return res;
	}
	
	
	public static void resetEverything(){
		ssh.disconnect();
		ssh = null;
		ebsl = null;
	}
	

}
