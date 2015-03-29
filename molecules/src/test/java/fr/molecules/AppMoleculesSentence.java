package fr.molecules;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.molecules.ISpaceManager;
import fr.molecules.MAtom;
import fr.molecules.MMolecule;
import fr.molecules.Space;
import fr.molecules.SpaceManager;

public class AppMoleculesSentence {

	public static void main(String[] args) throws InterruptedException {

		double xcmin = 100;
		double xcmax = 3000;
		double ycmin = 100;
		double ycmax = 3000;
		double zcmin = 100;
		double zcmax = 3000;

		float xcam = (float) ((xcmax - xcmin) / 2.0);
		float ycam = (float) ((ycmax - ycmin) / 2.0);

		ISpaceManager me = new SpaceManager(false);
		Space esp = me.getSpace();
		me.moveCamera((float) xcmax + 200, (float) ycmax + 200, 15000.0f, xcam,
				ycam, 0, 0f, 1.0f, 0f);

		double kconstantforce = 5000000;
		double force = 110.0;
		double forcediag = Math.sqrt(Math.pow(force, 2) + Math.pow(force, 2));
		double forcediagg = Math.sqrt(Math.pow(force, 2)
				+ Math.pow(forcediag, 2));

		List<MMolecule> lstMolecule = new ArrayList<MMolecule>();
		MMoleculeSentence mole = new MMoleculeSentence(me, force, "mol1_",
				101, 2000);
		lstMolecule.add(mole);

		mole.getTabMole1()[0][0][0].setVitessex(0.0);
		mole.getTabMole1()[0][0][0].setVitessey(2000);
		mole.getTabMole1()[0][0][0].setVitessez(0.0);

		int te = 1;
		int t = 0;
		Calendar cal = Calendar.getInstance();
		double tempsTot = 0;
		while (true) {
			Calendar calEnCours = Calendar.getInstance();
			double temps = (calEnCours.getTimeInMillis() - cal
					.getTimeInMillis()) / 1000.0;
			tempsTot += temps;
			cal = calEnCours;
			int index = 0;
			for (MMolecule molecule : lstMolecule) {
				for (int iii = 0; iii < molecule.getNbhauteur(); iii++) {
					for (int jjj = 0; jjj < molecule.getNbcolonne(); jjj++) {
						for (int kkk = 0; kkk < molecule.getNbligne(); kkk++) {
							MAtom encours = molecule.getTabMole1()[kkk][jjj][iii];

							if (encours.getMolgauche() != null) {
								forceRessort(encours, encours.getMolgauche(),
										force, kconstantforce);
							}
							if (encours.getMolhautgauche() != null) {
								forceRessort(encours,
										encours.getMolhautgauche(), forcediag,
										kconstantforce);
							}
							if (encours.getMolhautcentre() != null) {
								forceRessort(encours,
										encours.getMolhautcentre(), force,
										kconstantforce);
							}
							if (encours.getMolhautdroite() != null) {
								forceRessort(encours,
										encours.getMolhautdroite(), forcediag,
										kconstantforce);
							}
							if (encours.getApmolhautgauche() != null) {
								forceRessort(encours,
										encours.getApmolhautgauche(),
										forcediagg, kconstantforce);
							}
							if (encours.getApmolhautcentre() != null) {
								forceRessort(encours,
										encours.getApmolhautcentre(),
										forcediag, kconstantforce);
							}
							if (encours.getApmolhautdroite() != null) {
								forceRessort(encours,
										encours.getApmolhautdroite(),
										forcediagg, kconstantforce);
							}
							if (encours.getAvmolhautgauche() != null) {
								forceRessort(encours,
										encours.getAvmolhautgauche(),
										forcediagg, kconstantforce);
							}
							if (encours.getAvmolhautcentre() != null) {
								forceRessort(encours,
										encours.getAvmolhautcentre(),
										forcediag, kconstantforce);
							}
							if (encours.getAvmolhautdroite() != null) {
								forceRessort(encours,
										encours.getAvmolhautdroite(),
										forcediagg, kconstantforce);
							}
							if (encours.getAvmolgauche() != null) {
								forceRessort(encours, encours.getAvmolgauche(),
										forcediag, kconstantforce);
							}
							if (encours.getAvmolcentre() != null) {
								forceRessort(encours, encours.getAvmolcentre(),
										force, kconstantforce);
							}
							if (encours.getAvmoldroite() != null) {
								forceRessort(encours, encours.getAvmoldroite(),
										forcediag, kconstantforce);
							}
						}
					}
				}

				for (int iii = 0; iii < molecule.getNbhauteur(); iii++) {
					for (int jjj = 0; jjj < molecule.getNbcolonne(); jjj++) {
						for (int kkk = 0; kkk < molecule.getNbligne(); kkk++) {
							MAtom encours = molecule.getTabMole1()[kkk][jjj][iii];

							// forceApesanteur(encours);

							if (tempsTot < 20) {
								encours.setAccey(-(encours.getY() - 101) * 50
										+ encours.getAccey());

								encours.setVitessey(encours.getAccey() * temps
										+ encours.getVitessey());

								encours.setY(encours.getY()
										+ encours.getVitessey()
										* temps
										+ (encours.getAccey() * Math.pow(temps,
												2)) / 2.0);
							} else {

								encours.setVitessex(encours.getAccex() * temps
										+ encours.getVitessex());
								encours.setVitessey(encours.getAccey() * temps
										+ encours.getVitessey());
								encours.setVitessez(encours.getAccez() * temps
										+ encours.getVitessez());

								encours.setX(encours.getX()
										+ encours.getVitessex()
										* temps
										+ (encours.getAccex() * Math.pow(temps,
												2)) / 2.0);
								encours.setY(encours.getY()
										+ encours.getVitessey()
										* temps
										+ (encours.getAccey() * Math.pow(temps,
												2)) / 2.0);
								encours.setZ(encours.getZ()
										+ encours.getVitessez()
										* temps
										+ (encours.getAccez() * Math.pow(temps,
												2)) / 2.0);

								if (encours.getX() < xcmin) {
									encours.setVitessex(Math.abs(encours
											.getVitessex()));
									encours.setX(xcmin + 1);
								}
								if (encours.getX() > xcmax) {
									encours.setVitessex((-1)
											* Math.abs(encours.getVitessex()));
									encours.setX(xcmax - 1);
								}

								if (encours.getY() < ycmin) {
									encours.setVitessey(Math.abs(encours
											.getVitessey()));
									encours.setY(ycmin + 1);
								}

								if (encours.getY() > ycmax) {
									encours.setVitessey((-1)
											* Math.abs(encours.getVitessey()));
									encours.setY(ycmax - 1);
								}

								if (encours.getZ() < zcmin) {
									encours.setVitessez(Math.abs(encours
											.getVitessez()));
									encours.setZ(zcmin + 1);
								}
								if (encours.getZ() > zcmax) {
									encours.setVitessez((-1)
											* Math.abs(encours.getVitessez()));
									encours.setZ(zcmax - 1);
								}
							}

							esp.translationAbsCoordWorld(encours
									.getMyInstance().getTransformeGroup(),
									(float) encours.getX(), (float) encours
											.getY(), (float) encours.getZ());

							encours.setAccex(0.0);
							encours.setAccey(0.0);
							encours.setAccez(0.0);

						}

					}
				}

			}

		}

	}

