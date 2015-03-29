package fr.molecules;

import javax.media.j3d.TransformGroup;

public class InfoObject {

	private TransformGroup transformeGroup;
	private IMObj o;
	private BranchAndTransform brAndtr;

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

	public BranchAndTransform getBrAndtr() {
		return brAndtr;
	}

	public void setBrAndtr(BranchAndTransform brAndtr) {
		this.brAndtr = brAndtr;
	}

}
