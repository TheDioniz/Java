import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskPasswords {

    private final static String PASS_OPTIONS = "haslo|password|pass|passw";
    private final static String REGEX = "((" + PASS_OPTIONS + ")[\\s]*?[-=:_@ ]\\s*)(.\\w*)";

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Program usage:");
            System.err.println("java -jar <program_name> <src_file> <dst_file>");
            System.exit(0);
        }

        //Path srcFilePath = new File("/home/dszczukocki/Devel/Java/RegexTest/src/regextest/password_mock_file.txt").toPath();
        //Path dstFilePath = new File("/home/dszczukocki/Devel/Java/RegexTest/src/regextest/masked_data.txt").toPath();
        Path srcFilePath = new File(args[0]).toPath();
        Path dstFilePath = new File(args[1]).toPath();

        List<String> maskedData = readFile(srcFilePath, MaskPasswords.REGEX);
        writeFile(maskedData, dstFilePath);

        System.out.println("Successfully removed passwords from file: " + srcFilePath);
        System.out.println("Output data in: " + dstFilePath);

    }

    public static boolean writeFile(List<String> maskedData, Path dstFilePath) {

        try {
            Files.write(dstFilePath, maskedData, StandardOpenOption.CREATE);

        } catch (FileNotFoundException ex) {
            System.err.println("[Exception occurred] Message: DST file not found.");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("[Exception occurred] Message: Could not read the file.");
            System.err.println(ex);
        }

        return true;
    }

    public static List<String> readFile(final Path srcFilePath, final String regex) {
        // for temporal usage
        List<String> fileContent = null;
        // for masked contents
        List<String> maskedData = new ArrayList<>();
        // Read the file and mask the passwords
        try {
            fileContent = Files.readAllLines(srcFilePath);
            for (String line : fileContent) {
                line = maskData(line, regex);
                maskedData.add(line);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("[Exception occurred] Message: SRC file not found.");
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println("[Exception occurred] Message: Could not read the file.");
            System.err.println(ex);
        }

        return maskedData;
    }

    public static String maskData(final String data, final String regex) {

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(data);

        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            //System.out.println("found password: " + matcher.group(2));
            matcher.appendReplacement(sb, matcher.group(1) + "***masked***");
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

}
