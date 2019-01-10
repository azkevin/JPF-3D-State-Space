import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjglb.engine.GameEngine;
import org.lwjglb.engine.IGameLogic;
import org.lwjglb.game.DummyGame;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.Step;
import gov.nasa.jpf.vm.Transition;
import model.State;
import model.Edge;

/**
 * A listener that builds a 3D state space visual application that is explored by JPF.
 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.
 *
 * @author Kevin Arindaeng
 * @version 1.2
 * @since 2018-03-23
 */
public class StateSpace3D extends ListenerAdapter {

	private HashMap<Integer, State> states;
	private List<Edge> edges;
	private List<String> transitions;
	
	private State prevState = null;
	private int edgeCounter;

	public StateSpace3D(Config conf, JPF jpf) {
		this.states = new HashMap<Integer, State>();
		this.edges = new ArrayList<Edge>();
		this.transitions = new ArrayList<String>();
		this.edgeCounter = 0;
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
		
		System.out.println("\n--- Edges --- ");
		for(Edge edge : edges) {
			System.out.println(edge.toString());
		}
		
		System.out.println("\n--- Transitions --- ");
		for(String transition : transitions) {
			System.out.println(transition);
		}
		
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("JPF-3D-State-Space", 600, 480, vSync, gameLogic);
            gameEng.start();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
	}

	@Override
	public void stateAdvanced(Search search) {
		int stateId = search.getStateId();
		boolean hasNext = search.hasNextState();
		boolean isNew = search.isNewState();
		boolean isEndState = search.isEndState();
		if (this.prevState != null) {
			this.addTransition(this.prevState.getStateId(), stateId);
		} else {
			this.prevState = new State();
		}
		
		states.put(stateId, new State(stateId, hasNext, isNew, isEndState, null));
		edges.add(new Edge(this.edgeCounter, getEdgeLabel(search, stateId)));
		
		this.prevState.setState(stateId, hasNext, isNew, isEndState, null);
	}

	@Override
	public void stateRestored(Search search) {
		this.prevState.setState(search.getStateId(), search.hasNextState(), search.isNewState(), search.isEndState(), null);
		
	}

	@Override
	public void stateProcessed(Search search) {
		
	}

	@Override
	public void stateBacktracked(Search search) {
		this.prevState.setState(search.getStateId(), search.hasNextState(), search.isNewState(), search.isEndState(), null);
	}
	
	/**
	 * Return the string that will be used to label this edge for the user.
	 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.makeDotLabel()
	 * 
	 */
	private String getEdgeLabel(Search state, int my_id) {
		Transition trans = state.getTransition();
		if (trans == null) {
			return "-init-";
		}
		Step last_trans_step = trans.getLastStep();
		if (last_trans_step == null) {
			return "?";
		}

		StringBuilder result = new StringBuilder();

		int thread = trans.getThreadIndex();

		result.append("Thd");
		result.append(thread);
		result.append(':');
		result.append(last_trans_step.toString());

		return result.toString();
	}

	/**
	 * Creates a transition: currentState --> nextEdge --> nextState
	 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.addEdge()
	 */
	private void addTransition(int oldId, int newId) {
		int id = this.edgeCounter++;
		transitions.add("state" + oldId + " -> edge" + id);
		transitions.add("edge" + id + " -> state" + newId);
	}
}
