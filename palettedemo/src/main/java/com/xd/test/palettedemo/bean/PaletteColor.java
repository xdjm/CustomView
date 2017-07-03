package com.xd.test.palettedemo.bean;

/**
 * Copyright (C) 2017 By yjm at 20:55
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class PaletteColor {
    public int getTitleInt() {
        return titleInt;
    }

    public PaletteColor setTitleInt(int titleInt) {
        this.titleInt = titleInt;
        return this;
    }

    int titleInt;
    int tvInt;
    int imgInt;

    public String getTitle() {
        return title;
    }

    public PaletteColor setTitle(String title) {
        this.title = title;
        return this;
    }

    String title;
    String tv;
    public PaletteColor(int titleInt,int tvInt, int imgInt, String name,String title) {
       this.titleInt = titleInt;
        this.tvInt = tvInt;
        this.imgInt = imgInt;
        this.tv = name;
        this.title = title;
    }

    public int getTvInt() {
        return tvInt;
    }

    public PaletteColor setTvInt(int tvInt) {
        this.tvInt = tvInt;
        return this;
    }

    public int getImgInt() {
        return imgInt;
    }

    public PaletteColor setImgInt(int imgInt) {
        this.imgInt = imgInt;
        return this;
    }

    public String getTv() {
        return tv;
    }

    public PaletteColor setTv(String tv) {
        this.tv = tv;
        return this;
    }
}
