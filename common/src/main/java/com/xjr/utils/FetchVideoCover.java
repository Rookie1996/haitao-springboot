package com.xjr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FetchVideoCover {

    //视频路径
    private String ffmpegEXE;

    public FetchVideoCover(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    public void getCover(String videoInputPath, String coverOutputPath) throws IOException {
        // ffmpeg.exe -ss 00:00:01 -y -i full.mp4 -vframe 1 cut.jpg
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");
        command.add(coverOutputPath);

        executeProcess(command);
    }

    //公用方法执行shell命令
    public void executeProcess(List<String> command) throws IOException {

        for (String str : command) {
            System.out.print(str + " ");
        }
        System.out.println();

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        //process中有错误流、数据流。不读出来会在内存中阻塞主线程执行。
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ((line = br.readLine()) != null) {//为null就是读取end最后一行
            //读取就是释放errorStream!!!
        }
        br.close();
        inputStreamReader.close();
        errorStream.close();
    }

    public static void main(String[] args) {
        String ffmpegExe = "D:\\video_dev\\ffmpeg\\bin\\ffmpeg.exe";
        FetchVideoCover ffmpeg = new FetchVideoCover(ffmpegExe);
        try {
            ffmpeg.getCover("D:\\video_dev\\200822BG13YKY5P0\\video\\5c18bff6b6934e2bbf5056186c5426b0.mp4", "D:\\video_dev\\200822BG13YKY5P0\\video\\cut.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
