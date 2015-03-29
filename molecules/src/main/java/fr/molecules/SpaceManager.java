package fr.molecules;

import javax.media.j3d.LineArray;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;
import javax.vecmath.Point4f;
import javax.vecmath.Vector3f;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpaceManager implements ISpaceManager {

	private static final Logger LOG = LoggerFactory.getLogger(Space.class);

	public static final String TSPHERE = "TSPHERE";
	public static final String TMUSCLE = "TMUSCLE";
	public static final String TBOX = "TBOX";
	public static final String TCONE = "TCONE";
	public static final String TCYLINDER = "TCYLINDER";
	public static final String TFORM = "TFORM";
	public static final String A = "A";
	public static final String B = "B";
	public static final String C = "C";
	public static final String D = "D";
	public static final String E = "E";
	public static final String F = "F";
	public static final String G = "G";
	public static final String H = "H";
	public static final String I = "I";
	public static final String J = "J";
	public static final String K = "K";
	public static final String L = "L";
	public static final String M = "M";
	public static final String N = "N";
	public static final String O = "O";
	public static final String P = "P";
	public static final String Q = "Q";
	public static final String R = "R";
	public static final String S = "S";
	public static final String T = "T";
	public static final String U = "U";
	public static final String V = "V";
	public static final String W = "W";
	public static final String X = "X";
	public static final String Y = "Y";
	public static final String Z = "Z";

	public static final String TPLANE = "TPLANE";
	public static final String TLINE = "TLINE";
	public static final String TBEZIERLINE = "TBEZIERLINE";

	private Space esp;

	@Override
	public Space getSpace() {
		return esp;
	}

	public SpaceManager(boolean axeGroup) {
		esp = new Space(axeGroup);
		esp.addLightVector(0.0f, 0.0f, -1.0f);
	}

	@Override
	public TransformGroup imagineX(MInstance i) {

		String id = i.getId();
		String type = i.getType();
		boolean withCollision = i.isWithCollision();

		if (i.getTransformeGroup() != null) {
			return i.getTransformeGroup();
		}

		TransformGroup t = null;
		if (type.equals(TSPHERE)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addSphereToTransform(id, t, 0.1f, withCollision);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TCONE)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addConeToTransform(id, t, 0.1f, 0.1f,
					withCollision);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TCYLINDER)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addCylindreToTransform(id, t, 0.1f, 0.1f,
					withCollision);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TBOX)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addBoxToTransform(id, t, 0.1f, 0.1f, 0.1f,
					withCollision);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TLINE)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addLineToTransform(id, t, -1.0f, 0, 0, +1.0f, 0,
					0);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TBEZIERLINE)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addLineBezierToTransform(id, t, -0.8f, -0.6f,
					-0.2f, 0.2f, 0.2f, 0.3f, 0.8f, -0.5f);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TPLANE)) {
			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.addPlaneToTransform(id, t, -0.5f, -0.5f, 0f,
					0.5f, -0.5f, 0f, 0.5f, 0.5f, 0f, -0.5f, 0.5f, 0f);
			i.setTransformeGroup(t);
			i.setO(r.getO());
			return t;
		}
		if (type.equals(TMUSCLE)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tCheval = r.getTransformeGroup();

			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			BranchAndTransform btSphere = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			TransformGroup tSphere = btSphere.getTrG();
			esp.addSphereToTransform("tete", tSphere, 0.05f, withCollision);

			BranchAndTransform btBox = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			TransformGroup tBox = btBox.getTrG();
			esp.addBoxToTransform("corps", tBox, 0.01f, 0.5f, 0.01f,
					withCollision);

			BranchAndTransform btSphere2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			TransformGroup tSphere2 = btSphere2.getTrG();
			esp.addSphereToTransform("", tSphere2, 0.05f, withCollision);

			tCheval.addChild(btSphere.getBrG());
			tCheval.addChild(btBox.getBrG());
			tCheval.addChild(btSphere2.getBrG());

			esp.translationRelative(btSphere.getTrG(), 0f, 0.5f, 0f);
			esp.translationRelative(btSphere2.getTrG(), 0f, -0.5f, 0f);

			return t;
		}
		if (type.equals(TFORM)) {

			t = esp.addToRootBranchGroupAndTransform();

			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tform = r.getTransformeGroup();

			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			BranchAndTransform btSphere = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			BranchAndTransform btBox = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			tform.addChild(btSphere.getBrG());
			tform.addChild(btBox.getBrG());

			TransformGroup tSphere = btSphere.getTrG();
			esp.addSphereToTransform("head", tSphere, 0.05f,
					true);
			esp.translationRelative(btSphere.getTrG(), -0.1f, 0.25f, 0);

			TransformGroup tBox = btBox.getTrG();

			esp.addSphereToTransform("corps", tBox, 0.1f, true);

			esp.rotationRelativeDegree(tform, 0, 1.0f, 0.0f, 30);
			esp.rotationRelativeDegree(tform, 0, 0.0f, 1.0f, 30);
			return t;
		}
		if (type.equals(A)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(0f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, -0.1f, 0f);
			Point3f p4 = new Point3f(-0.05f, 0f, 0f);
			Point3f p5 = new Point3f(0.05f, 0f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(B)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(-0.1f, 0.02f, 0f);

			Point3f p4 = new Point3f(+0.05f, 0.1f, 0f);
			Point3f p5 = new Point3f(0.05f, 0.02f, 0f);
			Point3f p6 = new Point3f(0.12f, 0.02f, 0f);
			Point3f p7 = new Point3f(0.12f, -0.12f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p2.getX(),
					p2.getY(), p4.getX(), p4.getY(), p5.getX(), p5.getY(),
					p3.getX(), p3.getY());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine3.getTrG(), p3.getX(),
					p3.getY(), p6.getX(), p6.getY(), p7.getX(), p7.getY(),
					p1.getX(), p1.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(C)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.05f, -0.1f, 0f);

			Point3f p3 = new Point3f(-0.15f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.15f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p1.getX(),
					p1.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(),
					p2.getX(), p2.getY());

			tMain.addChild(btLine1.getBrG());

			return t;
		}
		if (type.equals(D)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, -0.1f, 0f);

			Point3f p3 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.1f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p1.getX(),
					p1.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(),
					p2.getX(), p2.getY());

			tMain.addChild(btLine1.getBrG());

			return t;
		}
		if (type.equals(E)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.06f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.1f, 0f, 0f);
			Point3f p5 = new Point3f(0.06f, 0f, 0f);
			Point3f p6 = new Point3f(0.06f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p5.getX(), p5.getY(), p5.getZ());

			BranchAndTransform btLine4 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine4.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p6.getX(), p6.getY(), p6.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());
			tMain.addChild(btLine4.getBrG());

			return t;
		}
		if (type.equals(F)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.06f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.1f, 0f, 0f);
			Point3f p5 = new Point3f(0.06f, 0f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(G)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.05f, -0.1f, 0f);

			Point3f p3 = new Point3f(-0.15f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.15f, -0.1f, 0f);

			Point3f p5 = new Point3f(0.03f, -0.1f, 0f);
			Point3f p6 = new Point3f(0.03f, -0.02f, 0f);
			Point3f p7 = new Point3f(-0.02f, -0.02f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p1.getX(),
					p1.getY(), p3.getX(), p3.getY(), p4.getX(), p4.getY(),
					p2.getX(), p2.getY());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p5.getX(), p5.getY(),
					p5.getZ(), p6.getX(), p6.getY(), p6.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p6.getX(), p6.getY(),
					p6.getZ(), p7.getX(), p7.getY(), p7.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(H)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.08f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.08f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.08f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.08f, 0f, 0f);
			Point3f p5 = new Point3f(0.08f, 0f, 0f);
			Point3f p6 = new Point3f(0.08f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p6.getX(), p6.getY(), p6.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(I)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.05f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.05f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.05f, -0.1f, 0f);
			Point3f p5 = new Point3f(0.f, 0.1f, 0f);
			Point3f p6 = new Point3f(0f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p5.getX(), p5.getY(),
					p5.getZ(), p6.getX(), p6.getY(), p6.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(J)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.05f, -0.02f, 0f);
			Point3f p2 = new Point3f(-0.05f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.05f, -0.02f, 0f);

			Point3f p5 = new Point3f(-0.05f, -0.13f, 0f);
			Point3f p6 = new Point3f(0.05f, -0.13f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine3.getTrG(), p4.getX(),
					p4.getY(), p6.getX(), p6.getY(), p5.getX(), p5.getY(),
					p1.getX(), p1.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(K)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.06f, 0.1f, 0f);
			Point3f p4 = new Point3f(-0.1f, 0f, 0f);
			Point3f p5 = new Point3f(0.06f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(L)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.06f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p3.getX(), p3.getY(), p3.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;
		}
		if (type.equals(M)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.08f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.08f, 0.1f, 0f);
			Point3f p4 = new Point3f(0f, 0.02f, 0f);
			Point3f p5 = new Point3f(0.1f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine4 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine4.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());
			tMain.addChild(btLine4.getBrG());

			return t;

		}
		if (type.equals(N)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.08f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.08f, -0.1f, 0f);
			Point3f p4 = new Point3f(0.1f, 0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p4.getX(), p4.getY(), p4.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;

		}
		if (type.equals(O)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.1f, -0.1f, 0f);
			Point3f p5 = new Point3f(0f, -0.1f, 0f);
			Point3f p6 = new Point3f(0f, 0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p5.getX(),
					p5.getY(), p4.getX(), p4.getY(), p3.getX(), p3.getY(),
					p6.getX(), p6.getY());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p5.getX(),
					p5.getY(), p2.getX(), p2.getY(), p1.getX(), p1.getY(),
					p6.getX(), p6.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;

		}
		if (type.equals(P)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(-0.1f, 0.02f, 0f);

			Point3f p4 = new Point3f(+0.05f, 0.1f, 0f);
			Point3f p5 = new Point3f(0.05f, 0.02f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p2.getX(),
					p2.getY(), p4.getX(), p4.getY(), p5.getX(), p5.getY(),
					p3.getX(), p3.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;

		}
		if (type.equals(Q)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.1f, -0.1f, 0f);
			Point3f p5 = new Point3f(0f, -0.1f, 0f);
			Point3f p6 = new Point3f(0f, 0.1f, 0f);

			Point3f p7 = new Point3f(0.05f, -0.05f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p5.getX(),
					p5.getY(), p4.getX(), p4.getY(), p3.getX(), p3.getY(),
					p6.getX(), p6.getY());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p5.getX(),
					p5.getY(), p2.getX(), p2.getY(), p1.getX(), p1.getY(),
					p6.getX(), p6.getY());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p7.getX(), p7.getY(),
					p7.getZ(), p4.getX(), p4.getY(), p4.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;

		}
		if (type.equals(R)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, -0.1f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);

			Point3f p3 = new Point3f(-0.1f, 0.0f, 0f);

			Point3f p4 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p5 = new Point3f(0.05f, 0.0f, 0f);

			Point3f p6 = new Point3f(0.02f, -0.1f, 0f);
			Point3f p7 = new Point3f(-0.06f, 0f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p2.getX(),
					p2.getY(), p4.getX(), p4.getY(), p5.getX(), p5.getY(),
					p3.getX(), p3.getY());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p7.getX(), p7.getY(),
					p7.getZ(), p6.getX(), p6.getY(), p6.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;

		}
		if (type.equals(S)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(0f, 0f, 0f);

			Point3f p3 = new Point3f(-0.1f, -0.1f, 0f);

			Point3f p4 = new Point3f(-0.12f, 0.12f, 0f);
			Point3f p5 = new Point3f(-0.1f, 0.0f, 0f);

			Point3f p6 = new Point3f(0.1f, 0f, 0f);
			Point3f p7 = new Point3f(0.12f, -0.12f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p1.getX(),
					p1.getY(), p4.getX(), p4.getY(), p5.getX(), p5.getY(),
					p2.getX(), p2.getY());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine2.getTrG(), p2.getX(),
					p2.getY(), p6.getX(), p6.getY(), p7.getX(), p7.getY(),
					p3.getX(), p3.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;

		}
		if (type.equals(T)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p2 = new Point3f(-0.05f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.05f, 0.1f, 0f);
			Point3f p5 = new Point3f(0.f, 0.1f, 0f);
			Point3f p6 = new Point3f(0f, -0.1f, 0f);

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p5.getX(), p5.getY(),
					p5.getZ(), p6.getX(), p6.getY(), p6.getZ());

			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(U)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0f, 0f);
			Point3f p2 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p4 = new Point3f(0.1f, 0f, 0f);

			Point3f p5 = new Point3f(-0.1f, -0.12f, 0f);
			Point3f p6 = new Point3f(0.1f, -0.12f, 0f);

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineBezierToTransform("", btLine1.getTrG(), p1.getX(),
					p1.getY(), p5.getX(), p5.getY(), p6.getX(), p6.getY(),
					p4.getX(), p4.getY());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(V)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p2.getX(), p2.getY(), p2.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;
		}
		if (type.equals(W)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(-0.08f, -0.1f, 0f);
			Point3f p3 = new Point3f(0.08f, -0.1f, 0f);
			Point3f p4 = new Point3f(0f, -0.02f, 0f);
			Point3f p5 = new Point3f(0.1f, 0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine4 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine4.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p5.getX(), p5.getY(), p5.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());
			tMain.addChild(btLine4.getBrG());

			return t;

		}
		if (type.equals(X)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, -0.1f, 0f);
			Point3f p4 = new Point3f(-0.1f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p4.getX(), p4.getY(), p4.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());

			return t;
		}
		if (type.equals(Y)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0f, 0f, 0f);
			Point3f p4 = new Point3f(0f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p3.getX(), p3.getY(), p3.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p3.getX(), p3.getY(),
					p3.getZ(), p4.getX(), p4.getY(), p4.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());

			return t;
		}
		if (type.equals(Z)) {

			t = esp.addToRootBranchGroupAndTransform();
			InfoObject r = esp.getMyBranchGroupAndTransform(id);
			TransformGroup tMain = r.getTransformeGroup();
			t.addChild(r.getBrAndtr().getBrG());
			i.setTransformeGroup(t);
			i.setO(r.getO());

			Point3f p1 = new Point3f(-0.1f, 0.1f, 0f);
			Point3f p2 = new Point3f(0.1f, 0.1f, 0f);
			Point3f p3 = new Point3f(0.1f, -0.1f, 0f);
			Point3f p4 = new Point3f(-0.1f, -0.1f, 0f);

			BranchAndTransform btLine1 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine1.getTrG(), p1.getX(), p1.getY(),
					p1.getZ(), p2.getX(), p2.getY(), p2.getZ());

			BranchAndTransform btLine2 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine2.getTrG(), p2.getX(), p2.getY(),
					p2.getZ(), p4.getX(), p4.getY(), p4.getZ());

			BranchAndTransform btLine3 = esp.getBranchGroupAndTransform(esp
					.isAxeGroup());
			esp.addLineToTransform("", btLine3.getTrG(), p4.getX(), p4.getY(),
					p4.getZ(), p3.getX(), p3.getY(), p3.getZ());

			tMain.addChild(btLine1.getBrG());
			tMain.addChild(btLine2.getBrG());
			tMain.addChild(btLine3.getBrG());
			return t;
		}

		return null;
	}

	private void imagineX_A_Y(MInstance box, MInstance i, float x, float y,
			float z) {

		String id = i.getId();

		IMObj o = null;
		if (i.getO() != null) {
			o = i.getO();
		} else {
			o = esp.getObjectById(id);
		}

		Transform3D trobj = new Transform3D();
		o.getLocalToVworld(trobj);
		Point3f point = new Point3f();
		trobj.transform(point);
		Vector3f vector = new Vector3f(point.getX() + x, point.getY() + y,
				point.getZ() + z);

		TransformGroup t = imagineX(box);
		Transform3D trobj2 = new Transform3D();
		t.getLocalToVworld(trobj2);
		trobj2.invert();
		Transform3D mytr = new Transform3D();
		mytr.setTranslation(vector);
		trobj2.mul(mytr);
		t.setTransform(trobj2);

	}

	private void showPosition(Transform3D trobj2) {
		Point3f point1 = new Point3f();
		trobj2.transform(point1);
		LOG.info("" + point1.getX());
		LOG.info("" + point1.getY());
		LOG.info("" + point1.getZ());
	}

	private void imagineX_ALa_Y(MInstance box, MInstance i, float x, float y,
			float z) {

		String id = i.getId();

		TransformGroup t = imagineX(box);

		IMObj o = null;
		if (i.getO() != null) {
			o = i.getO();
		} else {
			o = esp.getObjectById(id);
		}

		Transform3D tr = new Transform3D();
		o.getLocalToVworld(tr);

		Transform3D trobj2 = new Transform3D();
		t.getLocalToVworld(trobj2);
		trobj2.invert();

		trobj2.mul(tr);

		t.setTransform(trobj2);

		esp.translationRelative(t, x, y, z);
	}

	private boolean q_X_A_DeY(float x1, float x2) {
		if (x1 < x2) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void imagineXLeftY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, -0.5f, 0, 0);
	}

	@Override
	public void imagineXRightY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, 0.5f, 0, 0);
	}

	@Override
	public void imagineXTopY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, 0, 0.5f, 0);
	}

	@Override
	public void imagineXBottomY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, 0, -0.5f, 0);
	}

	@Override
	public void imagineXFrontY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, 0, 0, 0.5f);
	}

	@Override
	public void imagineXBackY(MInstance box, MInstance i) {
		imagineX_A_Y(box, i, 0, 0, -0.5f);
	}

	// ///////////////////////////////////////////////////////////////

	@Override
	public void imagineXRigthOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, 0.5f, 0, 0);
	}

	@Override
	public void imagineXLeftOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, -0.5f, 0, 0);
	}

	@Override
	public void imagineXTopOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, 0, 0.5f, 0);
	}

	@Override
	public void imagineXBottomOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, 0, -0.5f, 0);
	}

	@Override
	public void imagineXFrontOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, 0, 0, -0.5f);
	}

	@Override
	public void imagineXBackOfY(MInstance box, MInstance i) {
		imagineX_ALa_Y(box, i, 0, 0, 0.5f);
	}

	@Override
	public boolean isXLeftY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getX(), point2.getX());
	}

	@Override
	public boolean isXLeftOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getX(), point2.getX());
	}

	@Override
	public boolean isXRightY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getX(), point1.getX());
	}

	@Override
	public boolean isXRightOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getX(), point1.getX());
	}

	@Override
	public boolean isXBottomY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getY(), point2.getY());
	}

	@Override
	public boolean isXBottomOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getY(), point2.getY());
	}

	@Override
	public boolean isXTopY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getY(), point1.getY());
	}

	@Override
	public boolean isXTopOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getY(), point1.getY());
	}

	@Override
	public boolean isXFrontY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getZ(), point2.getZ());
	}

	@Override
	public boolean isXFrontOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point1.getZ(), point2.getZ());
	}

	@Override
	public boolean isXBackY(MInstance b, MInstance id) {
		Point3f point1 = esp.getAbsoluteCoordinateByObjetId(id);
		Point3f point2 = esp.getAbsoluteCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getY(), point1.getY());
	}

	@Override
	public boolean isXBackOfY(MInstance b, MInstance id) {
		Point3f point1 = esp.getRelativeToCamCoordinateByObjetId(id);
		Point3f point2 = esp.getRelativeToCamCoordinateByObjetId(b);
		return q_X_A_DeY(point2.getZ(), point1.getZ());
	}

	@Override
	public void move(MInstance i, final float vx1, final float vy1,
			final float vz1, final float vx2, final float vy2, final float vz2) {

		IMObj obj = null;
		if (i.getO() != null) {
			obj = i.getO();
		} else {
			obj = esp.getObjectById(i.getId());
		}

		Point3f[] plaPts = new Point3f[2];
		plaPts[0] = new Point3f(vx1, vy1, vz1);
		plaPts[1] = new Point3f(vx2, vy2, vz2);
		LineArray pla = (LineArray) obj.getGeometry();
		pla.setCoordinates(0, plaPts);		

	}

	@Override
	public void move(MInstance i, Point3f p1, Point3f p2) {
		IMObj obj = null;
		if (i.getO() != null) {
			obj = i.getO();
		} else {
			obj = esp.getObjectById(i.getId());
		}
		Point3f[] plaPts = new Point3f[2];
		plaPts[0] = p1;
		plaPts[1] = p2;
		LineArray pla = (LineArray) obj.getGeometry();
		pla.setCoordinates(0, plaPts);

	}

	@Override
	public void moveCamera(float eyex, float eyey, float eyez, float centerx,
			float centery, float centerz, float upx, float upy, float upz) {
		esp.moveCamera(eyex, eyey, eyez, centerx, centery, centerz, upx, upy,
				upz);
	}

	@Override
	public void moveCamera() {
		esp.moveCamera(0f, 0f, 2.0f, 0f, 0f, 0f, 0f, 1.0f, 0f);
	}

	@Override
	public void moveCameraTop() {
		esp.moveCamera(0f, 1.3f, 5.0f, 0f, 0f, 0f, 0f, 1.0f, 0f);
	}

	@Override
	public void moveBezier(MInstance i, float p1x, float p1y, float p2x,
			float p2y, float p3x, float p3y, float p4x, float p4y) {
		IMObj obj = null;
		if (i.getO() != null) {
			obj = i.getO();
		} else {
			obj = esp.getObjectById(i.getId());
		}

		Point4f[] ctrl = new Point4f[4];
		ctrl[0] = new Point4f(p1x, p1y, 0, 1);
		ctrl[1] = new Point4f(p2x, p2y, 0, 1);
		ctrl[2] = new Point4f(p3x, p3y, 0, 1);
		ctrl[3] = new Point4f(p4x, p4y, 0, 1);

		int nt = 100;
		Point3f[] curv = esp.getCurve(ctrl, nt);
		LineStripArray pla = (LineStripArray) obj.getGeometry();
		pla.setCoordinates(0, curv);

	}

	@Override
	public void rotateAbsX(MInstance b, float xz, float yz, float xy,
			double angleDeg) {
		esp.rotationAbsCoordWorld(b.getTransformeGroup(), xz, yz, xy, angleDeg);
	}

	@Override
	public void moveAbsX(MInstance b, float x, float y, float z) {
		esp.translationAbsCoordWorld(b.getTransformeGroup(), x, y, z);
	}

	@Override
	public void scaleAbsX(MInstance b, float x, float y, float z) {
		esp.echelleAbsCoordWorld(b.getTransformeGroup(), x, y, z);
	}

	@Override
	public void rotateRelativeX(MInstance b, float x, float y, float z,
			double angleDeg) {
		esp.rotationRelativeDegreeCoordWorld(b.getTransformeGroup(), x, y, z,
				angleDeg);
	}

	@Override
	public void moveRelativeX(MInstance b, float x, float y, float z) {
		esp.translationRelativeCoordWorld(b.getTransformeGroup(), x, y, z);
	}

	@Override
	public void scaleRelativeX(MInstance b, float x, float y, float z) {
		esp.echelleRelativeCoordWorld(b.getTransformeGroup(), x, y, z);
	}

}
