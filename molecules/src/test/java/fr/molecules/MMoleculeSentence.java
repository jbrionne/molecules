package fr.molecules;

import fr.molecules.ISpaceManager;
import fr.molecules.MAtom;
import fr.molecules.MInstance;
import fr.molecules.MMolecule;
import fr.molecules.Space;
import fr.molecules.SpaceManager;

public class MMoleculeSentence extends MMolecule {

	public MMoleculeSentence(ISpaceManager me, double force, String denomination,
			double pos, double poids) {
		super();
		int nbhauteur = 1;
		int nbligne = 14;
		int nbcolonne = 1;

		setNbhauteur(nbhauteur);
		setNbligne(nbligne);
		setNbcolonne(nbcolonne);
		Space esp = me.getSpace();
		setTabMole1(new MAtom[nbligne][nbcolonne][nbhauteur]);
		int indexMol = 1;
		for (int ii = 0; ii < nbhauteur; ii++) {
			for (int jj = 0; jj < nbcolonne; jj++) {
				for (int kk = 0; kk < nbligne; kk++) {
					MInstance myInstance = null;
					if (kk == 0) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.H, false);
					}
					if (kk == 1) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.E, false);
					}
					if (kk == 2) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.L, false);
					}
					if (kk == 3) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.L, false);
					}
					if (kk == 4) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.O, false);
					}
					if (kk == 5) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.TSPHERE, false);
					}
					if (kk == 6) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.H, false);
					}
					if (kk == 7) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.A, false);
					}
					if (kk == 8) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.V, false);
					}
					if (kk == 9) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.E, false);
					}
					if (kk == 10) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.TSPHERE, false);
					}
					if (kk == 11) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.F, false);
					}
					if (kk == 12) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.U, false);
					}
					if (kk == 13) {
						myInstance = new MInstance(denomination + indexMol,
								SpaceManager.N, false);
					}

					MAtom mol = new MAtom();
					mol.setMyInstance(myInstance);
					getTabMole1()[kk][jj][ii] = mol;
					me.imagineX(myInstance);

					mol.setPoids(poids);
					indexMol++;
				}
			}
		}

		for (int iii = 0; iii < nbhauteur; iii++) {
			for (int jjj = 0; jjj < nbcolonne; jjj++) {
				for (int kkk = 0; kkk < nbligne; kkk++) {

					MAtom encours = getTabMole1()[kkk][jjj][iii];

					encours.setX(kkk * force + pos);
					encours.setY(jjj * force + pos);
					encours.setZ(iii * force + pos);

					esp.translationAbsCoordWorld(encours.getMyInstance()
							.getTransformeGroup(), (float) encours.getX(),
							(float) encours.getY(), (float) encours.getZ());
					esp.echelleRelative(encours.getMyInstance()
							.getTransformeGroup(), 200.0f, 200.0f, 200.0f);

					int lim = 0;
					int limi = nbhauteur - 1;
					int limj = nbcolonne - 1;
					int limk = nbligne - 1;

					if (kkk != lim) {
						encours.setMolgauche(getTabMole1()[kkk - 1][jjj][iii]);
					}
					if (kkk != limk) {
						encours.setMoldroite(getTabMole1()[kkk + 1][jjj][iii]);
					}
					if (jjj != limj) {
						encours.setMolhautcentre(getTabMole1()[kkk][jjj + 1][iii]);
					}
					if (kkk != lim && jjj != limj) {
						encours.setMolhautgauche(getTabMole1()[kkk - 1][jjj + 1][iii]);
					}
					if (kkk != limk && jjj != limj) {
						encours.setMolhautdroite(getTabMole1()[kkk + 1][jjj + 1][iii]);
					}
					if (jjj != lim) {
						encours.setMolbascentre(getTabMole1()[kkk][jjj - 1][iii]);
					}
					if (kkk != lim && jjj != lim) {
						encours.setMolbasgauche(getTabMole1()[kkk - 1][jjj - 1][iii]);
					}
					if (kkk != limk && jjj != lim) {
						encours.setMolbasdroite(getTabMole1()[kkk + 1][jjj - 1][iii]);
					}
					if (iii != limi) {
						encours.setAvmolcentre(getTabMole1()[kkk][jjj][iii + 1]);
					}
					if (kkk != lim && iii != limi) {
						encours.setAvmolgauche(getTabMole1()[kkk - 1][jjj][iii + 1]);
					}
					if (kkk != limk && iii != limi) {
						encours.setAvmoldroite(getTabMole1()[kkk + 1][jjj][iii + 1]);
					}
					if (jjj != limj && iii != limi) {
						encours.setAvmolhautcentre(getTabMole1()[kkk][jjj + 1][iii + 1]);
					}
					if (kkk != lim && jjj != limj && iii != limi) {
						encours.setAvmolhautgauche(getTabMole1()[kkk - 1][jjj + 1][iii + 1]);
					}
					if (kkk != limk && jjj != limj && iii != limi) {
						encours.setAvmolhautdroite(getTabMole1()[kkk + 1][jjj + 1][iii + 1]);
					}
					if (jjj != lim && iii != limi) {
						encours.setAvmolbascentre(getTabMole1()[kkk][jjj - 1][iii + 1]);
					}
					if (kkk != lim && jjj != lim && iii != limi) {
						encours.setAvmolbasgauche(getTabMole1()[kkk - 1][jjj - 1][iii + 1]);
					}
					if (kkk != limk && jjj != lim && iii != limi) {
						encours.setAvmolbasdroite(getTabMole1()[kkk + 1][jjj - 1][iii + 1]);
					}
					if (iii != lim) {
						encours.setApmolcentre(getTabMole1()[kkk][jjj][iii - 1]);
					}
					if (kkk != lim && iii != lim) {
						encours.setApmolgauche(getTabMole1()[kkk - 1][jjj][iii - 1]);
					}
					if (kkk != limk && iii != lim) {
						encours.setApmoldroite(getTabMole1()[kkk + 1][jjj][iii - 1]);
					}
					if (jjj != limj && iii != lim) {
						encours.setApmolhautcentre(getTabMole1()[kkk][jjj + 1][iii - 1]);
					}
					if (kkk != lim && jjj != limj && iii != lim) {
						encours.setApmolhautgauche(getTabMole1()[kkk - 1][jjj + 1][iii - 1]);
					}
					if (kkk != limk && jjj != limj && iii != lim) {
						encours.setApmolhautdroite(getTabMole1()[kkk + 1][jjj + 1][iii - 1]);
					}
					if (jjj != lim && iii != lim) {
						encours.setApmolbascentre(getTabMole1()[kkk][jjj - 1][iii - 1]);
					}
					if (kkk != lim && jjj != lim && iii != lim) {
						encours.setApmolbasgauche(getTabMole1()[kkk - 1][jjj - 1][iii - 1]);
					}
					if (kkk != limk && jjj != lim && iii != lim) {
						encours.setApmolbasdroite(getTabMole1()[kkk + 1][jjj - 1][iii - 1]);
					}

				}
			}
		}
	}

}
