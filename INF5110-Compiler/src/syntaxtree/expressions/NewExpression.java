package syntaxtree.expressions;

import java.util.List;

import syntaxtree.AstStringListBuilder;
import syntaxtree.datatypes.DataType;

import compiler.SymbolTable;
import compiler.exception.SemanticException;

public class NewExpression extends Expression {

	private DataType dataType;

	public NewExpression(DataType dataType) {
		this.dataType = dataType;
	}

	public DataType getDataType() {
		return dataType;
	}

	@Override
	public DataType determineType(SymbolTable symbolTable) throws SemanticException {
		return dataType;
	}

	@Override
	public List<String> makeAstStringList() {
		return new AstStringListBuilder("NEW").addInline(dataType).build();
	}
}
