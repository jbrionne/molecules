package fr.molecules;

import java.util.TimerTask;

import fr.molecules.ISpaceManager;
import fr.molecules.MInstance;

public class MyTimerTaskLine extends TimerTask{

	private ISpaceManager me;
	float vx1 = 1.0f;
	float vy1 = 0.0f;
	float vz1 = 0.0f;
	float vx2 = -1.0f;
	float vy2 = 0.0f;
	float vz2 = 0.0f;
	
	
	float xEnCours = 0.0f;
	
	float sign1 = 1.0f;
	float sign2 = -1.0f;
	MInstance ligne1;
	
	public MyTimerTaskLine(ISpaceManager me, MInstance ligne1){
		this.me = me;
		this.ligne1 = ligne1;
	}
	
	
	@Override
	public void run() {
		//(x-x0)^2 + (y-y0)^2 = R^2
		//x^2 + y^2 = 1;
		xEnCours += 0.2f;
		if(xEnCours >= Math.PI){
			xEnCours = 0.1f;
		}
		
		vx1 = (float) Math.cos(xEnCours);
		vx2 = (float) Math.cos(xEnCours + Math.PI);
		
		
		vy1 =  ((float) Math.sqrt(1 - Math.pow(vx1, 2))) * sign1;
		vy2 =  ((float) Math.sqrt(1 - Math.pow(vx2, 2))) * sign2;
		
		
		me.move(ligne1 , vx1, vy1, 0, vx2, vy2, 0);			

	}

}
