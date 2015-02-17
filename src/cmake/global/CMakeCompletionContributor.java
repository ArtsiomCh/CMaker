package cmake.global;

import cmake.parsing.CMakeParserUtilImpl;
import cmake.psi.*;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by alex on 1/13/15.
 */
public class CMakeCompletionContributor extends CompletionContributor {
    public CMakeCompletionContributor() {
        // Completion for the variables in scope
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(CMakeTypes.UNQUOTED_ARGUMENT).withLanguage(CMakeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {

                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  ProcessingContext processingContext,
                                                  @NotNull CompletionResultSet resultSet) {
                        // TODO: add variables in global scope or in the nested scope if definition is nested
                        
                        List<PsiElement> vars = CMakeParserUtilImpl.getDefinedVars(parameters.getOriginalFile());
                        for (PsiElement e : vars) {
                            CMakeArguments args = ((CMakeCommandExpr)e).getArguments();
                            StringBuilder variant = new StringBuilder();
                            variant.append("${");
                            variant.append(args.getArgument().getText());
                            variant.append("}");
                            resultSet.addElement(LookupElementBuilder.create(variant.toString()).withIcon(
                                    ((CMakeCommandExpr) e).getPresentation().getIcon(true)
                            ));

                        } 
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(CMakeTypes.IDENTIFIER).withLanguage(CMakeLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        System.out.println("Complete" + parameters.toString() + "\n");
                        List<PsiElement> defs = CMakeParserUtilImpl.getDefinedSymbols(parameters.getOriginalFile().getProject());
                        for (PsiElement e : defs) {
                            CMakeArguments args = ((CMakeCommandExpr)e).getArguments();
                            StringBuilder variant = new StringBuilder();
                            variant.append(args.getArgument().getText());
                            variant.append("( ");
                            List<CMakeSeparatedArgument> arglist = args.getSeparatedArgumentList();
                            for(PsiElement a : arglist)
                            {
                                variant.append("<");
                                variant.append(a.getText());
                                variant.append("> ");
                            }
                            variant.append(")");
                            resultSet.addElement(LookupElementBuilder.create(variant.toString()).withIcon(
                                    ((CMakeCommandExpr) e).getPresentation().getIcon(true)
                            ));

                        }
                    }
                }
        );
        
    }
}
