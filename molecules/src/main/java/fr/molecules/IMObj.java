package fr.molecules;

import javax.media.j3d.Geometry;
import javax.media.j3d.Locale;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public interface IMObj {

	String getId();

	void getLocalToVworld(Transform3D tr);

	Locale getLocale();

	Node getParent();

	TransformGroup getTransform();

	Geometry getGeometry(int arg0);

	Geometry getGeometry();

}
