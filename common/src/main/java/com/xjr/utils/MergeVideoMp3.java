package com.xjr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        this.ffmpegEXE = ffmpegEXE;
    }

    //1、先删bgm
    public void deleteBgm(String videoInputPath, String videoOutputPath) throws IOException {
        //ffmpeg -i in.mp4 -an nosound.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(videoInputPath);
        command.add("-an");
        command.add(videoOutputPath);

        executeProcess(command);
    }

    //2、再覆盖新的bgm
    //processBuilder shell命令行执行
    public void convertor(String mp3InputPath, String videoInputPath,
                          double seconds, String videoOutPutPath) throws IOException {
        //ffmpeg.exe -i bgm.mp3 -i nosound.mp4  -t 4.1 -y full.mp4
        //以给定mp3和mp4覆盖之后生成以mp4为时长的full。-t为时间,-y表示覆盖

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(mp3InputPath);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        command.add("-y");
        command.add(videoOutPutPath);

        executeProcess(command);

    }

    //直接在原视频上合并新的bgm
    public void merge(String mp3InputPath, String videoInputPath,
                      double seconds, String videoOutPutPath) throws IOException {

        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(mp3InputPath);
        command.add("-i");
        command.add(videoInputPath);
        command.add("-t");
        command.add(String.valueOf(seconds));
        //两个音频通过过滤器合并
        command.add("-filter_complex");
        command.add("amix=inputs=2");
        command.add(videoOutPutPath);

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

        String del_videoInputPath = "D:\\video_dev\\200822BG13YKY5P0\\video\\wxcb5adcf07fc3a00d.o6zAJs29AJyoBjALt2A2kdIHRlN4.gU7qZX7cOuTy9a652f08782917e660f25352165ae5b4.mp4";
        String del_videoOutputPath = "D:\\video_dev\\ffmpeg\\bin\\nosound.mp4";

        String mp3InputPath = "D:\\video_dev\\bgm\\bgm.mp3";
        String videoInputPath = "D:\\video_dev\\ffmpeg\\bin\\nosound.mp4";
        String videoOutputPath = "D:\\video_dev\\ffmpeg\\bin\\full.mp4";

        MergeVideoMp3 ffmpeg = new MergeVideoMp3(ffmpegExe);
        try {
            ffmpeg.deleteBgm(del_videoInputPath, del_videoOutputPath);
            ffmpeg.convertor(mp3InputPath, videoInputPath, 4.1, videoOutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
