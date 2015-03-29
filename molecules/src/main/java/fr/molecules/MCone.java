package fr.molecules;

import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Cone;

public class MCone extends Cone implements IMObj {

	private String id;

	public MCone(String id, float arg1, float arg2) {
		super(arg1, arg2);
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
