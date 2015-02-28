package oblig1parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import syntaxtree.expressions.Variable;

@RunWith(Enclosed.class)
public class VariableParserTest extends ParserTest {

	private static Symbol parseSymbol(String string) throws Exception {
		Scanner scanner = new Lexer(toInputStream(string));
		VarParser parser = new VarParser(scanner);
		return parser.parse();
	}

	private static Variable parse(String string) throws Exception {
		return (Variable) parseSymbol(string).value;
	}

	public static class ParseMethod {

		@Test
		public void lowercaseName_success() throws Exception {
			String name = "foo";
			
			assertEquals(name, parse(name).getName());
		}

		@Test
		public void uppercaseName_success() throws Exception {
			String name = "FOO";
			
			assertEquals(name, parse(name).getName());
		}
		
		@Test(expected = ScannerError.class)
		public void digitsName_errorThrown() throws Exception {
			parse("1234");
		}
		
		@Test
		public void digitsPrecededByLetterName_success() throws Exception {
			String name = "f1234";
			
			assertEquals(name, parse(name).getName());
		}
		
		//@Test(expected = ScannerError.class)
		public void underscoreName_errorThrown() throws Exception {
			parse("_");
		}
		
		@Test
		public void underscorePrecededByLetterName_success() throws Exception {
			String name = "f_";
			
			assertEquals(name, parse(name).getName());
		}
		
		//@Test(expected = ScannerError.class)
		public void dollarSymbolName_errorThrown() throws Exception {
			parse("$");
		}
		
		@Test
		public void scandinavianLetterName_success() throws Exception {
			parse("ø");
		}
		
		@Test
		public void expressionDotName_success() throws Exception {
			String varString = VARIABLE_NAME + "." + VARIABLE_NAME2;
			Variable variable = parse(varString);
			
			assertEquals(VARIABLE_NAME2, variable.getName());
			assertEquals(VARIABLE_NAME, variable.getExpression().getName());
		}
	}

	@RunWith(Parameterized.class)
	public static class ParseMethodKeyword {

		@Parameter
		public Keyword keyword;

		@Test(expected = ParserSyntaxException.class)
		public void keyword_exceptionThrown() throws Exception {
			parse(keyword.toString());
		}

		@Parameters(name = "keyword {0}")
		public static Iterable<Object[]> data() {
			List<Object[]> result = new ArrayList<Object[]>();

			for (Keyword keyword : Keyword.values()) {
				result.add(new Object[] { keyword });
			}

			return result;
		}
	}
}