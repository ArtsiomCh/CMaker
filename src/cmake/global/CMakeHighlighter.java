package cmake.global;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NotNull;
import cmake.psi.CMakeTypes;
import cmake.parsing.CMakeLexer;

import java.util.Map;

/**
 * Created by alex on 12/21/14.
 */
public class CMakeHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> keys1;
    private static final Map<IElementType, TextAttributesKey> keys2;
    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new CMakeLexer();
    }

    // TODO: Add text highlighting attributes
    // TODO: Add mapping between token and its highlighting properties

    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
        "Keyword",
        DefaultLanguageHighlighterColors.KEYWORD
      );

    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
            "Line comment",
            DefaultLanguageHighlighterColors.LINE_COMMENT
    );

    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "Srting literal",
            DefaultLanguageHighlighterColors.STRING
    );

    public static final TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(
            "CMAKE.BRACES",
            DefaultLanguageHighlighterColors.BRACES
    );

    public static final TextAttributesKey BADCHAR = TextAttributesKey.createTextAttributesKey(
            "CMAKE.BADCHAR",
            DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE
    );

    public static final TextAttributesKey VAREXP = TextAttributesKey.createTextAttributesKey(
            "CMAKE.VAREXP",
            DefaultLanguageHighlighterColors.IDENTIFIER
    );

    public static final TextAttributesKey ESCAPED_CHAR = TextAttributesKey.createTextAttributesKey(
            "CMAKE.ESCAPED_CHAR",
            DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE
    );

    public static final TextAttributesKey BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey(
            "CMAKE.BLOCK_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT
    );

    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
            "CMAKE.NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
    );

    public static final TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "CMAKE.IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
    );

    static {
        keys1 = new THashMap<IElementType, TextAttributesKey>();
        keys2 = new THashMap<IElementType, TextAttributesKey>();
        // TODO: Populate maps here
        keys1.put(CMakeTypes.FUNCTION, KEYWORD);
        keys1.put(CMakeTypes.ENDFUNCTION, KEYWORD);
        keys1.put(CMakeTypes.IF, KEYWORD);
        keys1.put(CMakeTypes.ELSEIF,KEYWORD);
        keys1.put(CMakeTypes.ELSE,KEYWORD);
        keys1.put(CMakeTypes.ENDIF, KEYWORD);
        keys1.put(CMakeTypes.MACRO, KEYWORD);
        keys1.put(CMakeTypes.ENDMACRO, KEYWORD);
        keys1.put(CMakeTypes.FOREACH, KEYWORD);
        keys1.put(CMakeTypes.ENDFOREACH, KEYWORD);
        keys1.put(CMakeTypes.WHILE, KEYWORD);
        keys1.put(CMakeTypes.ENDWHILE, KEYWORD);
        keys1.put(CMakeTypes.KEYWORD, KEYWORD);
        keys1.put(CMakeTypes.LINE_COMMENT, COMMENT);
        keys1.put(CMakeTypes.BRACKET_COMMENT, COMMENT);
        keys1.put(CMakeTypes.QUOTED_ARGUMENT, STRING);
        keys1.put(CMakeTypes.LPAR, BRACES);
        keys1.put(CMakeTypes.RPAR, BRACES);
        keys1.put(TokenType.BAD_CHARACTER, BADCHAR);
        keys1.put(CMakeTypes.VAR_REF, VAREXP);
        //keys1.put(CMakeTypes.ESCAPED_CHAR,ESCAPED_CHAR);
        keys1.put(CMakeTypes.BRACKET_COMMENT,BLOCK_COMMENT);
        keys1.put(CMakeTypes.NUMBER,NUMBER);
        keys1.put(CMakeTypes.IDENTIFIER,IDENTIFIER);
    }
    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        return SyntaxHighlighterBase.pack(keys1.get(iElementType), keys2.get(iElementType));
    }
    //TODO: Fill the map to use it in the ColorsPage
    public static final Map<TextAttributesKey, Pair<String, HighlightSeverity>> DISPLAY_NAMES = new THashMap<TextAttributesKey, Pair<String, HighlightSeverity>>(6);

}
