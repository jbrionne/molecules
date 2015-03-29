package fr.molecules;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;

public class MBranchGroup extends BranchGroup implements IMObj {

	private String id;

	public MBranchGroup(String id) {
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
		return (TransformGroup) this.getChild(0);
	}

	@Override
	public Geometry getGeometry(int arg0) {
		return null;
	}

	@Override
	public Geometry getGeometry() {
		return null;
	}

}
