package syntaxtree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Program extends AstNode {

	private List<Decl> decls;

	public Program(List<Decl> decls) {
		this.decls = decls;
	}

	public List<Decl> getDecls() {
		return decls;
	}

	@Override
	public List<String> makeAstPrint() {
		List<String> result = new LinkedList<String>();
		result.add("(PROGRAM");

		Iterator<Decl> it = decls.iterator();
		while (it.hasNext()) {
			Decl decl = it.next();

			result.addAll(prependWithIndentation(decl.makeAstPrint()));

			if (it.hasNext()) {
				result.add("");
			}
		}

		result.add(")");

		return result;
	}
}
