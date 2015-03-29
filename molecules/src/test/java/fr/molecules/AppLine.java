package fr.molecules;

import javax.vecmath.Point3f;

import fr.molecules.ISpaceManager;
import fr.molecules.MInstance;
import fr.molecules.SpaceManager;

public class AppLine {

	public static void main(String[] args) throws InterruptedException {
		ISpaceManager me = new SpaceManager(true);
		MInstance ligne1 = new MInstance("ligne1", SpaceManager.TLINE, false);
		MInstance ligne2 = new MInstance("ligne2", SpaceManager.TLINE, false);
		MInstance ligne3 = new MInstance("ligne3", SpaceManager.TLINE, false);
		MInstance ligne4 = new MInstance("ligne4", SpaceManager.TLINE, false);

		MInstance ligne5 = new MInstance("ligne5", SpaceManager.TLINE, false);
		MInstance ligne6 = new MInstance("ligne6", SpaceManager.TLINE, false);
		MInstance ligne7 = new MInstance("ligne7", SpaceManager.TLINE, false);
		MInstance ligne8 = new MInstance("ligne8", SpaceManager.TLINE, false);

		MInstance ligne9 = new MInstance("ligne9", SpaceManager.TLINE, false);
		MInstance ligne10 = new MInstance("ligne10", SpaceManager.TLINE, false);
		MInstance ligne11 = new MInstance("ligne11", SpaceManager.TLINE, false);
		MInstance ligne12 = new MInstance("ligne12", SpaceManager.TLINE, false);

		me.imagineX(ligne1);
		me.imagineX(ligne2);
		me.imagineX(ligne3);
		me.imagineX(ligne4);
		me.imagineX(ligne5);
		me.imagineX(ligne6);
		me.imagineX(ligne7);
		me.imagineX(ligne8);
		me.imagineX(ligne9);
		me.imagineX(ligne10);
		me.imagineX(ligne11);
		me.imagineX(ligne12);

		Point3f p0 = new Point3f(0, 0, 0);
		Point3f p1 = new Point3f(1, 0, 0);
		Point3f p2 = new Point3f(0, 1, 0);
		Point3f p3 = new Point3f(1, 1, 0);
		Point3f p4 = new Point3f(0, 1, 1);
		Point3f p5 = new Point3f(1, 1, 1);
		Point3f p6 = new Point3f(1, 0, 1);
		Point3f p7 = new Point3f(0, 0, 1);

		me.move(ligne1, p0, p1);
		me.move(ligne2, p0, p2);
		me.move(ligne3, p1, p3);
		me.move(ligne4, p2, p3);
		me.move(ligne5, p2, p4);
		me.move(ligne6, p3, p5);
		me.move(ligne7, p1, p6);
		me.move(ligne8, p0, p7);
		me.move(ligne9, p7, p4);
		me.move(ligne10, p4, p5);
		me.move(ligne11, p5, p6);
		me.move(ligne12, p7, p4);

		Thread.sleep(5000);

		me.moveCameraTop();

	}

}
