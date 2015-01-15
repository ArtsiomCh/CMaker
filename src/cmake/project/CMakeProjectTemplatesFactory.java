package cmake.project;

import cmake.global.CMakeIcons;
import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.platform.ProjectTemplate;
import com.intellij.platform.ProjectTemplatesFactory;
import com.intellij.platform.templates.BuilderBasedTemplate;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by alex on 1/15/15.
 */
public class CMakeProjectTemplatesFactory extends ProjectTemplatesFactory {
    @NotNull
    @Override
    public String[] getGroups() {
        return new String[] {
                "Executable",
                "Library"};
    }

    @Override
    public Icon getGroupIcon(String group) {
        return CMakeIcons.FILE;
    }
    @NotNull
    @Override
    public ProjectTemplate[] createTemplates(String s, WizardContext wizardContext) {
        return new ProjectTemplate[0];
    }

    static class CMakeTemplate extends BuilderBasedTemplate {
        String name;
        String description;
        public CMakeTemplate(@NotNull String name, @NotNull String description,ModuleBuilder builder) {
            super(builder);
        }
        
        @Override
        @NotNull
        public String getName() {
            return name;
        }

        @Override
        @NotNull
        public String getDescription()
        {
           return description;
        }
    }
    
    
}
