package idh.java.litscan.cmds;

import idh.java.litscan.LitScan;

public interface ICommand {
	boolean verify(String[] args);
	
	void execute(String[] args);
	
	LitScan getMainApplication();
}
