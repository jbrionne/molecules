package fr.molecules;

import fr.molecules.ISpaceManager;
import fr.molecules.MInstance;
import fr.molecules.Space;
import fr.molecules.SpaceManager;


public class AppSpace {	
	
	public static void main(String[] args) throws InterruptedException {
		
		MInstance s = new MInstance("sphere1" , SpaceManager.TSPHERE, false);		
		MInstance s_dessus = new MInstance("s_dessus" , SpaceManager.TSPHERE, false);
		MInstance s_dessous = new MInstance("s_dessous" , SpaceManager.TSPHERE, false);
		MInstance s_agauche = new MInstance("s_agauche" , SpaceManager.TSPHERE, false);
		MInstance s_adroite = new MInstance("s_adroite" , SpaceManager.TSPHERE, false);
		MInstance s_devant = new MInstance("s_devant" , SpaceManager.TSPHERE, false);
		MInstance s_derriere = new MInstance("s_derriere" , SpaceManager.TSPHERE, false);
		
		MInstance s_audessus = new MInstance("s_audessus" , SpaceManager.TSPHERE, false);
		MInstance s_endessous = new MInstance("s_endessous" , SpaceManager.TSPHERE, false);
		MInstance s_alagauche = new MInstance("s_alagauche" , SpaceManager.TSPHERE, false);
		MInstance s_aladroite = new MInstance("s_aladroite" , SpaceManager.TSPHERE, false);
		MInstance s_audevant = new MInstance("s_audevant" , SpaceManager.TSPHERE, false);
		MInstance s_auderriere = new MInstance("s_auderriere" , SpaceManager.TSPHERE, false);
		
			
		ISpaceManager me = new SpaceManager(true);
		Space esp = me.getSpace();		
		me.imagineX(s);	
		esp.translationRelative(s.getTransformeGroup(), -0.5f, 0, 0);
		esp.rotationRelativeDegree(s.getTransformeGroup(),0, 0, 1.0f, 30);
		esp.rotationRelativeDegree(s.getTransformeGroup(),0, 1.0f, 0.0f, 60);		
		
		me.imagineXLeftY(s_agauche, s);
		me.imagineXRightY(s_adroite, s);
		me.imagineXBackY(s_derriere, s);
		me.imagineXFrontY(s_devant, s);
		me.imagineXTopY(s_dessus, s);
		me.imagineXBottomY(s_dessous, s);		
		
		me.imagineXRigthOfY(s_aladroite, s);
		me.imagineXLeftOfY(s_alagauche, s);
		me.imagineXBackOfY(s_auderriere, s);
		me.imagineXFrontOfY(s_audevant, s);
		me.imagineXBottomOfY(s_endessous, s);
		me.imagineXTopOfY(s_audessus, s);
		
	
	}

}
