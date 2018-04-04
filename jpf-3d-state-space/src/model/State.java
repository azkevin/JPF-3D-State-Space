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
	private String error;
	
	public State() {
		this.stateId = -1;
		this.hasNext = true;
		this.isNew = false;
		this.isEndState = false;
		this.error = null;
	}
	
	public State(int stateId, boolean hasNext, boolean isNew, boolean isEndState, String error) {
		this.stateId = stateId;
		this.hasNext = hasNext;
		this.isNew = isNew;
		this.isEndState = isEndState;
		this.error = error;
	}
	
	@Override
	public String toString() {
		return "stateId: " + this.stateId + 
				", hasNext: " + this.hasNext + 
				", isNew: " + this.isNew +
				", isEndState: " + this.isEndState +
				", error: " + this.error;
	}
	
	public void setState(int stateId, boolean hasNext, boolean isNew, boolean isEndState, String error) {
		this.setStateId(stateId);
		this.setHasNext(hasNext);
		this.setIsNew(isNew);
		this.setIsEndState(isEndState);
		this.setError(error);
	}
	
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isEndState() {
		return isEndState;
	}

	public void setIsEndState(boolean isEndState) {
		this.isEndState = isEndState;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
