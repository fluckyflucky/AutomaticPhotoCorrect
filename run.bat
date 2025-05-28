@echo off
setlocal

:: 设置参数
set "INPUT_DIR=input"
set "OUTPUT_DIR=output"
set "MAIN_CLASS=com.example.Main"
set "JAR_FILE=photoCheck-1.0.jar"
set "OPENCV_DLL_PATH=opencv_java4110.dll"



:: 检查 Java 是否可用
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [错误] Java 未安装或未配置在环境变量中。
    pause
    exit /b 1
)

:: 检查 OpenCV DLL 是否存在
if not exist "%OPENCV_DLL_PATH%" (
	color 04
    echo [错误] 找不到 OpenCV DLL：%OPENCV_DLL_PATH%
	color 07
    pause
    exit /b 1
)

:: 创建 output 文件夹（如果不存在）
if not exist "%OUTPUT_DIR%" mkdir "%OUTPUT_DIR%"

:: 遍历 input 中的图片文件
for %%F in ("%INPUT_DIR%\*.jpg" "%INPUT_DIR%\*.png") do (
    echo -
    echo -
    echo ----------正在处理：%%~nxF----------

    java --enable-native-access=ALL-UNNAMED -Djava.library.path=. -jar "%JAR_FILE%" "%%F" "%OUTPUT_DIR%\%%~nxF"
    if errorlevel 1 (
	echo [失败] ----处理失败：%%~nxF 

    ) else (
	echo [成功] ----已保存至：%OUTPUT_DIR%\%%~nxF 
	
    )
	
    
)

pause