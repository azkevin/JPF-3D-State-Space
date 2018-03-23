package model;

/**
 * Holds information on the states searched by JPF
 *
 * @author Kevin Arindaeng
 * @version 1.0
 * @since 2018-03-23
 */
public class State {
	
	private int stateId;
	private boolean hasNext;
	private boolean isNew;
	private boolean isEndState;
	
	public State(int stateId, boolean hasNext, boolean isNew, boolean isEndState) {
		this.stateId = stateId;
		this.hasNext = hasNext;
		this.isNew = isNew;
		this.isEndState = isEndState;
	}
	
	@Override
	public String toString() {
		return "stateId: " + this.stateId + 
				", hasNext: " + this.hasNext + 
				", isNew: " + this.isNew +
				", isEndState: " + this.isEndState;
	}
	
}