	private static void forceRessort(MAtom encours, MAtom next,
			double forceressort, double kconstantforce) {
		double distancex = next.getX() - encours.getX();
		double distancey = next.getY() - encours.getY();
		double distancez = next.getZ() - encours.getZ();
		double distancetotale = Math.sqrt((Math.pow(distancex, 2)
				+ Math.pow(distancey, 2) + Math.pow(distancez, 2)));
		double forcetot = (distancetotale - forceressort) / 50.0
				* kconstantforce;
		analyse(encours, next, distancetotale, distancex, distancey, distancez,
				forcetot);
	}

	private static void forceRepulsion(MAtom encours, MAtom next) {
		double distancex = next.getX() - encours.getX();
		double distancey = next.getY() - encours.getY();
		double distancez = next.getZ() - encours.getZ();
		double distancetotale = Math.sqrt((Math.pow(distancex, 2)
				+ Math.pow(distancey, 2) + Math.pow(distancez, 2)));
		double forcetot = (-1) * encours.getPoids() * next.getPoids()
				/ (Math.pow(distancetotale, 2)) * 1.0;
		analyse(encours, next, distancetotale, distancex, distancey, distancez,
				forcetot);
	}

	private static void forceContact(MAtom encours, MAtom next) {
		double distancex = next.getX() - encours.getX();
		double distancey = next.getY() - encours.getY();
		double distancez = next.getZ() - encours.getZ();
		double distancetotale = Math.sqrt((Math.pow(distancex, 2)
				+ Math.pow(distancey, 2) + Math.pow(distancez, 2)));
		if (distancetotale < 100) {
			double forcetot = (-1) * encours.getPoids() * next.getPoids()
					* 0.001;
			analyse(encours, next, distancetotale, distancex, distancey,
					distancez, forcetot);
		}
	}

