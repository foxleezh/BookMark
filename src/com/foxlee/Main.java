/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foxlee;

import com.foxlee.module.GoogleBookMark;
import com.foxlee.module.MarkDownModule;
import com.foxlee.util.FileOperation;
import com.google.gson.Gson;

public class Main {
    /**
     * 运行的路径，.jar的路径
     */
    private static final String ARG_HELP = "--help";
    private static final String ARG_GOOGLE_DIR = "-ip";
    private static final String ARG_GOOGLE_FILE = "-if";
    private static final String ARG_OUT_DIR = "-op";
    private static final String ARG_OUT_NAME = "-of";


//    public String inputPath = "C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default";        //google书签目录
//    public String inputFile = "Bookmarks";        //google书签名
//    public String outputPath = "D:/application/BookMark/build/jar";        //输出markdown目录
//    public String outputFile = "AndroidDoc.md";        //输出markdown地址


    public String inputPath = "";        //google书签目录
    public String inputFile = "";        //google书签名
    public String outputPath = "";        //输出markdown目录
    public String outputFile = "";        //输出markdown地址

    public GoogleBookMark bookMark;


    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    void run(String[] args) {
        parseArgs(args);
        readBookMarks();
        createModule();
    }

    private void createModule() {
        if("".equals(outputPath)||"".equals(outputFile)){
            usage();
            return;
        }
        MarkDownModule module=new MarkDownModule(outputPath,outputFile);
        module.bookMark=bookMark;
        module.write();
    }

    private void readBookMarks() {
        if("".equals(inputPath)||"".equals(inputFile)){
            usage();
            return;
        }
        Gson gson=new Gson();
        String json=FileOperation.read(inputPath,inputFile);
        bookMark=gson.fromJson(json,GoogleBookMark.class);
    }

    private void parseArgs(String[] args) {
        int index;

        for (index = 0; index < args.length; index++) {
            String arg = args[index];

            if (arg.equals(ARG_HELP)) {
                usage();
                break;
            } else if (arg.equals(ARG_GOOGLE_DIR)) {
                if (index == args.length - 1) {
                    System.err.println("Missing rootdir argument");
                    usage();
                }
                inputPath = args[++index];
            }else if (arg.equals(ARG_GOOGLE_FILE)) {
                if (index == args.length - 1) {
                    System.err.println("Missing rootdir argument");
                    usage();
                }
                inputFile = args[++index];
            }else if (arg.equals(ARG_OUT_DIR)) {
                if (index == args.length - 1) {
                    System.err.println("Missing rootdir argument");
                    usage();
                }
                outputPath = args[++index];
            }else if (arg.equals(ARG_OUT_NAME)) {
                if (index == args.length - 1) {
                    System.err.println("Missing rootdir argument");
                    usage();
                }
                outputFile = args[++index];
            }
        }
    }

    private void usage() {
        System.err.print(
                "usage\n"+
                "-ip (input path of Google bookmark)\n"+
                "-if (input file of Google bookmark)\n"+
                "-op (output path of markdown)\n"+
                "-of (output file of markdown)\n"
        );
        System.exit(0);
    }

}
