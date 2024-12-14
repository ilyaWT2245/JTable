package ru.vsu.cs.course1;

import org.apache.commons.cli.*;

import java.io.PrintStream;
import java.util.Locale;
import ru.vsu.cs.utils.ArrayUtils;

public class Program {
    public static final String PROGRAM_NAME_IN_HELP = "program (-h | -w | (-s -i <in-file> [-o <out-file>]))";

    public static void winMain() throws Exception {
        Locale.setDefault(Locale.ROOT);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Options cmdLineOptions = new Options();

        cmdLineOptions.addOption("h", "help", false, "Show help");
        cmdLineOptions.addOption("w", "window", false, "Use window user interface");
        cmdLineOptions.addOption("i", "input-file", true, "Input file");
        cmdLineOptions.addOption("o", "output-file", true, "Output file");
        cmdLineOptions.addOption("s", "solve", false, "Solve task");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(cmdLineOptions, args);
        } catch (Exception e) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }

        if (cmdLine.hasOption("h")) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }
        if (cmdLine.hasOption("w")) {
            winMain();
        } else {
            if (!cmdLine.hasOption("i")) {
                new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
                System.exit(1);
            }
            String inputFilename = cmdLine.getOptionValue("i");
            int[][] arr2 = ArrayUtils.readIntArray2FromFile(inputFilename);
            if (arr2 == null) {
                System.err.printf("Can't read array from \"%s\"%n", inputFilename);
                System.exit(2);
            }
            if (cmdLine.hasOption("s")) {
            }

            PrintStream out = (cmdLine.hasOption("o")) ? new PrintStream(cmdLine.getOptionValue("o")) : System.out;
            out.println(ArrayUtils.toString(arr2));
            out.close();
        }
    }
}
