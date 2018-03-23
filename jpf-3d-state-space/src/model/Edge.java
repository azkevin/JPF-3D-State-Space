package model;

import gov.nasa.jpf.vm.ThreadInfo;

/**
 * Holds information between two states (edge) when searched by JPF
 *
 * @author Kevin Arindaeng
 * @version 1.2
 * @since 2018-03-23
 */
public class Edge {
	
	private int edgeId;
	private ThreadInfo executedThread;
	
	public Edge(int edgeId, ThreadInfo executedThread) {
		this.edgeId = edgeId;
		this.executedThread = executedThread;
	}
	
	@Override
	public String toString() {
		return "edgeId: " + this.edgeId + 
				", lastExecutedClass: " + this.executedThread.toString();
	}
	
}
