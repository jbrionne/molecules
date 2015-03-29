package fr.molecules;

import java.util.Timer;

import javax.media.j3d.TransformGroup;

import fr.molecules.Space;

public class AppSpaceMove {

	
	private static Timer myTimer;
	
	public static String SPHERE1 = "SPHERE1";
	public static String BOX1 = "BOX1";
	public static String SPHERE2 = "SPHERE2";
	public static String BOX2 = "BOX2";
	public static String CHEVAL = "CHEVAL";
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Space esp = new Space(false);
		esp.addLightVector(0.0f, 0.0f, -1.0f);
		
		
		TransformGroup t = Space.getAxesGroup();
		esp.addToRootBranchGroupAndTransform(t);
		esp.addSphereToTransform(SPHERE1, t, 0.05f, false);
		
		esp.addBoxToTransform(BOX1, t, 0.3f, 0.1f, 0.5f, false);		
		
		
		
		myTimer = new Timer();
		myTimer.schedule(new MyTimerTask(esp, t), 0, 1000);
				
		
		Thread.sleep(500);
		
		
	}

}
