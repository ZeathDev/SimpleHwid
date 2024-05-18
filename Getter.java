import java.io.IOException;

public class Getter {
    public static void main(String[] args) {
        try {
            System.out.println("加密前:" + getUUIDFromWMIC());
            String str = MathUtils.AES(getUUIDFromWMIC(), "114514");
            System.out.println("加密后:" + str);
            System.out.println("解密后:" + MathUtils.AntiAES(str, "114514"));
        } catch (Exception e) {
            System.out.println("Can't get uuid");
        }
    }

    private static String getUUIDFromWMIC() throws IOException {
        Process process = Runtime.getRuntime().exec("wmic csproduct get uuid");
        String output = parseOutput(process);
        return output.substring(4).trim();
    }

    private static String parseOutput(Process process) throws IOException {
        byte[] buffer = new byte[1024];
        StringBuilder output = new StringBuilder();
        process.getInputStream().read(buffer);
        output.append(new String(buffer));
        return output.toString();
    }
}
