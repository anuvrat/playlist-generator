package pba.plgen.common.utils;

/**
 * @author AnuvratSingh
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {
	private final A m_elementA;
	private final B m_elementB;

	public Pair(A elementA, B elementB) {
		m_elementA = elementA;
		m_elementB = elementB;
	}

	public A getElementA() {
		return m_elementA;
	}

	public B getElementB() {
		return m_elementB;
	}
}
