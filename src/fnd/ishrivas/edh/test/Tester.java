package fnd.ishrivas.edh.test;

import fnd.ishrivas.edh.common.Const;
import fnd.ishrivas.edh.main.EBizInstance122x;
import fnd.ishrivas.edh.main.SSHController;

public class Tester {

	public static void main(String[] args) {
		EBizInstance122x e = new EBizInstance122x("rws3270197.us.oracle.com", "oracle", "oracle", "apps", "apps", "weblogic", "welcome1", "sso1224d");
		
		try {
			SSHController.setup(e);
			SSHController.startBounce(Const.SCRIPT_TYPE_BOUNCE_ALL);
		} catch (Exception e1) {
			e1.printStackTrace();
		};
		
	}

}
