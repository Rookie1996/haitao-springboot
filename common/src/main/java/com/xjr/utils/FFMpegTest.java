package com.xjr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {

    private String ffmpegEXE;

    public FFMpegTest(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    //processBuilder shell命令行执行
    public void convertor(String videoInputPath, String videoOutPutPath) throws IOException {
        //ffmpeg -i input.mp4 -y output.avi

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-y");
        command.add(videoOutPutPath);
        for (String str : command) {
            System.out.print(str + " ");
        }

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
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

    }

    public static void main(String[] args) {
        FFMpegTest ffmpeg = new FFMpegTest("D:\\video_dev\\ffmpeg-20200826-8f2c1f2-win64-static\\ffmpeg-20200826-8f2c1f2-win64-static\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("D:\\video_dev\\ffmpeg-20200826-8f2c1f2-win64-static\\ffmpeg-20200826-8f2c1f2-win64-static\\bin\\in.mp4",
                    "D:\\video_dev\\ffmpeg-20200826-8f2c1f2-win64-static\\ffmpeg-20200826-8f2c1f2-win64-static\\bin\\video.avi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
