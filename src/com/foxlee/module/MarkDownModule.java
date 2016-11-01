package com.foxlee.module;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14.
 */
public class MarkDownModule extends BaseModule{

    public GoogleBookMark bookMark;

    public MarkDownModule(){

    }

    public MarkDownModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
        StringBuilder str=new StringBuilder();
        str.append("# Android学习文档和工具\n\n");
        str.append("## 工具篇\n\n");
        ArrayList<BookChildModule> childlist=bookMark.roots.bookmark_bar.children;
        BookChildModule androidUtil=null;
        BookChildModule androidDoc=null;
        for (int i = 0; i < childlist.size(); i++) {
            if("android技术".equals(childlist.get(i).name)){
                androidDoc=childlist.get(i);
            }
            if("android工具".equals(childlist.get(i).name)){
                androidUtil=childlist.get(i);
            }
        }
        if(androidUtil==null){
            content=str.toString();
            return;
        }
        BookChildModule childModule=null;
        BookChildModule childModule1=null;
        for (int i = 0; i < androidUtil.children.size(); i++) {
            childModule=androidUtil.children.get(i);
            str.append("\n### ");
            str.append(childModule.name);
            str.append("\n");
            for (int j = 0; j < childModule.children.size(); j++) {
                childModule1=childModule.children.get(j);
                str.append("[");
                str.append(childModule1.name);
                str.append("](");
                str.append(childModule1.url);
                str.append(")<br>\n");
            }
        }
        if(androidDoc==null){
            content=str.toString();
            return;
        }

        str.append("## 技术篇\n\n");


        for (int i = 0; i < androidDoc.children.size(); i++) {
            childModule=androidDoc.children.get(i);
            str.append("\n### ");
            str.append(childModule.name);
            str.append("\n");
            for (int j = 0; j < childModule.children.size(); j++) {
                childModule1=childModule.children.get(j);
                str.append("[");
                str.append(childModule1.name);
                str.append("](");
                str.append(childModule1.url);
                str.append(")<br>\n");
            }
        }
        content=str.toString();
    }
}
