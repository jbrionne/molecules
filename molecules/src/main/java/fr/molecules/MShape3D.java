package fr.molecules;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;

public class MShape3D extends Shape3D implements IMObj {

	private String id;

	public MShape3D(String id) {
		super();
		this.id = id;
	}

	public MShape3D(String id, Geometry g, Appearance app) {
		super(g, app);
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public TransformGroup getTransform() {
		return (TransformGroup) this.getParent();
	}

	@Override
	public Geometry getGeometry(int arg0) {
		return super.getGeometry(arg0);
	}

	@Override
	public Geometry getGeometry() {
		return super.getGeometry();
	}

}
