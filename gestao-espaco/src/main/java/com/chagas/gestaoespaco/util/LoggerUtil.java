package com.chagas.gestaoespaco.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {
private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

public static void info(String mensagem) {
    System.out.println("[INFO] " + LocalDateTime.now().format(formatter) + " - " + mensagem);
}

public static void warn(String mensagem) {
    System.out.println("[WARN] " + LocalDateTime.now().format(formatter) + " - " + mensagem);
}

public static void error(String mensagem, Exception e) {
    System.err.println("[ERROR] " + LocalDateTime.now().format(formatter) + " - " + mensagem);
    if (e != null) {
        e.printStackTrace();
    }
}
}