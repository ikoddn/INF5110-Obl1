package syntaxtree.statements;

import java.util.List;

import syntaxtree.AstStringListBuilder;
import syntaxtree.expressions.Expression;

public class IfStatement extends Statement {

	private Expression expression;
	private List<Statement> ifBodyStatements;
	private List<Statement> elseBodyStatements;

	public IfStatement(Expression expression, List<Statement> ifBodyStatements,
			List<Statement> elseBodyStatements) {
		this.expression = expression;
		this.ifBodyStatements = ifBodyStatements;
		this.elseBodyStatements = elseBodyStatements;
	}

	public Expression getExpression() {
		return expression;
	}

	public List<Statement> getIfBodyStatements() {
		return ifBodyStatements;
	}

	public List<Statement> getElseBodyStatements() {
		return elseBodyStatements;
	}

	@Override
	public List<String> makeAstStringList() {
		AstStringListBuilder ast = new AstStringListBuilder("IF_STMT");

		AstStringListBuilder ifBody = new AstStringListBuilder("");
		ifBody.addIndented(ifBodyStatements);

		ast.addInline(expression).addIndented(ifBody);

		if (!elseBodyStatements.isEmpty()) {
			AstStringListBuilder elseBody = new AstStringListBuilder("");
			elseBody.addIndented(elseBodyStatements);

			ast.add("ELSE").addIndented(elseBody);
		}

		return ast.build();
	}
}
