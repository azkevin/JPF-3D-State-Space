import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.Step;
import model.State;
import model.Transition;

/**
 * A listener that builds a 3D state space visual application that is explored by JPF.
 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.
 *
 * @author Kevin Arindaeng
 * @version 1.1
 * @since 2018-03-23
 */
public class StateSpace3D extends ListenerAdapter {

	private HashMap<Integer, State> states;
	private List<Transition> transitions;

	public StateSpace3D(Config conf, JPF jpf) {
		this.states = new HashMap<Integer, State>();
		this.transitions = new ArrayList<Transition>();
	}

	@Override
	public void searchStarted(Search search) {

	}

	@Override
	public void searchFinished(Search search) {
		
		System.out.println("--- States --- ");
		for(State state : states.values()) {
			System.out.println(state.toString());
		}
		
		System.out.println("\n--- Transitions --- ");
		for(Transition transition : transitions) {
			System.out.println(transition.toString());
		}
		
		/*
		 * A method here that takes in the collection of states and transitions
		 * and creates the 3D state space to the user. Example method call below
		 */
		//create3DStateSpace(this.states, this.transitions);
	}

	@Override
	public void stateAdvanced(Search search) {
		int stateId = search.getStateId();
		boolean hasNext = search.hasNextState();
		boolean isNew = search.isNewState();
		boolean isEndState = search.isEndState();
		states.put(stateId, new State(stateId, hasNext, isNew, isEndState));
		
		String step = "";
		gov.nasa.jpf.vm.Transition trans = search.getTransition();
		if (trans == null) {
			step = "-init-";
		} else if (trans.getLastStep() == null) {
			step = "?";
		} else {
			step = trans.getLastStep().toString();
		}
		int threadId = trans.getThreadIndex();
		transitions.add(new Transition(threadId, step));
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
