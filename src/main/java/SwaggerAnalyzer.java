import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;

import java.io.File;
import java.util.Map;

/**
 * Created by tincho on 11/05/17.
 */
public class SwaggerAnalyzer {

    public static void main (String[] args) {

        // Path to swagger specifications
        String apiList = "./jsons";

        File[] folder = new File(apiList).listFiles();
        String line;
        for (File file : folder) {
            System.out.println(file);

            //Initialize parser
            Swagger swagger = new SwaggerParser().read(file.toString());

            //Get the paths (endpoints or resources)
            Map<String, Path> paths = swagger.getPaths();

            for (Map.Entry<String, Path> entry : paths.entrySet()) {
                // Do something with the paths
                System.out.println(entry.getKey());
                Path path = entry.getValue();

                // Iterate the operations available for a path (usually RESTful ones)
                for (Operation operation : path.getOperations()){
                    // Do something with the operation
                  System.out.println("\t" + operation.getOperationId() + " -- " + operation.getDescription());
                    System.out.print("\t\t");

                    //Iterate the parameters for the operation
                    for (Parameter parameter : operation.getParameters()){
                        // Do something wih the parameters
                      System.out.print(" " + parameter.getName());
                    }
                    System.out.println();
                }
                System.out.println();
            }
        }
    }
}