	private static void forceApesanteur(MAtom encours) {
		encours.setAccey(-0.1 + encours.getAccey());
	}

	private static void analyse(MAtom encours, MAtom next,
			double distancetotale, double distancex, double distancey,
			double distancez, double forcetot) {

		double distancexy = Math.sqrt((Math.pow(distancex, 2) + Math.pow(
				distancey, 2)));
		double anglealpha = 0;
		double anglebeta = 0;
		double forceinter = 0;
		double forcex = 0;
		double forcey = 0;
		double forcez = 0;
		double intermedx = 0;
		double intermedy = 0;
		double intermedz = 0;

		if (distancexy == 0) {
			anglealpha = 90.0;
		}

		if (distancexy != 0) {
			anglealpha = Math.atan(distancez / distancexy);
		}

		if (distancex == 0) {
			anglebeta = 90.0;
		}

		if (distancex != 0) {
			anglebeta = Math.atan(distancey / distancex);
		}

		if (forcetot == 0) {
			forceinter = 1;
		}
		if (forcetot != 0) {
			forceinter = (forcetot / Math.abs(forcetot));
		}
		forcex = Math.abs(Math.cos(anglebeta) * Math.cos(anglealpha))
				* forcetot;
		forcey = Math.abs(Math.sqrt((Math.pow(
				(forcetot * Math.cos(anglealpha)), 2) - Math.pow(forcex, 2))))
				* forceinter;
		forcez = Math.abs(Math.sin(anglealpha)) * forcetot;

		if (distancex == 0.0) {
			intermedx = 0.0;
		}
		if (distancex != 0.0) {
			intermedx = distancex / Math.abs(distancex);
		}

		if (distancey == 0.0) {
			intermedy = 0.0;
		}
		if (distancey != 0.0) {
			intermedy = distancey / Math.abs(distancey);
		}

		if (distancez == 0.0) {
			intermedz = 0.0;
		}
		if (distancez != 0.0) {
			intermedz = distancez / Math.abs(distancez);
		}

		encours.setAccex((1 / encours.getPoids()) * forcex * intermedx
				+ encours.getAccex());
		encours.setAccey((1 / encours.getPoids()) * forcey * intermedy
				+ encours.getAccey());
		encours.setAccez((1 / encours.getPoids()) * forcez * intermedz
				+ encours.getAccez());

		next.setAccex((1 / next.getPoids()) * (-1) * forcex * intermedx
				+ next.getAccex());
		next.setAccey((1 / next.getPoids()) * (-1) * forcey * intermedy
				+ next.getAccey());
		next.setAccez((1 / next.getPoids()) * (-1) * forcez * intermedz
				+ next.getAccez());

	}

}
