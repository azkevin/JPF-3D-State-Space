package model;

/**
 * Holds information on a transition when searched by JPF
 *
 * @author Kevin Arindaeng
 * @version 1.0
 * @since 2018-03-23
 */
public class Transition {
	
	private int transitionId;
	private String step;
	
	public Transition(int transitionId, String step) {
		this.transitionId = transitionId;
		this.step = step;
	}
	
	@Override
	public String toString() {
		return "transitionId: " + this.transitionId + 
				", step: " + this.step;
	}
	
}
