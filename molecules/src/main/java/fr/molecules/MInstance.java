package fr.molecules;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class MInstance {

	private String id;
	private String type;
	private TransformGroup transformeGroup;
	private BranchGroup branchGroup;
	private IMObj o;
	private boolean withCollision = false;

	public MInstance(String id, String type, boolean withCollision) {
		super();
		this.id = id;
		this.type = type;
		this.withCollision = withCollision;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TransformGroup getTransformeGroup() {
		return transformeGroup;
	}

	public void setTransformeGroup(TransformGroup transformeGroup) {
		this.transformeGroup = transformeGroup;
	}

	public IMObj getO() {
		return o;
	}

	public void setO(IMObj o) {
		this.o = o;
	}

	public boolean isWithCollision() {
		return withCollision;
	}

	public void setWithCollision(boolean withCollision) {
		this.withCollision = withCollision;
	}

	public BranchGroup getBranchGroup() {
		return branchGroup;
	}

	public void setBranchGroup(BranchGroup branchGroup) {
		this.branchGroup = branchGroup;
	}

}
