package com.dindane.cacophony;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Path;

public class Util {
    public static String tryToDetectMimeType(Path path) {
        String mimeType = null;

        try {
            mimeType = java.nio.file.Files.probeContentType(path);

            if (mimeType == null) {
                File file = new File(path.toAbsolutePath().toString());
                mimeType = URLConnection.guessContentTypeFromName(file.getName());
            }
        } catch (IOException e) {
        }

        return mimeType;
    }
}
