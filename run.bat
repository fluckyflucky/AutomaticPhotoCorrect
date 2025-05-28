@echo off
setlocal

:: ���ò���
set "INPUT_DIR=input"
set "OUTPUT_DIR=output"
set "MAIN_CLASS=com.example.Main"
set "JAR_FILE=photoCheck-1.0.jar"
set "OPENCV_DLL_PATH=opencv_java4110.dll"



:: ��� Java �Ƿ����
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [����] Java δ��װ��δ�����ڻ��������С�
    pause
    exit /b 1
)

:: ��� OpenCV DLL �Ƿ����
if not exist "%OPENCV_DLL_PATH%" (
	color 04
    echo [����] �Ҳ��� OpenCV DLL��%OPENCV_DLL_PATH%
	color 07
    pause
    exit /b 1
)

:: ���� output �ļ��У���������ڣ�
if not exist "%OUTPUT_DIR%" mkdir "%OUTPUT_DIR%"

:: ���� input �е�ͼƬ�ļ�
for %%F in ("%INPUT_DIR%\*.jpg" "%INPUT_DIR%\*.png") do (
    echo -
    echo -
    echo ----------���ڴ���%%~nxF----------

    java --enable-native-access=ALL-UNNAMED -Djava.library.path=. -jar "%JAR_FILE%" "%%F" "%OUTPUT_DIR%\%%~nxF"
    if errorlevel 1 (
	echo [ʧ��] ----����ʧ�ܣ�%%~nxF 

    ) else (
	echo [�ɹ�] ----�ѱ�������%OUTPUT_DIR%\%%~nxF 
	
    )
	
    
)

pause