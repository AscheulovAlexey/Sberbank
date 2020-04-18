package com.sberbank.task3;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadService implements Runnable {

    private String fromURL;
    private String toPath;
    public String threadName;

    public DownloadService(String fromURL, String toPath) {
        this.fromURL = fromURL;
        this.toPath = toPath;
    }

    @Override
    public void run() {
        try {
            threadName = Thread.currentThread().getName();
            downloadFileFromURL(fromURL, toPath);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
        }
    }

    public void downloadFileFromURL(String fromURL, String pathToDirectory) throws IOException, TikaException {
        System.out.println("Начал скачивать файл. Поток: " + threadName + "\nСсылка: " + fromURL + "\n");
        URL url = new URL(fromURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());

        File directory = new File(pathToDirectory);
        if (!directory.exists()) directory.mkdirs();

        String pathToFile = pathToDirectory +
                FilenameUtils.getBaseName(url.getPath()) + getExtensionFile(bufferedInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(pathToFile, false);

        int read = 0;
        int currentPercent = 0;
        double downloaded = 0.00;
        int percentDownloaded = 0;
        byte[] buffer = new byte[512];
        int fileSize = connection.getContentLength();

        long startTime = System.nanoTime();
        while ((read = bufferedInputStream.read(buffer, 0, 512)) != -1) {
            GlobalVariables.globalRateLimiter.acquire(512);
            fileOutputStream.write(buffer, 0, read);
            downloaded = downloaded + read;
            percentDownloaded = (int) (downloaded * 100) / fileSize;
            if (percentDownloaded % 10 == 0 && currentPercent != percentDownloaded) {
                currentPercent = percentDownloaded;
                System.out.println("Скачано " + percentDownloaded + "% файла. Поток: " + threadName);
            }
        }
        long endTime = System.nanoTime();

        GlobalVariables.totalSize += fileSize;
        SpeedService.measureSpeedDownloadOneThread(startTime, endTime, fileSize, threadName);

        fileOutputStream.close();
    }

    protected String getExtensionFile(BufferedInputStream bufferedInputStream) throws IOException, TikaException {

        TikaInputStream tikaInputStream = TikaInputStream.get(bufferedInputStream);
        TikaConfig tikaConfig = new TikaConfig();
        Detector detector = tikaConfig.getDetector();
        Metadata metadata = new Metadata();
        MediaType mediaType = detector.detect(tikaInputStream, metadata);
        MimeType mimeType = tikaConfig.getMimeRepository().forName(mediaType.toString());
        String extension = mimeType.getExtension();

        return extension;
    }

}