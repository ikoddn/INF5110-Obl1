package syntaxtree.declarations;

import java.util.List;

import syntaxtree.AstStringListBuilder;
import syntaxtree.Name;
import syntaxtree.datatypes.DataType;

import compiler.SymbolTable;
import compiler.exception.SemanticException;

public class VariableDecl extends Decl {

	private DataType dataType;

	public VariableDecl(Name name, DataType dataType) {
		super(name);
		this.dataType = dataType;
	}

	public DataType getDataType() {
		return dataType;
	}

	@Override
	public DataType determineType(SymbolTable symbolTable)
			throws SemanticException {
		return dataType;
	}

	@Override
	public List<String> makeAstStringList() {
		return new AstStringListBuilder("VAR_DECL").addInline(dataType, name)
				.build();
	}
}
