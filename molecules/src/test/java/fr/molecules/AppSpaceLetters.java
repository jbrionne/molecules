package fr.molecules;

import fr.molecules.ISpaceManager;
import fr.molecules.MInstance;
import fr.molecules.Space;
import fr.molecules.SpaceManager;

public class AppSpaceLetters {

	public static void main(String[] args) throws InterruptedException {

		MInstance lettreA = new MInstance("lettreA", SpaceManager.A,
				false);
		MInstance lettreB = new MInstance("lettreB", SpaceManager.B,
				false);
		MInstance lettreC = new MInstance("lettreC", SpaceManager.C,
				false);
		MInstance lettreD = new MInstance("lettreD", SpaceManager.D,
				false);
		MInstance lettreE = new MInstance("lettreE", SpaceManager.E,
				false);
		MInstance lettreF = new MInstance("lettreF", SpaceManager.F,
				false);
		MInstance lettreG = new MInstance("lettreG", SpaceManager.G,
				false);
		MInstance lettreH = new MInstance("lettreH", SpaceManager.H,
				false);
		MInstance lettreI = new MInstance("lettreI", SpaceManager.I,
				false);
		MInstance lettreJ = new MInstance("lettreJ", SpaceManager.J,
				false);
		MInstance lettreK = new MInstance("lettreK", SpaceManager.K,
				false);
		MInstance lettreL = new MInstance("lettreL", SpaceManager.L,
				false);
		MInstance lettreM = new MInstance("lettreM", SpaceManager.M,
				false);
		MInstance lettreN = new MInstance("lettreN", SpaceManager.N,
				false);
		MInstance lettreO = new MInstance("lettreO", SpaceManager.O,
				false);
		MInstance lettreP = new MInstance("lettreP", SpaceManager.P,
				false);
		MInstance lettreQ = new MInstance("lettreQ", SpaceManager.Q,
				false);
		MInstance lettreR = new MInstance("lettreR", SpaceManager.R,
				false);
		MInstance lettreR2 = new MInstance("lettreR2", SpaceManager.R,
				false);
		MInstance lettreS = new MInstance("lettreS", SpaceManager.S,
				false);
		MInstance lettreT = new MInstance("lettreT", SpaceManager.T,
				false);
		MInstance lettreU = new MInstance("lettreU", SpaceManager.U,
				false);
		MInstance lettreV = new MInstance("lettreV", SpaceManager.V,
				false);
		MInstance lettreW = new MInstance("lettreW", SpaceManager.W,
				false);
		MInstance lettreX = new MInstance("lettreX", SpaceManager.X,
				false);
		MInstance lettreY = new MInstance("lettreY", SpaceManager.Y,
				false);
		MInstance lettreZ = new MInstance("lettreZ", SpaceManager.Z,
				false);

		ISpaceManager me = new SpaceManager(false);
		Space esp = me.getSpace();

		me.imagineX(lettreA);
		me.imagineX(lettreR);
		esp.translationRelative(lettreR.getTransformeGroup(), +0.5f, 0, 0);
		me.imagineX(lettreB);
		esp.translationRelative(lettreB.getTransformeGroup(), +1.0f, 0, 0);
		me.imagineX(lettreR2);
		esp.translationRelative(lettreR2.getTransformeGroup(), +1.5f, 0, 0);
		me.imagineX(lettreE);
		esp.translationRelative(lettreE.getTransformeGroup(), +2.0f, 0, 0);

	}

}
