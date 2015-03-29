package fr.molecules;

import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Sphere;

public class MSphere extends Sphere implements IMObj {

	private String id;

	public MSphere(String id, float arg) {
		super(arg);
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
		return this.getShape(0).getGeometry(arg0);
	}

	@Override
	public Geometry getGeometry() {
		return this.getShape(0).getGeometry();
	}

}
