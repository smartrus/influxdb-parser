package pro.rustem.java.influxdb;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A wrapper class for shell commands execution
 */
public class CmdExecutor {
    protected Integer executeCommand(String command) {
        Integer exitCode = 0;

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            System.out.println("Influxdb dump started...");
            p = Runtime.getRuntime().exec(command);
            exitCode = p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Influxdb dump done.");
        return exitCode;

    }
}
