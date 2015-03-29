package fr.molecules;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.Node;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CollisionDetector extends Behavior {

	private static final Logger LOG = LoggerFactory.getLogger(Compiler.class);

	protected WakeupCriterion[] theCriteria;

	protected WakeupOr oredCriteria;

	protected Node collidingShape;

	public CollisionDetector(Node theShape, Bounds theBounds) {
		collidingShape = theShape;
		collidingShape.setBounds(theBounds);
		setSchedulingBounds(theBounds);
	}

	@Override
	public void initialize() {
		theCriteria = new WakeupCriterion[2];
		theCriteria[0] = new WakeupOnCollisionEntry(collidingShape);
		theCriteria[1] = new WakeupOnCollisionExit(collidingShape);
		oredCriteria = new WakeupOr(theCriteria);
		wakeupOn(oredCriteria);
	}

	@Override
	public void processStimulus(Enumeration criteria) {

		WakeupCriterion theCriterion;

		while (criteria.hasMoreElements()) {
			theCriterion = (WakeupCriterion) criteria.nextElement();
			if (theCriterion instanceof WakeupOnCollisionEntry) {
				Node theLeaf = ((WakeupOnCollisionEntry) theCriterion)
						.getTriggeringPath().getObject();
				LOG.info("" + theLeaf.getBounds());
				LOG.info("in collision " + theLeaf.getUserData());
			} else if (theCriterion instanceof WakeupOnCollisionExit) {
				Node theLeaf = ((WakeupOnCollisionExit) theCriterion)
						.getTriggeringPath().getObject();
				LOG.info("" + theLeaf.getBounds());
				LOG.info("exit collision  " + theLeaf.getUserData());
			}
			wakeupOn(oredCriteria);
		}
	}
}
