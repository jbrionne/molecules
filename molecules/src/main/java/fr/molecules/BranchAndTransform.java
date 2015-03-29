package fr.molecules;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.TransformGroup;

public class BranchAndTransform {

	private BranchGroup brG;
	private TransformGroup trG;
	
	public BranchAndTransform(BranchGroup brG, TransformGroup trG) {
		super();
		this.brG = brG;
		this.trG = trG;
	}
	public BranchGroup getBrG() {
		return brG;
	}
	public void setBrG(BranchGroup brG) {
		this.brG = brG;
	}
	public TransformGroup getTrG() {
		return trG;
	}
	public void setTrG(TransformGroup trG) {
		this.trG = trG;
	}
	
}
