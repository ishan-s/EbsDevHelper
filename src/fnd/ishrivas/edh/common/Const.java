package fnd.ishrivas.edh.common;

public class Const {

	public static final String SCRIPT_TYPE_BOUNCE_ALL = "bounceall";
	public static final String SCRIPT_TYPE_BOUNCE_MANAGED = "bouncemanaged";
	
	public static String SERVER_OACORE = "oacore_server1";
	public static String SERVER_FORMS = "forms_server1";
	public static String SERVER_OAEA = "oaea_server1";
	public static String SERVER_OAFM = "oafm_server1";
	public static String SERVER_FORMSC4WS = "forms-c4ws_server1";
	
	public static String TEMP_SCRIPTS_DIR = System.getProperty("java.io.tmpdir");
	public static String TEMP_SCRIPTS_DIR_INSTANCE = "~/ebs_dev_helper";
	
	public static final String CMD_SOURCE_EBS_RUN = "source /u01/R122_EBS/EBSapps.env run";
	public static final String CMD_SOURCE_EBS_PATCH = "source /u01/R122_EBS/EBSapps.env patch";
	
	public static String ENV_PATH;

	}
