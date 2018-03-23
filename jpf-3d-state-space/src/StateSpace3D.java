import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.VM;
import model.State;

/**
 * A listener that builds a 3D state space visual application that is explored by JPF.
 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.
 *
 * @author Kevin Arindaeng
 * @version 1.0
 * @since 2018-03-23
 */
public class StateSpace3D extends ListenerAdapter {

	private HashMap<Integer, State> states;
	private List<String> transitions;

	public StateSpace3D(Config conf, JPF jpf) {
		VM vm = jpf.getVM();
		vm.recordSteps(true);
		this.states = new HashMap<Integer, State>();
		this.transitions = new ArrayList<String>();
	}

	@Override
	public void searchStarted(Search search) {

	}

	@Override
	public void searchFinished(Search search) {
		for(State state : states.values()) {
			System.out.println(state.toString());
		}
	}

	@Override
	public void stateAdvanced(Search search) {
		int id = search.getStateId();
		boolean hasNext = search.hasNextState();
		boolean isNew = search.isNewState();
		boolean isEndState = search.isEndState();
		states.put(id, new State(id, hasNext, isNew, isEndState));
	}

	@Override
	public void stateRestored(Search search) {
	}

	@Override
	public void stateProcessed(Search search) {
	}

	@Override
	public void stateBacktracked(Search search) {
	}

}
