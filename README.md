# Automatic Photo Correction  自动照片矫正工具

本项目实现了对拍摄角度不正的照片进行自动矫正的功能，适用于有明显线条的风景照等图片，以及身份证、合同、纸质文档等扫描场景。

## 功能

✅ 支持自动检测图像角度并进行旋转校正  
✅ 支持图像旋转 + 边缘自动填充  

## 版本要求

Java17+

OpenCV

## 关于OpenCV安装
1. 下载 OpenCV

    前往官网下载 OpenCV for Windows / Linux / macOS：

    🔗 https://opencv.org/releases/

    推荐版本：**OpenCV 4.9** 或更新 (作者使用版本: **4.11**)

2. 安装

    下载解压后，在以下路径找到相关文件：
    
    opencv/build/java/

    你会看到： opencv-4**.jar 以及对应的dll 

    将它们 **分别** 放到系统文件夹user的.jdks下 lab/ 和 bin/ 两个文件夹，并在IDE中引入


##  TODO List

- [√] 图像自动旋转角度检测
- [ ] 透视矫正功能 
- [ ] 支持多种边缘填充方式选择
- [ ] 开发Android版本
- [ ] 添加 UI 界面支持文件拖拽


## 关于贡献
欢迎提交 PR 和 Issue
