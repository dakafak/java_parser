import parser.nodes.ProjectNode;
import parser.parser_api.ParserInterface;

import java.io.File;

public class ParserUserInterface {

    public static void main(String[] args){
//        if(args != null && args.length >= 1){
//            String projectToParseParentDirectory = args[0];// Parent directory containing all project files
            String projectToParseParentDirectory = "F:\\Projects\\New Blade Dodger Stuff\\blade_dodger_classic";//test directory for parsing

            File projectDirectory = new File(projectToParseParentDirectory);
            if(projectDirectory.exists() && projectDirectory.isDirectory()) {
                ProjectNode project = ParserInterface.getParsedProject(projectDirectory);
                ParserInterface.printProject(project);
            } else {
                System.out.println("Project does not exist at directory");
            }

//        }
    }



}
