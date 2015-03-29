package fr.molecules;

public class MMolecule {

	private int nbhauteur;
	private int nbligne;
	private int nbcolonne;

	private MAtom[][][] tabMole1;

	public MMolecule() {

	}

	public MMolecule(ISpaceManager me, int nbhauteur, int nbligne,
			int nbcolonne, double force, String denomination, double pos,
			double poids, String type) {
		super();
		this.nbhauteur = nbhauteur;
		this.nbligne = nbligne;
		this.nbcolonne = nbcolonne;
		Space esp = me.getSpace();
		tabMole1 = new MAtom[nbligne][nbcolonne][nbhauteur];
		int indexMol = 1;
		for (int ii = 0; ii < nbhauteur; ii++) {
			for (int jj = 0; jj < nbcolonne; jj++) {
				for (int kk = 0; kk < nbligne; kk++) {
					MInstance myInstance = new MInstance(denomination
							+ indexMol, type, false);
					MAtom mol = new MAtom();
					mol.setMyInstance(myInstance);
					tabMole1[kk][jj][ii] = mol;
					me.imagineX(myInstance);
					mol.setPoids(poids);
					indexMol++;
				}
			}
		}

		for (int iii = 0; iii < nbhauteur; iii++) {
			for (int jjj = 0; jjj < nbcolonne; jjj++) {
				for (int kkk = 0; kkk < nbligne; kkk++) {

					MAtom encours = tabMole1[kkk][jjj][iii];

					encours.setX(kkk * force + pos);
					encours.setY(jjj * force + pos);
					encours.setZ(iii * force + pos);

					esp.translationAbsCoordWorld(encours.getMyInstance()
							.getTransformeGroup(), (float) encours.getX(),
							(float) encours.getY(), (float) encours.getZ());
					esp.echelleRelative(encours.getMyInstance()
							.getTransformeGroup(), 100.0f, 100.0f, 100f);

					int lim = 0;
					int limi = nbhauteur - 1;
					int limj = nbcolonne - 1;
					int limk = nbligne - 1;

					if (kkk != lim) {
						encours.setMolgauche(tabMole1[kkk - 1][jjj][iii]);
					}
					if (kkk != limk) {
						encours.setMoldroite(tabMole1[kkk + 1][jjj][iii]);
					}
					if (jjj != limj) {
						encours.setMolhautcentre(tabMole1[kkk][jjj + 1][iii]);
					}
					if (kkk != lim && jjj != limj) {
						encours.setMolhautgauche(tabMole1[kkk - 1][jjj + 1][iii]);
					}
					if (kkk != limk && jjj != limj) {
						encours.setMolhautdroite(tabMole1[kkk + 1][jjj + 1][iii]);
					}
					if (jjj != lim) {
						encours.setMolbascentre(tabMole1[kkk][jjj - 1][iii]);
					}
					if (kkk != lim && jjj != lim) {
						encours.setMolbasgauche(tabMole1[kkk - 1][jjj - 1][iii]);
					}
					if (kkk != limk && jjj != lim) {
						encours.setMolbasdroite(tabMole1[kkk + 1][jjj - 1][iii]);
					}
					if (iii != limi) {
						encours.setAvmolcentre(tabMole1[kkk][jjj][iii + 1]);
					}
					if (kkk != lim && iii != limi) {
						encours.setAvmolgauche(tabMole1[kkk - 1][jjj][iii + 1]);
					}
					if (kkk != limk && iii != limi) {
						encours.setAvmoldroite(tabMole1[kkk + 1][jjj][iii + 1]);
					}
					if (jjj != limj && iii != limi) {
						encours.setAvmolhautcentre(tabMole1[kkk][jjj + 1][iii + 1]);
					}
					if (kkk != lim && jjj != limj && iii != limi) {
						encours.setAvmolhautgauche(tabMole1[kkk - 1][jjj + 1][iii + 1]);
					}
					if (kkk != limk && jjj != limj && iii != limi) {
						encours.setAvmolhautdroite(tabMole1[kkk + 1][jjj + 1][iii + 1]);
					}
					if (jjj != lim && iii != limi) {
						encours.setAvmolbascentre(tabMole1[kkk][jjj - 1][iii + 1]);
					}
					if (kkk != lim && jjj != lim && iii != limi) {
						encours.setAvmolbasgauche(tabMole1[kkk - 1][jjj - 1][iii + 1]);
					}
					if (kkk != limk && jjj != lim && iii != limi) {
						encours.setAvmolbasdroite(tabMole1[kkk + 1][jjj - 1][iii + 1]);
					}
					if (iii != lim) {
						encours.setApmolcentre(tabMole1[kkk][jjj][iii - 1]);
					}
					if (kkk != lim && iii != lim) {
						encours.setApmolgauche(tabMole1[kkk - 1][jjj][iii - 1]);
					}
					if (kkk != limk && iii != lim) {
						encours.setApmoldroite(tabMole1[kkk + 1][jjj][iii - 1]);
					}
					if (jjj != limj && iii != lim) {
						encours.setApmolhautcentre(tabMole1[kkk][jjj + 1][iii - 1]);
					}
					if (kkk != lim && jjj != limj && iii != lim) {
						encours.setApmolhautgauche(tabMole1[kkk - 1][jjj + 1][iii - 1]);
					}
					if (kkk != limk && jjj != limj && iii != lim) {
						encours.setApmolhautdroite(tabMole1[kkk + 1][jjj + 1][iii - 1]);
					}
					if (jjj != lim && iii != lim) {
						encours.setApmolbascentre(tabMole1[kkk][jjj - 1][iii - 1]);
					}
					if (kkk != lim && jjj != lim && iii != lim) {
						encours.setApmolbasgauche(tabMole1[kkk - 1][jjj - 1][iii - 1]);
					}
					if (kkk != limk && jjj != lim && iii != lim) {
						encours.setApmolbasdroite(tabMole1[kkk + 1][jjj - 1][iii - 1]);
					}

				}
			}
		}
	}

	public int getNbhauteur() {
		return nbhauteur;
	}

	public void setNbhauteur(int nbhauteur) {
		this.nbhauteur = nbhauteur;
	}

	public int getNbligne() {
		return nbligne;
	}

	public void setNbligne(int nbligne) {
		this.nbligne = nbligne;
	}

	public int getNbcolonne() {
		return nbcolonne;
	}

	public void setNbcolonne(int nbcolonne) {
		this.nbcolonne = nbcolonne;
	}

	public MAtom[][][] getTabMole1() {
		return tabMole1;
	}

	public void setTabMole1(MAtom[][][] tabMole1) {
		this.tabMole1 = tabMole1;
	}

}
