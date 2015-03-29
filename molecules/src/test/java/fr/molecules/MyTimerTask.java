package fr.molecules;

import java.util.TimerTask;

import javax.media.j3d.TransformGroup;

import fr.molecules.Space;

public class MyTimerTask extends TimerTask{

	private Space esp;
	private TransformGroup t;
	
	
	public MyTimerTask(Space esp, TransformGroup t){
		this.esp = esp;
		this.t = t;
	}
	
	
	@Override
	public void run() {
		esp.translationRelative(t, 0.1f, 0.0f, 0.0f);		
	}

}
