package fr.molecules;

import java.util.LinkedList;
import java.util.List;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Node;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Point4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

public class Space {

	private static final Logger LOG = LoggerFactory.getLogger(Space.class);

	
	private static List<IMObj> lstObj = new LinkedList<IMObj>();

	// X coordinate is positive to the right of the origin, negative to the
	// left.
	// Y coordinate is positive above the origin, negative below.
	// Z coordinate is negative INTO the screen, and positive OUT to the user.

	private SimpleUniverse universe;
	private BranchGroup group;
	private TransformGroup mere;
	private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0,
			5000.0), 10000.0);
	private TransformGroup cam;

	private Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
	
	private boolean axeGroup = true;

	public Space(boolean axeGroup) {
		this.axeGroup = axeGroup;
		universe = new SimpleUniverse();
		BranchAndTransform bt = getBranchGroupAndTransform(false);
		group = bt.getBrG();
		universe.getViewingPlatform().setNominalViewingTransform();
		View view = universe.getViewer().getView();
		view.setBackClipDistance(10000);
		cam = universe.getViewingPlatform().getViewPlatformTransform();
		Point3d eye = new Point3d(0.0, 0.0, 5.0);
		Point3d center = new Point3d(0.0, 0.0, 0.0);
		Vector3d up = new Vector3d(0.0, 1.0, 0.0);
		positionCamera(eye, center, up);
		mere = bt.getTrG();
		// add objects to Universe
		mere.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		mere.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		mere.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		mere.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mere.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		group.compile();
		universe.addBranchGraph(group);
	}

	public boolean isAxeGroup() {
		return axeGroup;
	}

	public BranchAndTransform getBranchGroupAndTransform(boolean axeGroup) {
		BranchGroup nbg = new BranchGroup();
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		nbg.setCapability(BranchGroup.ALLOW_DETACH);
		nbg.setCapability(BranchGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		nbg.setBoundsAutoCompute(false);
		TransformGroup tgr = null;
		if (axeGroup) {
			tgr = Space.getAxesGroup();
		} else {
			tgr = new TransformGroup();
		}
		tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		tgr.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		tgr.setBoundsAutoCompute(false);
		nbg.addChild(tgr);
		BranchAndTransform brAndtr = new BranchAndTransform(nbg, tgr);
		return brAndtr;
	}

	public InfoObject getMyBranchGroupAndTransform(String id) {

		MBranchGroup nbg = new MBranchGroup(id);
		addObjet(nbg);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		nbg.setCapability(BranchGroup.ALLOW_DETACH);
		nbg.setCapability(BranchGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		nbg.setBoundsAutoCompute(false);
		TransformGroup tgr = null;
		if (axeGroup) {
			tgr = Space.getAxesGroup();
		} else {
			tgr = new TransformGroup();
		}
		tgr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		tgr.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		tgr.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		tgr.setBoundsAutoCompute(false);
		nbg.addChild(tgr);
		BranchAndTransform brAndtr = new BranchAndTransform(nbg, tgr);
		InfoObject r = new InfoObject();
		r.setO(nbg);
		r.setBrAndtr(brAndtr);
		r.setTransformeGroup(tgr);
		return r;
	}

	public TransformGroup addToRootBranchGroupAndTransform() {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		BranchGroup nbg = bt.getBrG();
		TransformGroup objTrans = bt.getTrG();
		mere.addChild(nbg);
		return objTrans;
	}

	public TransformGroup addToRootBranchGroupAndTransform(
			TransformGroup objTrans) {
		BranchGroup nbg = new BranchGroup();
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		nbg.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		nbg.setCapability(BranchGroup.ALLOW_DETACH);
		nbg.setCapability(BranchGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		nbg.setBoundsAutoCompute(false);
		nbg.addChild(objTrans);
		mere.addChild(nbg);
		return objTrans;
	}

	public void addLightVector(float args0, float arg1, float arg2) {
		BranchAndTransform bt = getBranchGroupAndTransform(false);
		TransformGroup nbg = bt.getTrG();
		Color3f light1Color = new Color3f(1.8f, 0.1f, 0.1f);
		Vector3f light1Direction = new Vector3f(args0, arg1, arg2);
		DirectionalLight light1 = new DirectionalLight(light1Color,
				light1Direction);
		light1.setInfluencingBounds(bounds);
		nbg.addChild(light1);
		group.addChild(bt.getBrG());
	}

	public void addLightAmbient() {
		BranchAndTransform bt = getBranchGroupAndTransform(false);
		TransformGroup nbg = bt.getTrG();
		Color3f ambientColor = new Color3f(1.8f, 0.1f, 0.1f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		nbg.addChild(ambientLightNode);
		group.addChild(bt.getBrG());
	}

	private void addObjet(IMObj o) {
		if (o.getId() != null && !o.getId().equals("")) {
			lstObj.add(o);
		}
	}

	public InfoObject addConeToTransform(String id,
			TransformGroup objTrans, float arg1, float arg2,
			boolean withcollision) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		MCone cone = new MCone(id, arg1, arg2);
		nbg.addChild(cone);
		if (withcollision) {
			makeCollision(cone, nbg);
		}
		cone.setUserData(new UserData(id));

		objTrans.addChild(bt.getBrG());
		addObjet(cone);
		InfoObject r = new InfoObject();
		r.setO(cone);
		r.setTransformeGroup(nbg);
		return r;
	}

	public InfoObject addLineToTransform(String id,
			TransformGroup objTrans, float vx1, float vy1, float vz1,
			float vx2, float vy2, float vz2) {

		Appearance app = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(white,
				ColoringAttributes.SHADE_FLAT);
		app.setColoringAttributes(ca);

		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		// Plain line
		Point3f[] plaPts = new Point3f[2];
		plaPts[0] = new Point3f(vx1, vy1, vz1);
		plaPts[1] = new Point3f(vx2, vy2, vz2);
		LineArray pla = new LineArray(2, LineArray.COORDINATES);
		pla.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		pla.setCapability(GeometryArray.ALLOW_COORDINATE_WRITE);
		pla.setCoordinates(0, plaPts);
		MShape3D line = new MShape3D(id, pla, app);		
		nbg.addChild(line);

		line.setUserData(new UserData(id));

		objTrans.addChild(bt.getBrG());
		addObjet(line);
		InfoObject r = new InfoObject();
		r.setO(line);
		r.setTransformeGroup(nbg);
		return r;
	}

	public InfoObject addPlaneToTransform(String id,
			TransformGroup objTrans, float x1, float y1, float z1, float x2,
			float y2, float z2, float x3, float y3, float z3, float x4,
			float y4, float z4) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		PolygonAttributes p = new PolygonAttributes();
		Appearance planeAppearance = new Appearance();
		planeAppearance.setPolygonAttributes(p);
		Color3f planeColor = new Color3f(1.0f, 1.0f, 1.0f);
		ColoringAttributes planeCA = new ColoringAttributes(planeColor, 1);
		planeAppearance.setColoringAttributes(planeCA);
		QuadArray plane = new QuadArray(4, QuadArray.COORDINATES);
		plane.setCoordinate(0, new Point3f(x1, y1, z1));
		plane.setCoordinate(1, new Point3f(x2, y2, z2));
		plane.setCoordinate(2, new Point3f(x3, y3, z3));
		plane.setCoordinate(3, new Point3f(x4, y4, z4));
		MShape3D myPlane = new MShape3D(id, plane, planeAppearance);
		nbg.addChild(myPlane);

		myPlane.setUserData(new UserData(id));

		objTrans.addChild(bt.getBrG());
		addObjet(myPlane);
		InfoObject r = new InfoObject();
		r.setO(myPlane);
		r.setTransformeGroup(nbg);
		return r;
	}

	public InfoObject addCylindreToTransform(String id,
			TransformGroup objTrans, float arg1, float arg2,
			boolean withcollision) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		MCylinder cylinder = new MCylinder(id, arg1, arg2);
		nbg.addChild(cylinder);

		cylinder.setUserData(new UserData(id));

		if (withcollision) {
			makeCollision(cylinder, nbg);
		}

		objTrans.addChild(bt.getBrG());
		addObjet(cylinder);
		InfoObject r = new InfoObject();
		r.setO(cylinder);
		r.setTransformeGroup(nbg);
		return r;
	}

	public InfoObject addSphereToTransform(String id,
			TransformGroup objTrans, float arg, boolean withcollision) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		MSphere sphere = new MSphere(id, arg);

		sphere.setUserData(new UserData(id));

		if (withcollision) {
			makeCollisionSphere(id, sphere, nbg, arg);
		}
		nbg.addChild(sphere);
		objTrans.addChild(bt.getBrG());
		addObjet(sphere);
		InfoObject r = new InfoObject();
		r.setO(sphere);
		r.setTransformeGroup(nbg);
		r.setBrAndtr(bt);
		return r;
	}

	public void makeCollisionSphere(String id, MSphere sphere,
			TransformGroup nbg, float arg) {
		sphere.getShape().setBoundsAutoCompute(false);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				arg);

		sphere.getShape().setUserData(new UserData("bounds " + id));
		CollisionDetector cd = new CollisionDetector(sphere.getShape(),
				bounds);

		cd.setSchedulingBounds(bounds);
		LOG.info("bounds " + bounds);

		nbg.addChild(cd);
		nbg.setCollidable(true);
		sphere.setBounds(bounds);
		sphere.setCollisionBounds(bounds);
	}

	public void makeCollisionBox(MBox box, TransformGroup nbg, float arg) {
		box.setBoundsAutoCompute(false);

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
				arg);

		CollisionDetector cd = new CollisionDetector(box, bounds);

		LOG.info("bounds " + bounds);
		nbg.addChild(cd);
		nbg.setCollidable(true);
		box.setBounds(bounds);
		box.setCollisionBounds(bounds);
	}

	public void makeCollision(Node node, TransformGroup nbg) {
		CollisionDetector cd = new CollisionDetector(node,
				node.getBounds());

		LOG.info("node.getBounds() " + node.getBounds());
		nbg.setCollidable(true);
		nbg.addChild(cd);
	}

	public InfoObject addBoxToTransform(String id,
			TransformGroup objTrans, float largeur, float hauteur,
			float profondeur, boolean withcollision) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();
		MBox box = new MBox(id, largeur, hauteur, profondeur);

		box.setUserData(new UserData(id));

		if (withcollision) {
			makeCollisionBox(box, nbg, largeur);
		}
		nbg.addChild(box);
		objTrans.addChild(bt.getBrG());
		addObjet(box);
		InfoObject r = new InfoObject();
		r.setO(box);
		r.setTransformeGroup(nbg);
		r.setBrAndtr(bt);
		return r;
	}

	public InfoObject addLineBezierToTransform(String id,
			TransformGroup objTrans, float p1x, float p1y, float p2x,
			float p2y, float p3x, float p3y, float p4x, float p4y) {
		BranchAndTransform bt = getBranchGroupAndTransform(axeGroup);
		TransformGroup nbg = bt.getTrG();

		Appearance app = new Appearance();
		ColoringAttributes ca = new ColoringAttributes(white,
				ColoringAttributes.SHADE_FLAT);
		app.setColoringAttributes(ca);

		Point4f[] ctrl = new Point4f[4];
		ctrl[0] = new Point4f(p1x, p1y, 0, 1);
		ctrl[1] = new Point4f(p2x, p2y, 0, 1);
		ctrl[2] = new Point4f(p3x, p3y, 0, 1);
		ctrl[3] = new Point4f(p4x, p4y, 0, 1);

		MShape3D line = new MShape3D(id, mybezier(ctrl).getGeometry(), app);
		line.setUserData(new UserData(id));

		nbg.addChild(line);

		objTrans.addChild(bt.getBrG());
		addObjet(line);
		InfoObject r = new InfoObject();
		r.setO(line);
		r.setTransformeGroup(nbg);
		return r;
	}

	public IMObj getObjectById(String id) {
		for (IMObj obj : lstObj) {
			if (id.equals(obj.getId())) {
				return obj;
			}
		}
		return null;
	}

	public Point3f getAbsoluteCoordinateByObjetId(MInstance i) {
		Transform3D tr = new Transform3D();
		IMObj obj = null;
		if (i.getO() != null) {
			obj = i.getO();
		} else {
			obj = this.getObjectById(i.getId());
		}
		obj.getLocalToVworld(tr);
		Point3f point = new Point3f();
		tr.transform(point);
		return point;
	}

	public Point3f getAbsoluteCoordinateView() {
		Node n = cam.getChild(0);
		Transform3D tr = new Transform3D();
		n.getLocalToVworld(tr);
		Point3f point = new Point3f();
		tr.transform(point);
		return point;
	}

	public Point3f getRelativeToCamCoordinateByObjetId(MInstance i) {

		Node n = cam.getChild(0);
		Transform3D trcam2 = new Transform3D();
		n.getLocalToVworld(trcam2);

		Point3f pointCam = new Point3f();
		trcam2.transform(pointCam);

		Transform3D trobj = new Transform3D();
		IMObj obj = null;
		if (i.getO() != null) {
			obj = i.getO();
		} else {
			obj = this.getObjectById(i.getId());
		}
		obj.getLocalToVworld(trobj);

		Point3f pointobj = new Point3f();
		trobj.transform(pointobj);

		Point3f point = new Point3f();
		point.setX(pointobj.getX() - pointCam.getX());
		point.setY(pointobj.getY() - pointCam.getY());
		point.setZ(pointobj.getZ() - pointCam.getZ());

		return point;
	}

	private static TransformGroup scale(Node node, Vector3d vector) {

		Transform3D transform3D = new Transform3D();
		transform3D.setScale(vector);
		TransformGroup transformGroup = new TransformGroup();
		transformGroup.setTransform(transform3D);

		transformGroup.addChild(node);
		return transformGroup;
	}

	private Point3f[] cover(Point4f[] pset) {
		Point3f[] ret = new Point3f[pset.length];
		for (int i = 0; i < pset.length; i++) {
			float w = pset[i].w;
			ret[i] = new Point3f(pset[i].x / w, pset[i].y / w, pset[i].z / w);
		}
		return ret;
	}

	private Shape3D mybezier(Point4f[] ctrl) {
		cover(ctrl);
		Shape3D ret = new Shape3D();
		ret.setGeometry(bezier(ctrl));
		return ret;
	}

	private float[] mymul(float[] tt, float[][] M) {
		int i, j;
		float[] ret = new float[4];
		for (i = 0; i < 4; i++) {
			ret[i] = 0;
			for (j = 0; j < 4; j++) {
				ret[i] += tt[j] * M[j][i];
			}
		}
		return ret;
	}

	private Geometry bezier(Point4f[] ctrl) {
		int nt = 100;
		Point3f[] curv = getCurve(ctrl, nt);
		LineStripArray la = new LineStripArray(nt, GeometryArray.COORDINATES,
				new int[] { nt });
		la.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
		la.setCapability(GeometryArray.ALLOW_COORDINATE_WRITE);
		la.setCoordinates(0, curv);
		return la;
	}

	public Point3f[] getCurve(Point4f[] ctrl, int nt) {
		float[][] M = { { 1, 0, 0, 0 }, { -3, 3, 0, 0 }, { 3, -6, 3, 0 },
				{ -1, 3, -3, 1 } };

		float[] t = new float[nt];
		float div = (float) (1.0 / (nt - 1));
		int i;
		for (i = 0; i < nt; i++) {
			t[i] = i * div;
		}
		float[][] pset = new float[4][4];
		for (i = 0; i < 4; i++) {
			ctrl[i].get(pset[i]);
		}
		Point3f[] curv = new Point3f[nt];
		for (i = 0; i < nt; i++) {
			float[] tt = { 1, t[i], t[i] * t[i], t[i] * t[i] * t[i] };
			float[] tmp = mymul(tt, M);
			tmp = mymul(tmp, pset);

			curv[i] = new Point3f(tmp[0] / tmp[3], tmp[1] / tmp[3], tmp[2]
					/ tmp[3]);
		}
		return curv;
	}

	public static TransformGroup getAxesGroup() {
		ColorCube xAxis = new ColorCube();
		TransformGroup xGroup = scale(xAxis, new Vector3d(0.25, 0.01, 0.01));

		ColorCube yAxis = new ColorCube();
		TransformGroup yGroup = scale(yAxis, new Vector3d(0.01, 0.25, 0.01));

		ColorCube zAxis = new ColorCube();
		TransformGroup zGroup = scale(zAxis, new Vector3d(0.01, 0.01, 0.25));

		TransformGroup axesGroup = new TransformGroup();
		axesGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		axesGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		axesGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		axesGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		axesGroup.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
		axesGroup.addChild(xGroup);
		axesGroup.addChild(yGroup);
		axesGroup.addChild(zGroup);

		return axesGroup;
	}

	public void translationRelativeCoordWorld(TransformGroup objTrans, float x,
			float y, float z) {
		Transform3D worldMove = new Transform3D();
		worldMove.setTranslation(new Vector3f(x, y, z));
		Transform3D localToVworld = new Transform3D();
		objTrans.getLocalToVworld(localToVworld);
		localToVworld.invert();
		worldMove.mul(localToVworld, worldMove);
		Transform3D objectLocal = new Transform3D();
		objTrans.getTransform(objectLocal);
		objectLocal.mul(worldMove, objectLocal);
		objTrans.setTransform(objectLocal);
	}

	public void translationAbsCoordWorld(TransformGroup objTrans, float x,
			float y, float z) {
		Transform3D other = new Transform3D();
		objTrans.getTransform(other);
		Vector3f v = new Vector3f(x, y, z);
		Transform3D trobj = new Transform3D();
		trobj.mul(other);
		trobj.setTranslation(v);
		objTrans.setTransform(trobj);
	}

	public float getDistance(Point3f p1, Point3f p2) {
		return (float) Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2)
				+ Math.pow(p1.getY() - p2.getY(), 2)
				+ Math.pow(p1.getZ() - p2.getZ(), 2));
	}

	public void translationRelative(TransformGroup objTrans, float x, float y,
			float z) {
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(x, y, z));

		Transform3D tAbs = new Transform3D();
		objTrans.getTransform(tAbs);

		tAbs.mul(trans);
		objTrans.setTransform(tAbs);
	}

	public void moveCamera(float eyex, float eyey, float eyez, float centerx,
			float centery, float centerz, float upx, float upy, float upz) {
		Point3d eye = new Point3d(eyex, eyey, eyez);
		Point3d center = new Point3d(centerx, centery, centerz);
		Vector3d up = new Vector3d(upx, upy, upz);
		positionCamera(eye, center, up);
	}

	public void echelleRelativeCoordWorld(TransformGroup objTrans, float x,
			float y, float z) {
		Transform3D worldMove = new Transform3D();
		worldMove.setScale(new Vector3d(x, y, z));
		Transform3D localToVworld = new Transform3D();
		objTrans.getLocalToVworld(localToVworld);
		localToVworld.invert();
		worldMove.mul(localToVworld, worldMove);
		Transform3D objectLocal = new Transform3D();
		objTrans.getTransform(objectLocal);
		objectLocal.mul(worldMove, objectLocal);
		objTrans.setTransform(objectLocal);
	}

	public void echelleAbsCoordWorld(TransformGroup objTrans, float x, float y,
			float z) {
		Transform3D other = new Transform3D();
		objTrans.getTransform(other);
		Vector3d v = new Vector3d(x, y, z);
		Transform3D trobj = new Transform3D();
		trobj.mul(other);
		trobj.setScale(v);
		objTrans.setTransform(trobj);

	}

	public void echelleRelative(TransformGroup objTrans, float x, float y,
			float z) {
		Transform3D trans = new Transform3D();
		trans.setScale(new Vector3d(x, y, z));
		Transform3D tAbs = new Transform3D();
		objTrans.getTransform(tAbs);
		tAbs.mul(trans);
		objTrans.setTransform(tAbs);
	}

	public void rotationRelativeDegreeCoordWorld(TransformGroup objTrans,
			float xz, float yz, float xy, double angleDeg) {
		Transform3D worldMove = new Transform3D();
		AxisAngle4f angle = new AxisAngle4f(xz, yz, xy,
				(float) Math.toRadians(angleDeg));
		worldMove.setRotation(angle);
		Transform3D localToVworld = new Transform3D();
		objTrans.getLocalToVworld(localToVworld);
		localToVworld.invert();
		worldMove.mul(localToVworld, worldMove);
		Transform3D objectLocal = new Transform3D();
		objTrans.getTransform(objectLocal);
		objectLocal.mul(worldMove, objectLocal);
		objTrans.setTransform(objectLocal);
	}

	public void rotationAbsCoordWorld(TransformGroup objTrans, float xz,
			float yz, float xy, double angleDeg) {
		Transform3D other = new Transform3D();
		objTrans.getTransform(other);
		AxisAngle4f angle = new AxisAngle4f(xz, yz, xy,
				(float) Math.toRadians(angleDeg));
		Transform3D trobj = new Transform3D();
		trobj.mul(other);
		trobj.setRotation(angle);
		objTrans.setTransform(trobj);
	}

	public void rotationRelativeDegree(TransformGroup objTrans, float xz,
			float yz, float xy, double angleDeg) {
		Transform3D rotationRelative = new Transform3D();
		AxisAngle4f angle = new AxisAngle4f(xz, yz, xy,
				(float) Math.toRadians(angleDeg));
		rotationRelative.setRotation(angle);
		Transform3D tAbs = new Transform3D();
		objTrans.getTransform(tAbs);
		tAbs.mul(rotationRelative);
		objTrans.setTransform(tAbs);
	}

	public void positionCamera(Point3d eye, Point3d center, Vector3d up) {
		Transform3D look = new Transform3D();
		look.lookAt(eye, center, up);
		look.invert();
		ViewingPlatform viewingPlatform = universe.getViewingPlatform();
		TransformGroup viewTrans = viewingPlatform.getViewPlatformTransform();
		viewTrans.setTransform(look);
	}

	public SimpleUniverse getUniverse() {
		return universe;
	}

	public void setUniverse(SimpleUniverse universe) {
		this.universe = universe;
	}

	public TransformGroup getCam() {
		return cam;
	}

}
