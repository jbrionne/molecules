package fr.molecules;

import javax.media.j3d.Geometry;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Box;

public class MBox extends Box implements IMObj {

	private String id;

	public MBox(String id, float largeur, float hauteur, float profondeur) {
		super(largeur, hauteur, profondeur, null);
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
