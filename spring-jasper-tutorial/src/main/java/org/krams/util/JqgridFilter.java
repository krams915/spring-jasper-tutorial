package org.krams.util;

import java.util.ArrayList;

/**
 * A POJO that represents a jQgrid JSON requests {@link String}<br/>
 * A sample filter follows the following format:
 * <pre>
 * {"groupOp":"AND","rules":[{"field":"firstName","op":"eq","data":"John"}]}
 * </pre>
 */
public class JqgridFilter {
	
	private String source;
	private String groupOp;
	private ArrayList<Rule> rules;
	
	public JqgridFilter() {
		super();
	}
	
	public JqgridFilter(String source) {
		super();
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public ArrayList<Rule> getRules() {
		return rules;
	}
	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * Inner class containing field rules
	 */
	public static class Rule {
		private String junction;
		private String field;
		private String op;
		private String data;

		public Rule() {}
		
		public Rule(String junction, String field, String op, String data) {
			super();
			this.junction = junction;
			this.field = field;
			this.op = op;
			this.data = data;
		}

		public String getJunction() {
			return junction;
		}

		public void setJunction(String junction) {
			this.junction = junction;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOp() {
			return op;
		}

		public void setOp(String op) {
			this.op = op;
		}
	
		public String getData() {
			return data;
		}
	
		public void setData(String data) {
			this.data = data;
		}
	}

	
}

