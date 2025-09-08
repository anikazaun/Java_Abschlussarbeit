package idh.java.litscan.cmds;

import idh.java.litscan.LitScan;

public abstract class AbstractCommand implements ICommand {

	LitScan mainApplication;
	
	public AbstractCommand(LitScan mainApp) {
		this.mainApplication = mainApp;
	}

	public LitScan getMainApplication() {
		return this.mainApplication;
	}
	
	protected void log(String s) {
		System.out.println(s.replaceAll("\\n", "\\\\n"));
	};

}
