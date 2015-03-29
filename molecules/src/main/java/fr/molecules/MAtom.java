package fr.molecules;

public class MAtom {

	private MInstance myInstance;
	private double x = 0;
	private double y = 0;
	private double z = 0;
	private double vitessex = 0;
	private double vitessey = 0;
	private double vitessez = 0;
	private double accex = 0;
	private double accey = 0;
	private double accez = 0;
	private double poids = 0;

	private MAtom molgauche;
	private MAtom moldroite;
	private MAtom molhautcentre;
	private MAtom molhautgauche;
	private MAtom molhautdroite;
	private MAtom molbascentre;
	private MAtom molbasgauche;
	private MAtom molbasdroite;
	private MAtom avmolcentre;
	private MAtom avmolgauche;
	private MAtom avmoldroite;
	private MAtom avmolhautcentre;
	private MAtom avmolhautgauche;
	private MAtom avmolhautdroite;
	private MAtom avmolbascentre;
	private MAtom avmolbasgauche;
	private MAtom avmolbasdroite;
	private MAtom apmolcentre;
	private MAtom apmolgauche;
	private MAtom apmoldroite;
	private MAtom apmolhautcentre;
	private MAtom apmolhautgauche;
	private MAtom apmolhautdroite;
	private MAtom apmolbascentre;
	private MAtom apmolbasgauche;
	private MAtom apmolbasdroite;

	public double getVitessex() {
		return vitessex;
	}

	public void setVitessex(double vitessex) {
		this.vitessex = vitessex;
	}

	public double getVitessey() {
		return vitessey;
	}

	public void setVitessey(double vitessey) {
		this.vitessey = vitessey;
	}

	public double getVitessez() {
		return vitessez;
	}

	public void setVitessez(double vitessez) {
		this.vitessez = vitessez;
	}

	public double getAccex() {
		return accex;
	}

	public void setAccex(double accex) {
		this.accex = accex;
	}

	public double getAccey() {
		return accey;
	}

	public void setAccey(double accey) {
		this.accey = accey;
	}

	public double getAccez() {
		return accez;
	}

	public void setAccez(double accez) {
		this.accez = accez;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public MInstance getMyInstance() {
		return myInstance;
	}

	public void setMyInstance(MInstance myInstance) {
		this.myInstance = myInstance;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public MAtom getMolgauche() {
		return molgauche;
	}

	public void setMolgauche(MAtom molgauche) {
		this.molgauche = molgauche;
	}

	public MAtom getMoldroite() {
		return moldroite;
	}

	public void setMoldroite(MAtom moldroite) {
		this.moldroite = moldroite;
	}

	public MAtom getMolhautcentre() {
		return molhautcentre;
	}

	public void setMolhautcentre(MAtom molhautcentre) {
		this.molhautcentre = molhautcentre;
	}

	public MAtom getMolhautgauche() {
		return molhautgauche;
	}

	public void setMolhautgauche(MAtom molhautgauche) {
		this.molhautgauche = molhautgauche;
	}

	public MAtom getMolhautdroite() {
		return molhautdroite;
	}

	public void setMolhautdroite(MAtom molhautdroite) {
		this.molhautdroite = molhautdroite;
	}

	public MAtom getMolbascentre() {
		return molbascentre;
	}

	public void setMolbascentre(MAtom molbascentre) {
		this.molbascentre = molbascentre;
	}

	public MAtom getMolbasgauche() {
		return molbasgauche;
	}

	public void setMolbasgauche(MAtom molbasgauche) {
		this.molbasgauche = molbasgauche;
	}

	public MAtom getMolbasdroite() {
		return molbasdroite;
	}

	public void setMolbasdroite(MAtom molbasdroite) {
		this.molbasdroite = molbasdroite;
	}

	public MAtom getAvmolcentre() {
		return avmolcentre;
	}

	public void setAvmolcentre(MAtom avmolcentre) {
		this.avmolcentre = avmolcentre;
	}

	public MAtom getAvmolgauche() {
		return avmolgauche;
	}

	public void setAvmolgauche(MAtom avmolgauche) {
		this.avmolgauche = avmolgauche;
	}

	public MAtom getAvmoldroite() {
		return avmoldroite;
	}

	public void setAvmoldroite(MAtom avmoldroite) {
		this.avmoldroite = avmoldroite;
	}

	public MAtom getAvmolhautcentre() {
		return avmolhautcentre;
	}

	public void setAvmolhautcentre(MAtom avmolhautcentre) {
		this.avmolhautcentre = avmolhautcentre;
	}

	public MAtom getAvmolhautgauche() {
		return avmolhautgauche;
	}

	public void setAvmolhautgauche(MAtom avmolhautgauche) {
		this.avmolhautgauche = avmolhautgauche;
	}

	public MAtom getAvmolhautdroite() {
		return avmolhautdroite;
	}

	public void setAvmolhautdroite(MAtom avmolhautdroite) {
		this.avmolhautdroite = avmolhautdroite;
	}

	public MAtom getAvmolbascentre() {
		return avmolbascentre;
	}

	public void setAvmolbascentre(MAtom avmolbascentre) {
		this.avmolbascentre = avmolbascentre;
	}

	public MAtom getAvmolbasgauche() {
		return avmolbasgauche;
	}

	public void setAvmolbasgauche(MAtom avmolbasgauche) {
		this.avmolbasgauche = avmolbasgauche;
	}

	public MAtom getAvmolbasdroite() {
		return avmolbasdroite;
	}

	public void setAvmolbasdroite(MAtom avmolbasdroite) {
		this.avmolbasdroite = avmolbasdroite;
	}

	public MAtom getApmolcentre() {
		return apmolcentre;
	}

	public void setApmolcentre(MAtom apmolcentre) {
		this.apmolcentre = apmolcentre;
	}

	public MAtom getApmolgauche() {
		return apmolgauche;
	}

	public void setApmolgauche(MAtom apmolgauche) {
		this.apmolgauche = apmolgauche;
	}

	public MAtom getApmoldroite() {
		return apmoldroite;
	}

	public void setApmoldroite(MAtom apmoldroite) {
		this.apmoldroite = apmoldroite;
	}

	public MAtom getApmolhautcentre() {
		return apmolhautcentre;
	}

	public void setApmolhautcentre(MAtom apmolhautcentre) {
		this.apmolhautcentre = apmolhautcentre;
	}

	public MAtom getApmolhautgauche() {
		return apmolhautgauche;
	}

	public void setApmolhautgauche(MAtom apmolhautgauche) {
		this.apmolhautgauche = apmolhautgauche;
	}

	public MAtom getApmolhautdroite() {
		return apmolhautdroite;
	}

	public void setApmolhautdroite(MAtom apmolhautdroite) {
		this.apmolhautdroite = apmolhautdroite;
	}

	public MAtom getApmolbascentre() {
		return apmolbascentre;
	}

	public void setApmolbascentre(MAtom apmolbascentre) {
		this.apmolbascentre = apmolbascentre;
	}

	public MAtom getApmolbasgauche() {
		return apmolbasgauche;
	}

	public void setApmolbasgauche(MAtom apmolbasgauche) {
		this.apmolbasgauche = apmolbasgauche;
	}

	public MAtom getApmolbasdroite() {
		return apmolbasdroite;
	}

	public void setApmolbasdroite(MAtom apmolbasdroite) {
		this.apmolbasdroite = apmolbasdroite;
	}

}
