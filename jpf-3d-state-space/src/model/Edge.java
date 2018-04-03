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
	private String edgeInfo;
	
	public Edge(int edgeId, String edgeInfo) {
		this.edgeId = edgeId;
		this.edgeInfo = edgeInfo;
	}
	
	@Override
	public String toString() {
		return "edgeId: " + this.edgeId + 
				", edgeInfo: " + this.edgeInfo;
	}
	
}
