package fr.molecules;

import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3f;

public interface ISpaceManager {

	TransformGroup imagineX(MInstance i);	
	
	void imagineXLeftY(MInstance box, MInstance i);
	void imagineXRightY(MInstance box, MInstance i);
	void imagineXTopY(MInstance box, MInstance i);
	void imagineXBottomY(MInstance box, MInstance i);
	void imagineXFrontY(MInstance box, MInstance i);
	void imagineXBackY(MInstance box, MInstance i);
	
	
	void imagineXRigthOfY(MInstance box, MInstance i);
	void imagineXLeftOfY(MInstance box, MInstance i);
	void imagineXTopOfY(MInstance box, MInstance i);
	void imagineXBottomOfY(MInstance box, MInstance i);
	void imagineXFrontOfY(MInstance box, MInstance i);
	void imagineXBackOfY(MInstance box, MInstance i);
	
	
	boolean isXLeftY(MInstance b, MInstance id);
	boolean isXLeftOfY(MInstance b, MInstance id);
	boolean isXRightY(MInstance b, MInstance id);
	boolean isXRightOfY(MInstance b, MInstance id);
	boolean isXBottomY(MInstance b, MInstance id);
	boolean isXBottomOfY(MInstance b, MInstance id);
	boolean isXTopY(MInstance b, MInstance id);
	boolean isXTopOfY(MInstance b, MInstance id);
	boolean isXFrontY(MInstance b, MInstance id);
	boolean isXFrontOfY(MInstance b, MInstance id);
	boolean isXBackY(MInstance b, MInstance id);
	boolean isXBackOfY(MInstance b, MInstance id);
	
	void move(MInstance i, float vx1, float vy1, float vz1, float vx2, float vy2, float vz2);
	
	void move(MInstance i, Point3f p1, Point3f p2);
	
	void moveBezier(MInstance i, float p1x, float p1y, float p2x,
			float p2y, float p3x, float p3y, float p4x, float p4y);
	
	Space getSpace();
	
	
	void moveCamera();
	void moveCameraTop();
	void moveCamera(float eyex, float eyey, float eyez, float centerx, float centery, float centerz,  float upx, float upy, float upz);
		
	
	void rotateAbsX(MInstance b, float vx1, float vy1, float vz1, double angleDeg);
	void moveAbsX(MInstance b, float vx1, float vy1, float vz1);
	void scaleAbsX(MInstance b, float vx1, float vy1, float vz1);
	void rotateRelativeX(MInstance b, float vx1, float vy1, float vz1, double angleDeg);
	void moveRelativeX(MInstance b, float vx1, float vy1, float vz1);
	void scaleRelativeX(MInstance b, float vx1, float vy1, float vz1);
}
