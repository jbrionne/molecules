package fr.molecules;

import java.util.Timer;

import fr.molecules.ISpaceManager;
import fr.molecules.MInstance;
import fr.molecules.SpaceManager;

public class AppSpaceDynamicLine {

	private static Timer myTimer;

	public static void main(String[] args) throws InterruptedException {

		ISpaceManager me = new SpaceManager(true);
		MInstance ligne = new MInstance("ligne1", SpaceManager.TLINE, false);
		me.imagineX(ligne);

		myTimer = new Timer();
		myTimer.schedule(new MyTimerTaskLine(me, ligne), 0, 1);

		Thread.sleep(5000);

		me.moveCamera();

	}

}
