import java.util.ArrayList;
import java.util.List;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.JPF;
import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.VM;

/**
 * A listener that builds a 3D state space visual application that is explored by JPF.
 * Loosely based off of gov.nasa.jpf.listener.StateSpaceDot.
 *
 * @author Kevin Arindaeng
 * @version 1.0
 * @since 2018-03-23
 */
public class StateSpace3D extends ListenerAdapter {

	private List<String> states;
	private List<String> transitions;

	public StateSpace3D(Config conf, JPF jpf) {
		VM vm = jpf.getVM();
		vm.recordSteps(true);
		this.states = new ArrayList<String>();
		this.transitions = new ArrayList<String>();
	}

	@Override
	public void searchStarted(Search search) {

	}

	@Override
	public void searchFinished(Search search) {
	}

	@Override
	public void stateAdvanced(Search search) {
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
