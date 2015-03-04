package syntaxtree.declarations;

import java.util.LinkedList;
import java.util.List;

import syntaxtree.Name;
import syntaxtree.datatypes.DataType;
import syntaxtree.statements.Statement;

public class ProcedureDecl extends Decl {

	private DataType returnType;
	private List<ParameterDecl> parameterDecls;
	private List<Decl> subDecls;
	private List<Statement> subStatements;

	public ProcedureDecl(Name name, DataType returnType,
			List<ParameterDecl> parameterDecls, List<Decl> subDecls,
			List<Statement> subStatements) {
		super(name);

		this.returnType = returnType;
		this.parameterDecls = parameterDecls;
		this.subDecls = subDecls;
		this.subStatements = subStatements;
	}

	public DataType getReturnType() {
		return returnType;
	}

	public List<ParameterDecl> getParameterDecls() {
		return parameterDecls;
	}

	public List<Decl> getSubDecls() {
		return subDecls;
	}

	public List<Statement> getSubStatements() {
		return subStatements;
	}

	@Override
	public List<String> makeAstStringList() {
		List<String> result = new LinkedList<String>();

		StringBuilder sb = new StringBuilder();
		sb.append("(PROC_DECL ");
		sb.append(returnType.makeAstString());
		sb.append(" ");
		sb.append(name.makeAstString());
		result.add(sb.toString());

		for (ParameterDecl parameterDecl : parameterDecls) {
			result.addAll(prependAllWithIndentation(parameterDecl
					.makeAstStringList()));
		}

		if (!subDecls.isEmpty()) {
			result.add("");

			for (Decl decl : subDecls) {
				result.addAll(prependAllWithIndentation(decl.makeAstStringList()));
			}
		}

		if (!subStatements.isEmpty()) {
			result.add("");

			for (Statement statement : subStatements) {
				result.addAll(prependAllWithIndentation(statement
						.makeAstStringList()));
			}
		}

		result.add(")");

		return result;
	}
}